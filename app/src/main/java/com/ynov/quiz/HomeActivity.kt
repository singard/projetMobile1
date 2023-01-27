package com.ynov.quiz

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class HomeActivity : AppCompatActivity(){

    private lateinit var homeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homeButton: Button = findViewById(R.id.homeButton)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = "Quiz OM"
        setSupportActionBar(toolbar)

        homeButton.setOnClickListener {
            Toast.makeText(this, resources.getString(R.string.toast_message), Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_favorite->{
                setTheme(R.style.Theme_QuizDevMobile);
                recreate();
                true
            }
            R.id.action_settings->{
                setTheme(R.style.Theme_QuizDevMobile);
                recreate();
                true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}