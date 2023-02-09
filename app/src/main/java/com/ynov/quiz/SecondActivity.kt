package com.ynov.quiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.slider.Slider
import com.ynov.quiz.utils.Theme
import com.ynov.quiz.utils.ThemeManager
import java.util.*

class SecondActivity: AppCompatActivity() {

    private lateinit var quizzFirstname: TextView
    private lateinit var resultYear: EditText
    private lateinit var resultNumberChamp: Slider
    private lateinit var validateBtn: Button
    private val classeName : String = "SecendActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeManager.themeSelect(this)
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
                Log.d(classeName, "action light select")
                ThemeManager.setTheme(this,this, Theme.LIGHT)
            }
            R.id.action_night->{
                Log.d(classeName, "action night select")
                ThemeManager.setTheme(this,this, Theme.DARK)
            }
            R.id.action_automatic->{
                Log.d(classeName, "action automatic select")
                ThemeManager.setTheme(this,this, Theme.AUTOMATIC)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}