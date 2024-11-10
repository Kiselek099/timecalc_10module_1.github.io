package com.example.timecalculator
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var firstOperandET: EditText
    private lateinit var secondOperandET: EditText
    private lateinit var plusBTN: Button
    private lateinit var difBTN: Button
    private lateinit var textResultTV: TextView
    private lateinit var toolbarMain: Toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.resetMenuMain -> {
                firstOperandET.text.clear()
                secondOperandET.text.clear()
                textResultTV.text = "Результат"
                textResultTV.setTextColor(getColor(R.color.black))
                val toast = Toast.makeText(
                    applicationContext,
                    "Данные очищены",
                    Toast.LENGTH_LONG
                ).show()
            }
            R.id.exitMenuMain -> {
                val toast = Toast.makeText(
                    applicationContext,
                    "Приложение закрыто",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        toolbarMain.subtitle = "prod.by Slavik228"
        title = "Time Calculator"
        toolbarMain.setLogo(R.drawable.baseline_calculate_24)
        firstOperandET = findViewById(R.id.firstOperandET)
        secondOperandET = findViewById(R.id.secondOperandET)
        plusBTN = findViewById(R.id.plusBTN)
        difBTN = findViewById(R.id.difBTN)
        textResultTV = findViewById(R.id.textResultTV)
        plusBTN.setOnClickListener(this)
        difBTN.setOnClickListener(this)

    }
    override fun onClick(v: View?) {
        if (firstOperandET.text.isEmpty() || secondOperandET.text.isEmpty()) return
        var timeOne = firstOperandET.text.toString()
        var timeTwo = secondOperandET.text.toString()
        var result = when (v?.id) {
            R.id.plusBTN -> Operations(timeOne, timeTwo).addTime()
            R.id.difBTN -> Operations(timeOne, timeTwo).subtractTime()
            else -> ""
        }
        if (result.isNotEmpty()) {
            textResultTV.text = result
            textResultTV.setTextColor(getColor(R.color.red ))
            Toast.makeText(
                applicationContext,
                "Результат: $result",
                Toast.LENGTH_LONG).show()
        }
    }
}


