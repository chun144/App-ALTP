package com.chun.ailatrieuphu1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.chun.ailatrieuphu1.R
import com.chun.ailatrieuphu1.fragment.FirstFragment
import com.chun.ailatrieuphu1.fragment.HighScoreFragment
import com.chun.ailatrieuphu1.fragment.PlayFragment
import com.chun.ailatrieuphu1.fragment.SecondFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.content, FirstFragment(), FirstFragment::class.java.name)
            .commit()
    }

    fun openSecondFragment(level: Int?, refresh: Boolean?, help5050: Boolean?, audience: Boolean?, call: Boolean?) {
        val secondFragment = SecondFragment()
        if (level != null && refresh != null && help5050 != null && audience != null && call != null) {
            val bundle = Bundle()
            bundle.putInt("level", level)
            bundle.putBoolean("refresh", refresh)
            bundle.putBoolean("help5050", help5050)
            bundle.putBoolean("audience", audience)
            bundle.putBoolean("call", call)
            //đưa bundle vào fragment
            secondFragment.arguments = bundle
        }

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.open_to_left, R.anim.exit_to_left,
                R.anim.open_to_right, R.anim.exit_to_right)
            .replace(R.id.content, secondFragment, SecondFragment::class.java.name)
            .addToBackStack(null).commit()
    }

    fun openPlayFragment(level: Int?, refresh: Boolean?, help5050: Boolean?, audience: Boolean?, call: Boolean?) {
        val playFragment = PlayFragment()
        if (level != null && refresh != null && help5050 != null && audience != null && call != null) {
            val bundle = Bundle()
            bundle.putInt("level", level)
            bundle.putBoolean("refresh", refresh)
            bundle.putBoolean("help5050", help5050)
            bundle.putBoolean("audience", audience)
            bundle.putBoolean("call", call)
            playFragment.arguments = bundle
        }

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.open_to_left, R.anim.exit_to_left,
                R.anim.open_to_right, R.anim.exit_to_right)
            .replace(R.id.content, playFragment, PlayFragment::class.java.name)
            .addToBackStack(null).commit()
    }

    fun openFirstFragment() {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.open_to_left, R.anim.exit_to_left,
                R.anim.open_to_right, R.anim.exit_to_right)
            .replace(R.id.content, FirstFragment(), FirstFragment::class.java.name)
            .addToBackStack(null).commit()
    }

    fun openHighScoreFragment() {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.open_to_left, R.anim.exit_to_left,
                R.anim.open_to_right, R.anim.exit_to_right)
            .replace(R.id.content, HighScoreFragment(), HighScoreFragment::class.java.name)
            .addToBackStack(null).commit()
    }

}