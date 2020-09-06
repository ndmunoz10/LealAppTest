package com.nicdamun.lealapp.common

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nicdamun.lealapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(resources.configuration, shouldRecreate = false)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setTheme(newConfig)
    }

    private fun setTheme(config: Configuration, shouldRecreate: Boolean = true) {
        when(config.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> setTheme(R.style.LightMode)
            Configuration.UI_MODE_NIGHT_YES -> setTheme(R.style.DarkMode)
        }
        if (shouldRecreate) recreate()
    }
}