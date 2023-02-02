package com.ynov.quiz

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.slider.Slider
import java.util.*

class SecondActivity: AppCompatActivity() {

    private lateinit var quizzFirstname: TextView
    private lateinit var resultYear: EditText
    private lateinit var resultNumberChamp: Slider
    private lateinit var validateBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = "Quiz"
        setSupportActionBar(toolbar)

        quizzFirstname = findViewById<TextView>(R.id.quizz_firstname)
        validateBtn = findViewById<Button>(R.id.validate_quizz)
        resultYear = findViewById<EditText>(R.id.result_year)
        resultNumberChamp = findViewById<Slider>(R.id.result_number_champ)

        val bundle: Bundle? = intent.extras

        val firstname = bundle!!.getString("firstname")

        if(Locale.getDefault().displayLanguage == "en") {
            quizzFirstname.text = "Welcome $firstname"
        } else {
            quizzFirstname.text = "Bienvenue $firstname"
        }

        var numberOfWinChamp = resultNumberChamp.value

        resultNumberChamp.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                numberOfWinChamp = slider.value;
            }

            override fun onStopTrackingTouch(slider: Slider) {
                numberOfWinChamp = slider.value;
            }
        })

        validateBtn.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            var numberOfGoodAnswer = 0;

            if(resultYear.text.toString() == "1993") {
                numberOfGoodAnswer += 1;
            }

            if (numberOfWinChamp.compareTo(9.0) == 0) {
                numberOfGoodAnswer += 1;
            }

            intent.putExtra("goodAnswers", numberOfGoodAnswer.toString())

            intent.putExtra("firstname", firstname.toString())

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
}