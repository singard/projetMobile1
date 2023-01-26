package com.ynov.quiz

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity: AppCompatActivity() {

    private lateinit var quizzFirstname: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        quizzFirstname = findViewById<TextView>(R.id.quizz_firstname)

        val bundle: Bundle? = intent.extras
        val firstname = bundle!!.getString("firstname")

        quizzFirstname.text = "Bienvenue $firstname"
    }
}