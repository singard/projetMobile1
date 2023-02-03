package com.ynov.quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.ynov.quiz.databinding.ActivityMainBinding
import android.app.DatePickerDialog;
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.ynov.quiz.utils.ThemeManager
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
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeManager.themeSelect(this)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = "Inscription"
        setSupportActionBar(toolbar)

        dateButton = findViewById<Button>(R.id.datepicker_birthday)
        sexeSpinner = findViewById<Spinner>(R.id.sexe_spinner)
        validateBtn = findViewById<Button>(R.id.validate_form)
        firstnameTF = findViewById<EditText>(R.id.firstname)
        lastnameTF = findViewById<EditText>(R.id.lastname)
        phoneTF = findViewById<EditText>(R.id.phone_number)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        navigationView = findViewById<NavigationView>(R.id.navigation)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(myCalendar)
        }

        val options = arrayOf("Homme", "Femme", "Non binaire")

        var sexe = options[0];

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
                sexe = options[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        validateBtn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("firstname", firstnameTF.text.toString())
            intent.putExtra("lastname", lastnameTF.text.toString())
            intent.putExtra("phone", phoneTF.text.toString())
            intent.putExtra("sexe", sexe.toString())

            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_light->{
                // TODO: Light Mode
                true
            }
            R.id.action_night->{
                // TODO: Dark Mode
                true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateLabel(myCalendar: Calendar) {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.FRANCE)
        dateButton.setText(sdf.format(myCalendar.time))
    }
}