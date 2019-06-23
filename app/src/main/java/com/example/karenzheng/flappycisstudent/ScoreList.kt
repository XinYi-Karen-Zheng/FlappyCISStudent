package com.example.karenzheng.flappycisstudent

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.karenzheng.flappycisstudent.PlayerAdapter
import com.example.karenzheng.flappycisstudent.R.id.players_recycler_view
import kotlinx.android.synthetic.main.score_list.*
import java.util.*


class ScoreList : AppCompatActivity() {
    val players: ArrayList<Player> = ArrayList<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.score_list)

        // retrieve contacts from shared prefs
        val sharedPref = this?.getSharedPreferences("players", Context.MODE_PRIVATE)
        val names = sharedPref.getString("names", "").split("|")
        if (!sharedPref.getString("names","").isEmpty()) {
            for (name in names) {
                val course = sharedPref.getString(name+"_class", "")
                val score = sharedPref.getString(name+"_score", "")
                val player = Player(name, course, score)
                players.add(player)
            }
        players.sortWith(compareBy({it.score}))
        }

        /**Custom RecyclerView Example**/
        // creates a vertical linear layout manager
        players_recycler_view.layoutManager = LinearLayoutManager(this)

        //adapter with click listener
        players_recycler_view.adapter = PlayerAdapter(this, players)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.delete -> {

                val builder = AlertDialog.Builder(this@ScoreList)
                builder.setTitle("Delete score board")
                builder.setMessage("Are you sure you want to delete all the cute CIS students?")
                builder.setPositiveButton("Yes") {dialog, which ->
                    val sharedPref = this?.getSharedPreferences("players", Context.MODE_PRIVATE)
                    val process = sharedPref.edit()
                    process.clear()
                    process.commit()
                    Toast.makeText(applicationContext, "Done", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("No") { dialog, which ->

                }

                builder.show()

                return true
            }
            R.id.new_game->{
                val i = Intent(this, GameActivity::class.java)
                startActivity(i)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}