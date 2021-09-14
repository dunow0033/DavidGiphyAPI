package com.example.giphyapi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphyapi.R
import com.example.giphyapi.data.models.Data
import com.example.giphyapi.databinding.ItemGiphyPreviewBinding

class GiphyAdapter() : RecyclerView.Adapter<GiphyAdapter.GiphyViewHolder>() {

    inner class GiphyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    private val differCallback = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyViewHolder {
        ItemGiphyPreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return GiphyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_giphy_preview, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Data) -> Unit)? = null
    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        val giphy = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(giphy.images.original.url).into(findViewById(R.id.ivGiphy))
            setOnClickListener {
                onItemClickListener?.let { it(giphy) }

            }
        }
    }

    fun setOnItemClickListener(listener: (Data) -> Unit) {
        onItemClickListener = listener
    }
}






