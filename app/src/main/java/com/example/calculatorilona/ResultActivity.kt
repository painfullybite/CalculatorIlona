package com.example.calculatorilona

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.calculatorilona.databinding.ActivityResultBinding
import net.objecthunter.exp4j.ExpressionBuilder

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    companion object {
        const val RESULT_STRING_KEY = "RESULT_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)

        setContentView(binding.root)

        intent.extras?.getString(RESULT_STRING_KEY)?.apply {

            try {
                binding.resultTextView.text = ExpressionBuilder(this).build().evaluate().toString()
            } catch (e: Exception) {
                binding.resultTextView.text = "Unable to calculate!"
                Log.d("lololo", this)
            }

        }


    }
}