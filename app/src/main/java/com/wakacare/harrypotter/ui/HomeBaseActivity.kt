package com.wakacare.harrypotter.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.wakacare.harrypotter.R

class HomeBaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_base)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        try {
            super.onActivityResult(requestCode, resultCode, data)
            if (getForegroundFragment() != null) {
                getForegroundFragment()!!.onActivityResult(requestCode, resultCode, data)
            }
        } catch (e: Exception) {
           throw e
        }
    }

    private fun getForegroundFragment(): Fragment? {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.base)
        return navHostFragment?.childFragmentManager?.fragments?.get(0)
    }
}