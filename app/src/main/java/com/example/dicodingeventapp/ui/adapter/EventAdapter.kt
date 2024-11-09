package com.example.dicodingeventapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dicodingeventapp.data.remote.response.ListEventsItem
import com.example.dicodingeventapp.databinding.ItemEventBinding

@Suppress("RedundantSuppression", "RedundantSuppression")
class EventAdapter(private val listener: OnItemClickListener) :
    ListAdapter<ListEventsItem, EventAdapter.MyViewHolder>(DIFF_CALLBACK) {

    interface OnItemClickListener {
        fun onItemClick(event: ListEventsItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

    class MyViewHolder(private val binding: ItemEventBinding, private val listener: OnItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(event: ListEventsItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(event.mediaCover)
                    .into(imgEvent)
                tvName.text = event.name

                itemView.setOnClickListener {
                    Log.d("EventViewModel", "Requesting event with id: ${event.id}")
                    listener.onItemClick(event)
                }
            }
        }
    }

    @Suppress("unused", "unused")
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListEventsItem>() {
            override fun areItemsTheSame(oldItem: ListEventsItem, newItem: ListEventsItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ListEventsItem, newItem: ListEventsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}