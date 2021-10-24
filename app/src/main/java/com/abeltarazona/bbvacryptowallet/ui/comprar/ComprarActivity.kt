package com.abeltarazona.bbvacryptowallet.ui.comprar

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.abeltarazona.bbvacryptowallet.Crypto
import com.abeltarazona.bbvacryptowallet.adapters.CryptoAdapter
import com.abeltarazona.bbvacryptowallet.databinding.ActivityComprarBinding
import com.abeltarazona.bbvacryptowallet.getCryptoMarketList
import com.abeltarazona.bbvacryptowallet.utils.CommonFunctions
import com.abeltarazona.bbvacryptowallet.utils.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ComprarActivity : AppCompatActivity(), CommonFunctions {

    lateinit var binding: ActivityComprarBinding

    private val viewModel: ComprarViewModel by viewModels()

    private val adapterCollect = CryptoAdapter() { getCrypto(it) }

    var cripto: Crypto? = null

    @Inject
    lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComprarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservers()

        binding.rvCryptoComprar.adapter = adapterCollect

        adapterCollect.submitList(getCryptoMarketList())

        binding.card.tvCardAvailableBalance.text =
            "S/ ${String.format("%.2f", intent.getDoubleExtra("saldo", 0.0))}"

        binding.amount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                p0?.let {
                    if (p0.isNotEmpty()) {
                        val amountSoles = p0.toString().toDouble()
                        cripto?.let {

                            val total = amountSoles / it.price

                            binding.textView33.text = String.format("%.5f", total)

                        }
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.btnComprar.setOnClickListener {

            val amountSoles = binding.amount.text.toString().toDouble()

            viewModel.transfer(
                sharedPref.getUserID() ?: "",
                binding.textView33.text.toString(),
                cripto?.nameLarge ?: "",
                (amountSoles + 0.6).toString()
            )
        }

        binding.imageButton.setOnClickListener {
            finish()
        }
    }

    override fun initObservers() {
        viewModel.eventShowMessage.observe(this, {

            it?.run {
                Toast.makeText(baseContext, this, Toast.LENGTH_SHORT).show()
            }

        })

        viewModel.eventShowLoading.observe(this, {
            it?.run {
                if (this) {
                    binding.progressBar5.visibility = View.VISIBLE
                } else {
                    binding.progressBar5.visibility = View.GONE
                }
            }
        })

        viewModel.eventShowSuccess.observe(this, {
            it?.run {
                if (this) {
                    Toast.makeText(baseContext, "Compra exitosa", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    finish()
                }
            }
        })

    }


    private fun getCrypto(cript: Crypto) {
        cripto = cript
        binding.valueCrypto.text = "≡ S/ ${String.format("%.2f", cript.price)} "
        binding.cripname.text = cript.name
        calculateCriptos()
    }

    private fun calculateCriptos() {

        if (binding.amount.text.isNotEmpty()) {
            val amountSoles = binding.amount.text.toString().toDouble()
            cripto?.let {
                val total = amountSoles / it.price
                binding.textView33.text = String.format("%.5f", total)
            }
        }


    }
}