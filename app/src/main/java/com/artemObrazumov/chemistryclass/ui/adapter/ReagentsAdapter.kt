package com.artemObrazumov.chemistryclass.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artemObrazumov.chemistryclass.R
import com.artemObrazumov.chemistryclass.data.models.Reagent

class ReagentsAdapter(
    private var dataSet: List<Reagent> = emptyList(),
    private val onReagentClicked: (position: Int) -> Unit
): RecyclerView.Adapter<ReagentsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View, val onReagentClicked: (position: Int) -> Unit)
        :RecyclerView.ViewHolder(itemView) {
        val reagentName: TextView = itemView.findViewById(R.id.reagentName)
        init {
            itemView.setOnClickListener {
                onReagentClicked(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reagent_item, parent, false)
        return ViewHolder(view, onReagentClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reagent = dataSet[position]
        holder.reagentName.text = reagent.name
    }

    override fun getItemCount(): Int = dataSet.size

    fun setDataSet(data: List<Reagent>) {
        this.dataSet = data
        notifyDataSetChanged()
    }

    fun getDataSet(): List<Reagent> = dataSet
}