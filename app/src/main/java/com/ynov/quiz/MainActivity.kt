package com.ynov.quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.ynov.quiz.databinding.ActivityMainBinding
import android.app.DatePickerDialog;
import android.content.Intent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var firstnameTF: EditText
    private lateinit var lastnameTF: EditText
    private lateinit var phoneTF: EditText
    private lateinit var binding: ActivityMainBinding
    private lateinit var dateButton: Button
    private lateinit var sexeSpinner: Spinner
    private lateinit var validateBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dateButton = findViewById<Button>(R.id.datepicker_birthday)
        sexeSpinner = findViewById<Spinner>(R.id.sexe_spinner)
        validateBtn = findViewById<Button>(R.id.validate_form)
        firstnameTF = findViewById<EditText>(R.id.firstname)
        lastnameTF = findViewById<EditText>(R.id.lastname)
        phoneTF = findViewById<EditText>(R.id.phone_number)

        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(myCalendar)
        }

        val options = arrayOf("Homme", "Femme", "Non binaire")
        sexeSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)

        dateButton.setOnClickListener {
            DatePickerDialog(this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        sexeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                print("a")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                print("b")
            }

        }

        validateBtn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateLabel(myCalendar: Calendar) {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.FRANCE)
        dateButton.setText(sdf.format(myCalendar.time))
    }
}