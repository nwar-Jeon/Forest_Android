package com.nwar.dsm.deanomoo_dsm.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.nwar.dsm.deanomoo_dsm.R

class Splash : AppCompatActivity() {

    private val SPLASH_DELAY : Long = 2000
    private var handler : Handler? = null
    internal val runnable = Runnable {
        if(!isFinishing){
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler = Handler()
        handler!!.postDelayed(runnable,SPLASH_DELAY)
    }

    override fun onDestroy() {
        if(handler != null){
            handler!!.removeCallbacks(runnable)
        }
        super.onDestroy()
    }
}
