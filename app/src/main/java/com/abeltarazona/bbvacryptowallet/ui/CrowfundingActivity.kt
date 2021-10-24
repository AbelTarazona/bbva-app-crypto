package com.abeltarazona.bbvacryptowallet.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abeltarazona.bbvacryptowallet.databinding.ActivityBeneficiosBinding
import com.abeltarazona.bbvacryptowallet.databinding.ActivityCrowfundingBinding

class CrowfundingActivity : AppCompatActivity() {

    lateinit var binding: ActivityCrowfundingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrowfundingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}