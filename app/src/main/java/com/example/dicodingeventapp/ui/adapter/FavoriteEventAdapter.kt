package com.example.dicodingeventapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dicodingeventapp.data.database.FavoriteEvent
import com.example.dicodingeventapp.databinding.ItemFavoriteEventBinding

class FavoriteEventAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<FavoriteEventAdapter.FavoriteEventViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(favoriteEvent: FavoriteEvent)
    }

    private var favoriteEventAdapter: List<FavoriteEvent> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteEventViewHolder {
        val binding = ItemFavoriteEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteEventViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: FavoriteEventViewHolder, position: Int) {
        holder.bind(favoriteEventAdapter[position])
    }

    override fun getItemCount(): Int = favoriteEventAdapter.size

    @SuppressLint("NotifyDataSetChanged")
    fun setFavoriteEvents(favoriteEvents: List<FavoriteEvent>) {
        this.favoriteEventAdapter = favoriteEvents
        notifyDataSetChanged()
    }

    class FavoriteEventViewHolder(
        private val binding: ItemFavoriteEventBinding, private val listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteEvent: FavoriteEvent) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(favoriteEvent.mediaCover)
                    .into(imgFavorite)
                tvFavoriteTitle.text = favoriteEvent.name
                tvFavoriteCategory.text = favoriteEvent.category

                itemView.setOnClickListener {
                    listener.onItemClick(favoriteEvent)
                }
            }
        }
    }
}
