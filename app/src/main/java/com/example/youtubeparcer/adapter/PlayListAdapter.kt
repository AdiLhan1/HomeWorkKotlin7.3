package com.example.youtubeparcer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeparcer.R
import com.example.youtubeparcer.model.ItemsItem
import com.squareup.picasso.Picasso

class PlayListAdapter(val function: (ItemsItem) -> Unit) :
    RecyclerView.Adapter<PlayListAdapter.YoutubeViewHolder>() {
    private var list = mutableListOf<ItemsItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_yotube_playlist, parent, false)
        return YoutubeViewHolder(view, function)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: YoutubeViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updateData(newList: MutableList<ItemsItem>) {
        list = newList
        notifyDataSetChanged()
    }

    class YoutubeViewHolder(itemView: View, val function: (ItemsItem) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private var image: ImageView? = null
        private var title: TextView? = null
        private var description: TextView? = null

        init {
            image = itemView.findViewById(R.id.image)
            title = itemView.findViewById(R.id.title)
            description = itemView.findViewById(R.id.description)
        }

        fun bind(item: ItemsItem) {
            Picasso
                .get()
                .load(item.snippet.thumbnails.medium.url)
                .fit()
                .centerCrop()
                .into(image)
            title?.text = item.snippet.title
            description?.text = item.contentDetails.itemCount
            itemView.setOnClickListener {
                function(item)
            }

        }

    }
}
