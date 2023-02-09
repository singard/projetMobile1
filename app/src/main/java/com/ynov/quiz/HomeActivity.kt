package com.ynov.quiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

import com.google.android.material.navigation.NavigationView
import com.ynov.quiz.utils.Theme
import com.ynov.quiz.utils.ThemeManager

class HomeActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var begin_btn: Button
    private val classeName : String = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeManager.themeSelect(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = "Quiz OM"
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation)
        begin_btn = findViewById(R.id.begin_btn)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        begin_btn.setOnClickListener {
            Toast.makeText(this, resources.getString(R.string.toast_message), Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        navigationView.setNavigationItemSelectedListener {menuItem ->
            if(menuItem.itemId == R.id.informations_item) {

            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
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
    override fun onDestroy() {
        super.onDestroy()
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        if ( drawerLayout.isOpen()) {
            drawerLayout.close()
        }
    }
}