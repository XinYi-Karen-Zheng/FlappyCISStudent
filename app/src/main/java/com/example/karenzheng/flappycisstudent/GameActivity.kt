package com.example.karenzheng.flappycisstudent

import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics

class GameActivity : AppCompatActivity() {

    var g : GameView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val h = displayMetrics.heightPixels
        val w = displayMetrics.widthPixels
        g = GameView(this, w.toFloat(), h.toFloat())
        setContentView(g)
    }
}