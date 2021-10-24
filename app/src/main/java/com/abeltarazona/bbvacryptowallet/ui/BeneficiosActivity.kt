package com.abeltarazona.bbvacryptowallet.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abeltarazona.bbvacryptowallet.CanjeAdapter
import com.abeltarazona.bbvacryptowallet.CollectAdapter
import com.abeltarazona.bbvacryptowallet.databinding.ActivityBeneficiosBinding
import com.abeltarazona.bbvacryptowallet.getCanjeList
import com.abeltarazona.bbvacryptowallet.getCollectList

class BeneficiosActivity : AppCompatActivity() {

    lateinit var binding: ActivityBeneficiosBinding

    private val adapterCollect = CollectAdapter()
    private val adapterCanje = CanjeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeneficiosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvCollect.adapter = adapterCollect
        binding.rvCollect2.adapter = adapterCanje

        adapterCollect.submitList(getCollectList())
        adapterCanje.submitList(getCanjeList())

        binding.crowd.setOnClickListener {
            startActivity(Intent(this, CrowfundingActivity::class.java))
        }

        binding.textView22.text = intent.getIntExtra("token", 0).toString()

    }
}