package com.example.tipcalculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.textfield.TextInputEditText
import kotlin.math.round
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var bill : Float? = 0f
        var tipRate : Float = 0f
        var result : Float = 0f

        val billField : TextInputEditText = findViewById<TextInputEditText>(R.id.billInput)
        val dropDown : AutoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.tipRateDropDown)
        val resultTxt : TextView = findViewById<TextView>(R.id.resultTxt)
        var wantRoundSwitch : MaterialSwitch = findViewById<MaterialSwitch>(R.id.wantRoundSwitch)

        val items = listOf<TipRate>(
            TipRate(0.15f),
            TipRate(0.18f),
            TipRate(0.2f)
        )

        val adapter : ArrayAdapter<TipRate> = ArrayAdapter(
            this,
            R.layout.dropdown_item,
            items
        )

        billField.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(value: Editable?) {
                    bill = value.toString().toFloatOrNull() ?: 0f
                    result = bill * tipRate

                    if(wantRoundSwitch.isChecked)
                    {
                        result = round(result)
                    }

                    val str = getString(R.string.tip_amount, result)
                    resultTxt.setText(str)
                }
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            }
        )

        dropDown.setAdapter(adapter)
        dropDown.onItemClickListener = AdapterView.OnItemClickListener {parent, _, position, _ ->
            val tipRateObj : TipRate = parent.getItemAtPosition(position) as TipRate
            tipRate = tipRateObj.amount
            result = bill?.times(tipRate) ?: 0f

            if(wantRoundSwitch.isChecked)
            {
                result = round(result)
            }

            val str = getString(R.string.tip_amount, result)
            resultTxt.setText(str)
        }
    }
}