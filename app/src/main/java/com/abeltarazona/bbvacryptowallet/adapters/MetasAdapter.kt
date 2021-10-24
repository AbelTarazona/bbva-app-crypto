package com.abeltarazona.bbvacryptowallet

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abeltarazona.bbvacryptowallet.databinding.ItemMetaBinding

/**
 * Created by AbelTarazona on 23/10/2021
 */
class MetasAdapter : ListAdapter<Meta, MetasAdapter.Holder>(MetaDiffCallback()) {

    var selectedItemPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = parent.inflate(R.layout.item_meta)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)

        if (position == selectedItemPos) {
            holder.selected()
        } else {
            holder.unselected()
        }

        holder.bind(item)

        holder.itemView.setOnClickListener {
            selectedItemPos = position
            notifyDataSetChanged()
        }
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemMetaBinding.bind(view)

        fun bind(data: Meta) {
            with(binding) {
                textView20.text = data.title
                imageView7.setImageResource(data.icon)
            }
        }

        fun selected() {
            binding.imageView8.visibility = View.VISIBLE
        }

        fun unselected() {
            binding.imageView8.visibility = View.INVISIBLE
        }
    }

}

class MetaDiffCallback : DiffUtil.ItemCallback<Meta>() {
    override fun areItemsTheSame(oldItem: Meta, newItem: Meta): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Meta, newItem: Meta): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}