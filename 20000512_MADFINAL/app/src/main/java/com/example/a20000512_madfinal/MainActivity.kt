package com.example.a20000512_madfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

//Main Activity class
class MainActivity : AppCompatActivity() {

    //Variables to store the current operator,operand and the text view
    private lateinit var resultTextView: TextView
    private var currentOperator: String? = null
    private var currentOperand: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultTextView = findViewById(R.id.resultTextView)

        //Get references to all the number buttons
        val numberButtons = listOf<Button>(
            findViewById(R.id.button0),
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8),
            findViewById(R.id.button9),
            findViewById(R.id.buttonDot),
        )

        //Setting onclick listeners for number buttons
        numberButtons.forEach { button ->
            button.setOnClickListener {
                val currentText = resultTextView.text.toString()
                val buttonText = button.text.toString()
                if(currentText =="0"){
                    resultTextView.text = buttonText
                    return@setOnClickListener
                }
                if(currentText == "NaN"){
                    resultTextView.text = buttonText
                    return@setOnClickListener
                }
                resultTextView.text = currentText + buttonText
            }
        }

        // Get references to all the operator buttons
        val operatorButtons = listOf<Button>(
            findViewById(R.id.buttonAdd),
            findViewById(R.id.buttonSubtract),
            findViewById(R.id.buttonMultiply),
            findViewById(R.id.buttonDivide)
        )
        // Set onClick listeners for operator buttons
        operatorButtons.forEach { button ->
            button.setOnClickListener {
                currentOperator = button.text.toString()
                currentOperand = resultTextView.text.toString().toDoubleOrNull() ?: 0.0
                resultTextView.text = ""
            }
        }

        // Set onClick listener for the equals button
        val equalsButton = findViewById<Button>(R.id.buttonEquals)
        equalsButton.setOnClickListener {
            val inputOperand = resultTextView.text.toString().toDoubleOrNull() ?: 0.0
            if(currentOperator == null){
                return@setOnClickListener
            }
            val result = performCalculation(currentOperand, inputOperand, currentOperator)
            resultTextView.text = result.toString()
            currentOperator = null
            currentOperand = result
        }
        // Set onClick listener for the clear button
        val clearButton = findViewById<Button>(R.id.buttonClear)
        clearButton.setOnClickListener {
            resultTextView.text = "0"
            currentOperator = null
            currentOperand = 0.0
        }
    }
    private fun performCalculation(operand1: Double, operand2: Double, operator: String?): Double {
        return when (operator) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "×" -> operand1 * operand2
            "÷" -> if (operand2 != 0.0) operand1 / operand2 else Double.NaN
            else -> Double.NaN
        }
    }
}