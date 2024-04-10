package com.hardik.vasundharatask.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hardik.vasundharatask.R
import com.hardik.vasundharatask.presentation.model.ChessboardModel
import kotlin.math.log

class GridAdapter() : RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    private var items: MutableList<ChessboardModel> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(items: List<ChessboardModel>){
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rec_grid, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.btn.setBackgroundColor(if (items[position].isActive) Color.DKGRAY else Color.GRAY)
        holder.btn.setOnClickListener{
            onItemClickListener?.let {
                it(
                    ChessboardModel(
                        items[position].row,
                        items[position].col,
                        isActive = !items[position].isActive
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btn: TextView = itemView.findViewById(R.id.button)
    }

    private var onItemClickListener: ((ChessboardModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (ChessboardModel) -> Unit) {
        onItemClickListener = listener
    }
}
