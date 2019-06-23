package com.example.karenzheng.flappycisstudent

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game.setOnClickListener{
            val i = Intent(this, GameActivity::class.java)
            startActivity(i)
        }

        board.setOnClickListener{
            val j = Intent(this, ScoreList::class.java)
            startActivity(j)
        }
    }
}
