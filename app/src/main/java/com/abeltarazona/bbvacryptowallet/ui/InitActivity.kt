package com.abeltarazona.bbvacryptowallet.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abeltarazona.bbvacryptowallet.databinding.ActivityInitBinding
import com.abeltarazona.bbvacryptowallet.ui.creation.CreationActivity

class InitActivity : AppCompatActivity() {

    lateinit var binding: ActivityInitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            startActivity(Intent(this, CreationActivity::class.java))
        }
    }
}