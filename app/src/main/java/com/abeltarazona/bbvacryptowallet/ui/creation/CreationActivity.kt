package com.abeltarazona.bbvacryptowallet.ui.creation

import android.content.Intent
import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.abeltarazona.bbvacryptowallet.R
import com.abeltarazona.bbvacryptowallet.databinding.ActivityCreationBinding
import com.abeltarazona.bbvacryptowallet.getListUsers
import com.abeltarazona.bbvacryptowallet.ui.MetasActivity
import com.abeltarazona.bbvacryptowallet.utils.CommonFunctions
import com.abeltarazona.bbvacryptowallet.utils.SharedPref
import com.google.zxing.WriterException
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CreationActivity : AppCompatActivity(), CommonFunctions {

    lateinit var binding: ActivityCreationBinding

    private val viewModel: CreationViewModel by viewModels()

    @Inject
    lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservers()

        initViews()

        Handler().postDelayed({
            binding.progressBar.visibility = View.GONE
            binding.imageView2.visibility = View.VISIBLE
            binding.textView7.visibility = View.VISIBLE
            binding.button2.isEnabled = true
        }, 5000)

        load()
    }

    private fun load() {
        viewModel.createWallet(getListUsers()[0].toString())
    }

    override fun initViews() {
        binding.button2.setOnClickListener {
            startActivity(Intent(this, MetasActivity::class.java))
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
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        })

        viewModel.eventShowSuccess.observe(this, {
            it?.run {
                if (this) {
                    binding.imageView2.visibility = View.VISIBLE
                    binding.textView7.visibility = View.VISIBLE
                    binding.button2.isEnabled = true
                } else {
                    finish()
                }
            }
        })

        viewModel.wallet.observe(this, {
            it?.run {
                binding.textView7.text = this.usuario
                sharedPref.setUserID(this.usuario)
                generateQR(this.public_key)
            }
        })
    }

    private fun generateQR(hash: String) {

        val manager = getSystemService(WINDOW_SERVICE) as WindowManager
        val display = manager.defaultDisplay
        val point = Point()
        display.getSize(point)
        val width: Int = point.x
        val height: Int = point.y
        var smallerDimension = if (width < height) width else height
        smallerDimension = smallerDimension * 3 / 4

        val qrgEncoder = QRGEncoder(hash, null, QRGContents.Type.TEXT, smallerDimension)
        qrgEncoder.colorBlack = resources.getColor(R.color.blue)
        qrgEncoder.colorWhite = Color.WHITE
        try {
            // Setting Bitmap to ImageView
            binding.imageView2.setImageBitmap(qrgEncoder.bitmap)
        } catch (e: WriterException) {
            Log.v("Abelcillo", e.toString())
        }

    }


}