package com.example.calculatorilona

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatorilona.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val listOfOperations = mutableListOf<String>()
    private val listOfNumbers = mutableListOf<Float>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.plusButton.setOnClickListenerWithAddingNumberToList()
        binding.minusButton.setOnClickListenerWithAddingNumberToList()
        binding.multiplyButton.setOnClickListenerWithAddingNumberToList()
        binding.divideButton.setOnClickListenerWithAddingNumberToList()

        binding.clearButton.setOnClickListener {
            listOfOperations.clear()
            listOfNumbers.clear()
            binding.numbersTextView.text = ""
        }

        binding.calculateButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            var result = ""

            if (binding.numberEditText.isNotEmpty()) {
                val lastNumber = binding.numberEditText.text.toString().toFloat()

                for (i in listOfNumbers.indices) {
                    result += "${listOfNumbers[i]}${listOfOperations[i]}"
                }
                result += "$lastNumber"

                intent.putExtra(ResultActivity.RESULT_STRING_KEY, result)
                startActivity(intent)
            } else {
                Toast.makeText(this@MainActivity, "Enter the last number!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun EditText.isNotEmpty(): Boolean = text.toString().isNotEmpty()

    private fun Button.setOnClickListenerWithAddingNumberToList() {
        this.setOnClickListener {
            if (binding.numberEditText.isNotEmpty()) {

                val operator = this.text.toString()
                val number = binding.numberEditText.text.toString().toFloat()
                listOfOperations.add(operator)
                listOfNumbers.add(number)
                binding.numbersTextView.text = "${binding.numbersTextView.text} $number $operator"
                binding.numberEditText.setText("")
            }
            else {
                Toast.makeText(this@MainActivity, "Write a number!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}