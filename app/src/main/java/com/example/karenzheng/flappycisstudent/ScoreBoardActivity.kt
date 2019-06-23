package com.example.karenzheng.flappycisstudent

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.score_board_activity.*
import android.content.Intent
import com.example.karenzheng.flappycisstudent.R.id.*


class ScoreBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.score_board_activity)

        val score:String = intent.getStringExtra("score")
        playerScore.setText("Your score is: " + score)

        savePlayer.setOnClickListener {
            val name = addName.text.toString()
            val course = addClass.text.toString()

            // add in shared preferences
            if (!course.isEmpty() && !name.isEmpty()) {
                val sharedPref = this?.getSharedPreferences("players", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                val currNames = sharedPref.getString("names", "")
                if (currNames.isEmpty()) {
                    editor.putString("names", name)
                } else {
                    editor.putString("names", currNames +"|"+name)
                }
                editor.putString(name+"_class", course)
                editor.putString(name+"_score", score)
                editor.commit()
            }
            finish()

            val i = Intent(this, ScoreList::class.java)
            startActivity(i)
        }
    }
}