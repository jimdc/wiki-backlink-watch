package com.kongzi.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kongzi.model.Baqlink
import com.kongzi.databinding.RvItemBaqlinkBinding

class BacklinkRecyclerViewAdapter(private var items: List<Baqlink>,
                                  private var listener: OnItemClickListener)
    : RecyclerView.Adapter<BacklinkRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvItemBaqlinkBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun replaceData(list: List<Baqlink>) {
        items = list
        notifyDataSetChanged()
    }

    class ViewHolder(private var binding: RvItemBaqlinkBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(baq: Baqlink, listener: OnItemClickListener?) {
            binding.baqlink = baq
            if (listener != null) {
                binding.root.setOnClickListener({ _ -> listener.onItemClick(layoutPosition) })
            }

            binding.executePendingBindings()
        }
    }

}