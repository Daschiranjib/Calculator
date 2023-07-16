package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvinput: TextView? = null
    var lastdigit: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvinput = findViewById(R.id.tvinput)
    }
    fun onDigit(view: View)
    {
        tvinput?.append((view as Button).text)
        lastDot = false
        lastdigit = true
    }
    fun onClear(view: View)
    {
        tvinput?.text=""
    }
    fun onDecimalPoint(view: View)
    {
        if(lastdigit && !lastDot) {
            tvinput?.append(".")
            lastDot=false
            lastdigit=false
        }
    }
    fun onOperator(view: View)
    {
        tvinput?.text?.let {
            if ( lastdigit && !isOperatorAdded(it.toString())){
                tvinput?.append((view as Button).text)
                lastDot=false
                lastdigit=false
            }
        }
    }
    fun onEqual(view: View)
    {
        if(lastdigit)
        {
            var tvValue = tvinput?.text.toString()
            var prefix = ""
            try {
                if(tvValue.startsWith("-"))
                {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-"))
                {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }

                    tvinput?.text = removeZeroAfterDot( (one.toDouble()-two.toDouble()).toString())
                }
                else if (tvValue.contains("+"))
                {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }

                    tvinput?.text = removeZeroAfterDot( (one.toDouble()+two.toDouble()).toString())
                }else if (tvValue.contains("/"))
                {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }

                    tvinput?.text = removeZeroAfterDot( (one.toDouble()/two.toDouble()).toString())
                }else if (tvValue.contains("*"))
                {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }

                    tvinput?.text = removeZeroAfterDot( (one.toDouble()*two.toDouble()).toString())
                }

            }catch (e : ArithmeticException)
            {
                e.printStackTrace()
            }
        }

    }

    private fun removeZeroAfterDot(result : String) : String{
        var value = result
        if(result.contains(".0"))
            value = result.substring(0,result.length -2)

        return value
    }

    private fun isOperatorAdded(value : String) : Boolean{
        return if( value.startsWith("-"))
        {
            false
        }
        else{
            value.contains("/") ||
                    value.contains("*") ||
                    value.contains("+") ||
                    value.contains("-")
        }
    }
}


