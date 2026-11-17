package com.example.kurdukaa_01_02

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var imgFigure: ImageView
    lateinit var spinnerFigure: Spinner
    lateinit var etData: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        imgFigure = findViewById(R.id.imgFigure)
        spinnerFigure = findViewById(R.id.spinnerFigure)
        etData = findViewById(R.id.etData)

        val figures = arrayOf("Выберите фигуру", "Треугольник", "Круг")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, figures)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFigure.adapter = adapter
        spinnerFigure.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        etData.setText("")

        if (position == 1) {
            imgFigure.setImageResource(R.drawable.triangle)
            etData.hint = "Введите a и b через пробел"
        } else if (position == 2) {
            imgFigure.setImageResource(R.drawable.circle)
            etData.hint = "Введите периметр P"
        } else {
            imgFigure.setImageDrawable(null)
            etData.hint = "Введите данные для расчета"
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    fun onCalcClick(view: View) {
        val position = spinnerFigure.selectedItemPosition

        if (position == 0) {
            showMessage("Ошибка", "Выберите фигуру")
            return
        }

        val text = etData.text.toString().trim().replace(',', '.')

        if (text == "") {
            showMessage("Ошибка", "Введите данные для расчета")
            return
        }

        if (position == 1) {
            calcTriangle(text)
        } else {
            calcCircle(text)
        }
    }

    fun calcTriangle(text: String) {
        val parts = text.split(" ")

        if (parts.size != 2) {
            showMessage("Ошибка", "Введите два числа a и b через пробел")
            return
        }

        val a = parts[0].toDoubleOrNull()
        val b = parts[1].toDoubleOrNull()

        if (a == null || b == null || a <= 0 || b <= 0) {
            showMessage("Ошибка", "a и b должны быть положительными числами")
            return
        }

        val p = 2 * a + b
        showMessage("Результат", "Периметр P = 2a + b = " + p)
    }

    fun calcCircle(text: String) {
        val p = text.toDoubleOrNull()

        if (p == null || p <= 0) {
            showMessage("Ошибка", "P должно быть положительным числом")
            return
        }

        val r = p / (2 * Math.PI)
        val rounded = Math.round(r * 1000) / 1000.0
        showMessage("Результат", "Радиус R = P / 2π = " + rounded)
    }

    fun showMessage(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)
        builder.show()
    }
}