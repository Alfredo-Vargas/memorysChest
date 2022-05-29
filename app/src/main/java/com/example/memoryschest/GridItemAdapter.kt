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
                       private val showMenuDelete: (Boolean) -> Unit
                       ): RecyclerView.Adapter<GridItemAdapter.ViewHolder>() {
    private var isEnabled = false
    private val itemSelectedList = mutableListOf<Int>()  // dynamic list to track selected items

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
        // initially all select marks are not visible
        holder.selectMark.visibility = View.GONE

        holder.cardImage.setOnClickListener {
            if (item.selected == false) {
                selectItem(holder, item, position)
            }
            else {
                deselectItem(holder, item, position)
            }
        }
//
//        holder.cardImage.setOnLongClickListener {
//            selectItem(holder, item, position)
//            true
//        }
//

//        holder.cardImage.setOnClickListener {
//            //  if already selected then means remove selection
//            if (itemSelectedList.contains(position)) {
//                itemSelectedList.removeAt(position)
//                holder.selectMark.visibility = View.GONE
//                item.selected = false
//                if (itemSelectedList.isEmpty()) {
//                    // if nothing is selected no remove operations are allowed
//                    showMenuDelete(false)
//                    isEnabled = false
//                }
//            }
//            else if (isEnabled) {
//                selectItem(holder, item, position)
//            }
//        }
    }

    // function that selects the image visually
    private fun selectItem(holder: GridItemAdapter.ViewHolder, item: CardItemValues, position: Int) {
        isEnabled = true  // at least one selected item shows the delete button
//        itemSelectedList.add(position)  // we update the dynamic list that keeps track of selected items
        item.selected = true
        holder.selectMark.visibility = View.VISIBLE
        showMenuDelete(true)
    }
    // function that deselects the image visually
    private fun deselectItem(holder: GridItemAdapter.ViewHolder, item: CardItemValues, position: Int) {
//        itemSelectedList.removeAt(position)
        item.selected = false
        holder.selectMark.visibility = View.GONE
/*

        if (itemselectedlist.isempty()) {
            showmenudelete(false)
            isenabled = false
        }

*/
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