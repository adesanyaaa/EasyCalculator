package com.adesoftware.easycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

class EasyCalculatorActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var resultTv: TextView
    private lateinit var solutionTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_calculator)
        resultTv = findViewById(R.id.tv_result)
        solutionTv = findViewById(R.id.tv_solution)

        val buttonC = MaterialButton(this)
        assignId(buttonC, R.id.button_c)

        val buttonOpenBracket = MaterialButton(this)
        assignId(buttonOpenBracket, R.id.button_open_bracket)

        val buttonCloseBracket = MaterialButton(this)
        assignId(buttonCloseBracket, R.id.button_close_bracket)

       val buttonDivide = MaterialButton(this)
        assignId(buttonDivide, R.id.button_divide)

        val buttonMultiply = MaterialButton(this)
        assignId(buttonMultiply, R.id.button_multiply)

        val buttonPlus = MaterialButton(this)
        assignId(buttonPlus, R.id.button_plus)

        val buttonMinus = MaterialButton(this)
        assignId(buttonMinus, R.id.button_minus)

        val buttonEquals = MaterialButton(this)
        assignId(buttonEquals, R.id.button_equals)

        val button0 = MaterialButton(this)
        assignId(button0, R.id.button_0)

        val button1 = MaterialButton(this)
        assignId(button1, R.id.button_1)

        val button2 = MaterialButton(this)
        assignId(button2, R.id.button_2)

        val button3 = MaterialButton(this)
        assignId(button3, R.id.button_3)

        val button4 = MaterialButton(this)
        assignId(button4, R.id.button_4)

        val button5 = MaterialButton(this)
        assignId(button5, R.id.button_5)

        val button6 = MaterialButton(this)
        assignId(button6, R.id.button_6)

        val button7 = MaterialButton(this)
        assignId(button7, R.id.button_7)

        val button8 = MaterialButton(this)
        assignId(button8, R.id.button_8)

        val button9 = MaterialButton(this)
        assignId(button9, R.id.button_9)

        val buttonAC = MaterialButton(this)
        assignId(buttonAC, R.id.button_ac)

        val buttonDot = MaterialButton(this)
        assignId(buttonDot, R.id.button_dot)
    }

    private fun assignId(btn: MaterialButton, id: Int) {
        val btn: MaterialButton = findViewById(id)
        btn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val button: MaterialButton = view as MaterialButton
        val buttonText: String = button.text.toString()
        var dataToCalculate: String = solutionTv.text.toString()
        if (buttonText == "AC") {
            solutionTv.text = ""
            resultTv.text = "0"
            return
        }
        if (buttonText == "=") {
            solutionTv.text = resultTv.text
            return
        }
        if (buttonText == "C") {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length -1)
        } else {
            dataToCalculate += buttonText
        }
        solutionTv.text = dataToCalculate

        val finalResult: String = getResult(dataToCalculate)
        if (finalResult != "Error") {
            resultTv.text = finalResult
        }
    }

    private fun getResult(data: String) :String {
        try {
            val context: Context = Context.enter()
            context.optimizationLevel = -1
            var scriptable: Scriptable = context.initStandardObjects()
            var finalResult: String = context.evaluateString(scriptable, data,
                "Javascript", 1, null).toString()
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "")
            }
            return finalResult
        } catch (e: Exception) {
            e.printStackTrace()
            return "Error"
        }

    }
}