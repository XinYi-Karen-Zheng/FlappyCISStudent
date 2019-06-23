package com.example.karenzheng.flappycisstudent

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.player_item.view.*

/**
 * Created by Angel on 2/23/2019.
 */

class PlayerAdapter(var activity: Activity,
                                 var playerList:List<Player>) : RecyclerView.Adapter<ViewHolder>() {
    //gets the number of items in the list
    override fun getItemCount() = playerList.size

    // create viewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(activity).inflate(R.layout.player_item, parent, false))

    // binds the contact info to the viewholder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(playerList[position])
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var name: TextView = view.PlayerName
    var fav: TextView = view.PlayerFav
    var score: TextView = view.PlayerScore

    // for binding contact info to view
    fun bind(item: Player) {
        name.text = item.name
        fav.text = item.favClass
        score.text = item.score
    }
}
