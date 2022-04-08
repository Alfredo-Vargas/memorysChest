package com.example.memoryschest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

// TODO: add array of booleans, each item has now a state of selected or not selected !!!
class GridItemAdapter (val cardTitles : Array<String>, val cardImages : Array<String>, var selectedSymbols : Array<String>)
    : RecyclerView.Adapter<GridItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardImage : ImageView = itemView.findViewById(R.id.cardImage)
        val cardTitle : TextView = itemView.findViewById(R.id.cardTitle)
        val selectedSymbols : ImageView = itemView.findViewById(R.id.item_selected_mark)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cardTitle.text = cardTitles[position]
        Picasso.get().load(cardImages[position]).into(holder.cardImage)
//        holder.selectedSymbols.visibility = View.GONE
    }

    override fun getItemCount(): Int {
        return cardTitles.size
    }
}