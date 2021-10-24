package com.abeltarazona.bbvacryptowallet.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.abeltarazona.bbvacryptowallet.*
import com.abeltarazona.bbvacryptowallet.adapters.InversionAdapter
import com.abeltarazona.bbvacryptowallet.databinding.ActivityMainBinding
import com.abeltarazona.bbvacryptowallet.models.Inversion
import com.abeltarazona.bbvacryptowallet.models.balance.Balance
import com.abeltarazona.bbvacryptowallet.ui.comprar.ComprarActivity
import com.abeltarazona.bbvacryptowallet.utils.CommonFunctions
import com.abeltarazona.bbvacryptowallet.utils.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CommonFunctions {

    lateinit var binding: ActivityMainBinding

    private val marketAdapter = MarketPriceAdapter()

    private val inversionAdapter = InversionAdapter()

    private var isTokenBBVAShowing = false
    private var isMetaShowing = false

    private val viewModel: MainViewModel by viewModels()

    private var token = 0

    private var bitcoins = 0.0

    private var soles = 0.0

    private var saldo = 0.0

    private var list = mutableListOf<Inversion>()

    @Inject
    lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservers()

        binding.rvMarketPrice.adapter = marketAdapter

        marketAdapter.submitList(getCryptoMarketList())

        binding.rvInversiones.adapter = inversionAdapter

        binding.btnChange.setOnClickListener {
            isTokenBBVAShowing = !isTokenBBVAShowing
            if (isTokenBBVAShowing) {
                binding.title.text = "TOKEN BBVA"
                binding.tvAmountBitcoin.text = "$token BBVA"
                binding.tvAmountSoles.text = "Canjear"
                binding.iconBalanceActual.setImageResource(R.drawable.icon_bbva)
            } else {
                binding.title.text = "BALANCE ACTUAL"
                binding.tvAmountBitcoin.text = "${String.format("%.5f", bitcoins)} BTC"
                binding.tvAmountSoles.text = "S/ ${String.format("%.2f", soles)}"
                binding.iconBalanceActual.setImageResource(R.drawable.bitcoin)
            }
        }

        binding.view.setOnClickListener {
            startActivity(Intent(this, OpenTokenActivity::class.java))
        }

        binding.view5.setOnClickListener {
            val intent = Intent(this, BeneficiosActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
        }

        binding.view3.setOnClickListener {
            val intent = Intent(this, ComprarActivity::class.java)
            intent.putExtra("saldo", saldo)
            startActivity(intent)
        }

        binding.btnOpenMeta.setOnClickListener {
            isMetaShowing = !isMetaShowing
            if (isMetaShowing) {
                binding.btnOpenMeta.setImageResource(R.drawable.ic_baseline_expand_less_24)
                binding.constraintLayout.visibility = View.VISIBLE
            } else {
                binding.btnOpenMeta.setImageResource(R.drawable.ic_baseline_expand_more_24)
                binding.constraintLayout.visibility = View.GONE
            }
        }

        binding.swpe.setOnRefreshListener {
            load()
        }

        binding.btnComprar.setOnClickListener {

            val intent = Intent(this, ComprarActivity::class.java)
            intent.putExtra("saldo", saldo)
            startActivity(intent)
        }

        load()
    }

    private fun load() {
        sharedPref.getUserID()?.let {
            viewModel.getBalance(it)
        }
    }

    override fun initObservers() {
        viewModel.eventShowMessage.observe(this, {
            it?.run {
                binding.swpe.isRefreshing = false
                Toast.makeText(baseContext, this, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.eventShowLoading.observe(this, {
            it?.run {
                if (this) {
                    binding.progressBar6.visibility = View.VISIBLE
                } else {
                    binding.swpe.isRefreshing = false
                    binding.progressBar6.visibility = View.GONE
                }
            }
        })

        viewModel.balance.observe(this, {
            it?.run {
                token = this.tokens
                bitcoins = calculateBitcoin(this)
                soles = bitcoins * BITCOIN_VALUE * DOLAR_SOLES
                saldo = this.saldoSoles
                list = generateListInversiones(this)

                inversionAdapter.submitList(null)
                inversionAdapter.submitList(list)

                if (list.size == 0) {
                    binding.msgInvers.visibility = View.VISIBLE
                } else {
                    binding.msgInvers.visibility = View.GONE
                }

                // Show Home Screen
                if (isTokenBBVAShowing) {
                    binding.tvAmountBitcoin.text = "$token BBVA"
                    binding.tvAmountSoles.text = "Canjear"
                } else {
                    binding.tvAmountBitcoin.text = "${String.format("%.5f", bitcoins)} BTC"
                    binding.tvAmountSoles.text = "S/ ${String.format("%.2f", soles)}"
                }

            }
        })
    }

    private fun calculateBitcoin(balance: Balance): Double {
        val bbtc = balance.bbtc
        val beth = balance.beth
        val bbva = balance.bbva
        val ada = balance.ada
        val cod = balance.cod

        val bbtv_bitcoin = bbtc
        val beth_bitcoin = (beth * ETHEREUM_VALUE) / BITCOIN_VALUE
        val bbva_bitcoin = (bbva * 3.95) / BITCOIN_VALUE
        val ada_bitcoin = (ada * CARDANO_VALUE) / BITCOIN_VALUE
        val cod_bitcoin = (cod * 2.56) / BITCOIN_VALUE

        return (bbtv_bitcoin + beth_bitcoin + bbva_bitcoin + ada_bitcoin + cod_bitcoin)
    }

    private fun generateListInversiones(balance: Balance): MutableList<Inversion> {
        val bbtc = balance.bbtc
        val beth = balance.beth
        val bbva = balance.bbva
        val ada = balance.ada
        val cod = balance.cod

        val list = mutableListOf<Inversion>()

        if (bbtc != 0.0) {
            val inversionBBTC = Inversion(
                img = "${imageIcon}1.png",
                nameLarge = "BBVA Bitcoin",
                nameShort = "BBTC",
                amountCripto = String.format("%.5f", bbtc),
                amountSoles = String.format("%.2f", ((bbtc * BITCOIN_VALUE) * DOLAR_SOLES))
            )
            list.add(inversionBBTC)
        }
        if (beth != 0.0) {
            val inversionBETH = Inversion(
                img = "${imageIcon}1027.png",
                nameLarge = "BBVA Ethereum",
                nameShort = "BETH",
                amountCripto = String.format("%.5f", beth),
                amountSoles = String.format("%.2f", ((beth * ETHEREUM_VALUE) * DOLAR_SOLES))
            )
            list.add(inversionBETH)
        }
        if (bbva != 0.0) {
            val inversionBBVA = Inversion(
                img = "https://i.imgur.com/qfJwCEp.png",
                nameLarge = "BBVA Coin",
                nameShort = "BBVA",
                amountCripto = String.format("%.5f", bbva),
                amountSoles = String.format("%.2f", ((bbva * 3.95) * DOLAR_SOLES))
            )
            list.add(inversionBBVA)
        }
        if (ada != 0.0) {
            val inversionADA = Inversion(
                img = "${imageIcon}2010.png",
                nameLarge = "Cardano",
                nameShort = "ADA",
                amountCripto = String.format("%.5f", ada),
                amountSoles = String.format("%.2f", ((ada * CARDANO_VALUE) * DOLAR_SOLES))
            )
            list.add(inversionADA)
        }
        if (cod != 0.0) {
            val inversionCod = Inversion(
                img = "https://i.imgur.com/Ff9AIBr.png",
                nameLarge = "Coditec",
                nameShort = "COD",
                amountCripto = String.format("%.5f", cod),
                amountSoles = String.format("%.2f", ((cod * 2.56) * DOLAR_SOLES))
            )
            list.add(inversionCod)
        }

        return list
    }
}