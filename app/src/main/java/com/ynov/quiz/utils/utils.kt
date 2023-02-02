package com.ynov.quiz.utils

import android.app.Activity
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import com.ynov.quiz.R


class ThemeManager {

    companion object {

        fun setDarkTheme(activity: Activity) {
            activity.setTheme(R.style.DarkTheme)
        }

        fun setLightTheme(activity: Activity) {
            activity.setTheme(R.style.LightTheme)

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
                activity.setTheme(R.style.LightTheme)

            }

        }
    }
}