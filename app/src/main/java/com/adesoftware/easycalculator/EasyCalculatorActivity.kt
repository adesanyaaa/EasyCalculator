package com.adesoftware.easycalculator

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.mariuszgromada.math.mxparser.Expression


class EasyCalculatorActivity : AppCompatActivity() {

    private lateinit var previousCalculation: TextView
    private lateinit var display: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_calculator)

        previousCalculation = findViewById(R.id.previous_cal_view)
        display = findViewById(R.id.display_edit_text)

        display.showSoftInputOnFocus = false
    }

    private fun updateText(stringToAdd: String) {
        val oldString: String = display.text.toString()

        val cursorPosition: Int = display.selectionStart
        val leftString: String = oldString.substring(0, cursorPosition)
        val rightString: String = oldString.substring(cursorPosition)


        display.setText(String.format("%s%s%s", leftString, stringToAdd, rightString))
        display.setSelection(cursorPosition + stringToAdd.length)
    }

    fun zeroBtnPush(view: View) {
        updateText(resources.getString(R.string.zeroText))
    }

    fun oneBtnPush(view: View) {
        updateText(resources.getString(R.string.oneText))
    }

    fun twoBtnPush(view: View) {
        updateText(resources.getString(R.string.twoText))
    }

    fun threeBtnPush(view: View) {
        updateText(resources.getString(R.string.threeText))
    }

    fun fourBtnPush(view: View) {
        updateText(resources.getString(R.string.fourText))
    }

    fun fiveBtnPush(view: View) {
        updateText(resources.getString(R.string.fiveText))
    }

    fun sixBtnPush(view: View) {
        updateText(resources.getString(R.string.sixText))
    }

    fun sevenBtnPush(view: View) {
        updateText(resources.getString(R.string.sevenText))
    }

    fun eightBtnPush(view: View) {
        updateText(resources.getString(R.string.eightText))
    }

    fun nineBtnPush(view: View) {
        updateText(resources.getString(R.string.nineText))
    }

    fun decimalBtnPush(view: View) {
        updateText(resources.getString(R.string.decimalText))
    }

    fun addBtnPush(view: View) {
        updateText(resources.getString(R.string.addText))
    }

    fun subtractBtnPush(view: View) {
        updateText(resources.getString(R.string.subtractText))
    }

    fun multiplyBtnPush(view: View) {
        updateText(resources.getString(R.string.multiplyText))
    }

    fun divideBtnPush(view: View) {
        updateText(resources.getString(R.string.divideText))
    }

    fun openParentBtnPush(view: View) {
        updateText(resources.getString(R.string.parenthesesOpenText))
    }

    fun closeParentBtnPush(view: View) {
        updateText(resources.getString(R.string.parenthesesCloseText))
    }

    fun clearBtnPush(view: View) {
        display.setText("")
    }

    fun equalsBtnPush(view: View) {
        val userExpression: String = display.text.toString()

        val expression: Expression = Expression(userExpression)
        val result: String = expression.calculate().toString()
        display.setText(result)
        display.setSelection(result.length)
    }

    fun backspaceBtnPush(view: View) {
        val cursorPosition: Int = display.selectionStart
        val textLength: Int = display.text.length
        if (cursorPosition != 0 && textLength != 0) {
            val selection: SpannableStringBuilder = display.text as SpannableStringBuilder
            selection.replace(cursorPosition-1, cursorPosition, "")
            display.text = selection
            display.setSelection(cursorPosition-1)
        }
    }
}