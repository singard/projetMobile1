package com.ynov.quiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.ynov.quiz.utils.Theme
import com.ynov.quiz.utils.ThemeManager

class ResultActivity: AppCompatActivity() {

    private lateinit var resultLabel: TextView
    private lateinit var validateBtn: Button
    private val classeName : String = "ResultActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeManager.themeSelect(this)
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
            resultLabel.text = R.string.text_result_0.toString()
        } else if (goodAnswers!!.equals("1")) {
            resultLabel.text = R.string.text_result_1.toString()
        } else {
            resultLabel.text = R.string.text_result_2.toString()
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