package com.abeltarazona.bbvacryptowallet.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abeltarazona.bbvacryptowallet.databinding.ActivityOpenTokenBinding

class OpenTokenActivity : AppCompatActivity() {

    lateinit var binding: ActivityOpenTokenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpenTokenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button4.setOnClickListener {
            startActivity(Intent(this, DetailOTActivity::class.java))
        }
    }
}