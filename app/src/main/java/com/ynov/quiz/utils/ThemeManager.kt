package com.ynov.quiz.utils

import android.app.Activity
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import com.ynov.quiz.R


class ThemeManager {



    companion object {
        private var themeSelect = Theme.AUTOMATIC
        private val classeName : String = "ThemeManager"

        fun setTheme(activity: Activity,theme : Theme ){
            this.themeSelect = theme
            themeSelect(activity)
            activity.recreate();
        }

        fun themeSelect(activity: Activity){

            when (themeSelect) {
                Theme.LIGHT-> setLightTheme(activity)
                Theme.DARK -> setDarkTheme(activity)
                Theme.AUTOMATIC -> setAutomaticTheme(activity)
                else -> Log.i("classeName","not theme found")
            }

        }

        fun setDarkTheme(activity: Activity) {
            activity.setTheme(R.style.DarkTheme)
            Log.i(classeName,"dark theme install");
        }

        fun setLightTheme(activity: Activity) {
            activity.setTheme(R.style.LightTheme)
            Log.i(classeName,"light theme install");

        }

        fun setAutomaticTheme(activity: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val currentNightMode = activity.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
                when (currentNightMode) {
                    Configuration.UI_MODE_NIGHT_NO -> activity.setTheme(R.style.LightTheme)
                    Configuration.UI_MODE_NIGHT_YES -> activity.setTheme(R.style.DarkTheme)
                    Configuration.UI_MODE_NIGHT_UNDEFINED -> activity.setTheme(R.style.LightTheme)
                }
            } else {
                Log.i(classeName,"default theme");
                activity.setTheme(R.style.LightTheme)


            }

        }


    }
}