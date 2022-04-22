package com.chun.ailatrieuphu1.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.chun.ailatrieuphu1.fragment.BaseFragment

open class BaseActivity : AppCompatActivity() {

    fun getCurrentFragment(): Fragment? {
        for (fragment in supportFragmentManager.fragments) {
            if (fragment != null && fragment.isVisible) {
                return fragment
            }
        }
        return null
    }

    fun onBackRoot() {
        super.onBackPressed()
    }

    fun onFinish() {
        finish()
    }

    override fun onBackPressed() {
        val fr = getCurrentFragment()
        if (fr != null && fr is BaseFragment) {
            fr.onBackPressForFragment()
        } else {
            onBackRoot()
        }
    }
}
