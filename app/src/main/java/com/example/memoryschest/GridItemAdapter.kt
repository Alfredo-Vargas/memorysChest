package com.example.memoryschest

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.memoryschest.models.CardItemValues
import com.squareup.picasso.Picasso

// TODO: add array of booleans, each item has now a state of selected or not selected !!!
class GridItemAdapter (private var listItemValues: MutableList<CardItemValues>,
                       val cardTitles : Array<String>,
                       val cardImages : Array<String>,
                       var cardValues : MutableList<CardItemValues>,
                       private val showMenuDelete: (Boolean) -> Unit
                       ): RecyclerView.Adapter<GridItemAdapter.ViewHolder>() {
    private var isEnabled = false
    private val itemSelectedList = mutableListOf<Int>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardImage : ImageView = itemView.findViewById(R.id.cardImage)
        val cardTitle : TextView = itemView.findViewById(R.id.cardTitle)
        val selectMark : ImageView = itemView.findViewById(R.id.item_selected_mark)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItemValues[position]
        holder.cardTitle.text = cardTitles[position]
        Picasso.get().load(cardImages[position]).into(holder.cardImage)
        holder.selectMark.visibility = View.GONE

        holder.cardTitle.setOnLongClickListener {
            selectItem(holder, item, position)
            true
        }

        holder.cardTitle.setOnClickListener {
            if (itemSelectedList.contains(position)) {
                itemSelectedList.removeAt(position)
//                holder.selectMark.visibility = View.GONE
                item.selected = false
                if (itemSelectedList.isEmpty()) {
                    showMenuDelete(false)
                    isEnabled = false
                }
            }
            else if (isEnabled) {
                selectItem(holder, item, position)
            }
        }

        }

    private fun selectItem(holder: GridItemAdapter.ViewHolder, item: CardItemValues, position: Int) {
        isEnabled = true
        itemSelectedList.add(position)
        item.selected = true
//        holder.
        showMenuDelete(true)
    }

    override fun getItemCount(): Int {
        return cardTitles.size
    }
/*

    fun deleteSelectedItem() {
        if (itemSelectedList.isNotEmpty()) {
            listItemValues.removeAll{item -> item.selected}
            isEnabled = false
            itemSelectedList.clear()
        }
        notifyDataSetChanged()
    }

*/
}