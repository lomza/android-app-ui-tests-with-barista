package com.lomza.uitestsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroceriesListAdapter(
    private val groceriesArray: Array<String>,
    private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<GroceriesListAdapter.MyViewHolder>() {

    class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent,false) as TextView
        return MyViewHolder(textView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val grocery = groceriesArray[position]
        holder.textView.text = grocery
        holder.textView.setOnClickListener { onItemClick(grocery) }
    }

    override fun getItemCount() = groceriesArray.size
}