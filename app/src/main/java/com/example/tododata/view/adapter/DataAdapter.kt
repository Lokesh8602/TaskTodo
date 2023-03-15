package com.example.tododata.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tododata.R
import com.example.tododata.room.DataEntity

class DataAdapter (
    val context: Context,
    val noteClickDeleteInterface: NoteClickDeleteInterface,
    val noteClickInterface: NoteClickInterface
) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    // on below line we are creating a
    // variable for our all notes list.
    private val allData = ArrayList<DataEntity>()

    // on below line we are creating a view holder class.
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title = itemView.findViewById<TextView>(R.id.title)
        var date = itemView.findViewById<TextView>(R.id.date)
        val deleteIV = itemView.findViewById<ImageView>(R.id.IVDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.view,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.setText(allData.get(position).title)
        holder.date.setText("Date :" + allData.get(position).date)

        holder.deleteIV.setOnClickListener {

            noteClickDeleteInterface.onDeleteIconClick(allData.get(position))
        }

        // on below line we are adding click listener
        // to our recycler view item.
        holder.itemView.setOnClickListener {
            // on below line we are calling a note click interface
            // and we are passing a position to it.
            noteClickInterface.onNoteClick(allData.get(position))
        }
    }

    override fun getItemCount(): Int {
        // on below line we are
        // returning our list size.
        return allData.size
    }

    // below method is use to update our list of notes.
    fun updateList(newList: List<DataEntity>) {
        // on below line we are clearing
        // our notes array list
        allData.clear()
        // on below line we are adding a
        // new list to our all notes list.
        allData.addAll(newList)
        // on below line we are calling notify data
        // change method to notify our adapter.
        notifyDataSetChanged()
    }
}

interface NoteClickDeleteInterface {
    // creating a method for click
    // action on delete image view.
    fun onDeleteIconClick(note: DataEntity)
}
interface NoteClickInterface {
    // creating a method for click action
    // on recycler view item for updating it.
    fun onNoteClick(note: DataEntity)
}