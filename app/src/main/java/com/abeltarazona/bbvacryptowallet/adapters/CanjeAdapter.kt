package com.abeltarazona.bbvacryptowallet

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abeltarazona.bbvacryptowallet.databinding.ItemCanjeBinding
import com.bumptech.glide.Glide

/**
 * Created by AbelTarazona on 23/10/2021
 */
class CanjeAdapter :
    ListAdapter<Canje, CanjeAdapter.Holder>(CanjeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = parent.inflate(R.layout.item_canje)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)

        holder.bind(item)

    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemCanjeBinding.bind(view)

        fun bind(data: Canje) {
            with(binding) {
                Glide.with(itemView).load(data.image).into(imageView12)
                textView27.text = data.name
                tks.text = "${data.tokens} BBVA"
            }
        }
    }
}

class CanjeDiffCallback : DiffUtil.ItemCallback<Canje>() {
    override fun areItemsTheSame(oldItem: Canje, newItem: Canje): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Canje, newItem: Canje): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}