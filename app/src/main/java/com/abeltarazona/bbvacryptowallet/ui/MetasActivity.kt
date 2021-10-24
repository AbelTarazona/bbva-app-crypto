package com.abeltarazona.bbvacryptowallet.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abeltarazona.bbvacryptowallet.MetasAdapter
import com.abeltarazona.bbvacryptowallet.databinding.ActivityMetasBinding
import com.abeltarazona.bbvacryptowallet.getMetasList

class MetasActivity : AppCompatActivity() {

    lateinit var binding: ActivityMetasBinding

    private val adapter = MetasAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMetasBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button2.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.rv.adapter = adapter
        adapter.submitList(getMetasList())

    }
}