package com.ynov.quiz

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider

class SecondActivity: AppCompatActivity() {

    private lateinit var quizzFirstname: TextView
    private lateinit var resultYear: EditText
    private lateinit var resultNumberChamp: Slider
    private lateinit var validateBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        quizzFirstname = findViewById<TextView>(R.id.quizz_firstname)
        validateBtn = findViewById<Button>(R.id.validate_quizz)
        resultYear = findViewById<EditText>(R.id.result_year)
        resultNumberChamp = findViewById<Slider>(R.id.result_number_champ)

        val bundle: Bundle? = intent.extras

        val firstname = bundle!!.getString("firstname")

        quizzFirstname.text = "Bienvenue $firstname"
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

            Log.d("ICI", numberOfWinChamp.toString())

            if (numberOfWinChamp.compareTo(9.0) == 0) {
                numberOfGoodAnswer += 1;
            }

            intent.putExtra("goodAnswers", numberOfGoodAnswer.toString())

            intent.putExtra("firstname", firstname.toString())

            startActivity(intent)
        }
    }
}