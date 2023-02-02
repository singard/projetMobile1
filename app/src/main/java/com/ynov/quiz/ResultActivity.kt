package com.ynov.quiz

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ResultActivity: AppCompatActivity() {

    private lateinit var resultLabel: TextView
    private lateinit var validateBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = "Résultats"
        setSupportActionBar(toolbar)

        resultLabel = findViewById<TextView>(R.id.result_label)
        validateBtn = findViewById<Button>(R.id.restart)

        val bundle: Bundle? = intent.extras
        val goodAnswers = bundle!!.getString("goodAnswers")
        val firstname = bundle!!.getString("firstname")

        if (goodAnswers!!.equals("0")) {
            resultLabel.text = "Vous n'avez aucune bonne réponse, réessayez !"
        } else if (goodAnswers!!.equals("1")) {
            resultLabel.text = "Vous avez une bonne réponse, réessayez pour trouver la deuxième"
        } else {
            resultLabel.text = "Bravo ! Vous avez tout juste !"
        }

        validateBtn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)

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