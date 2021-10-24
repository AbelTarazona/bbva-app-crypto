package com.abeltarazona.bbvacryptowallet

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abeltarazona.bbvacryptowallet.databinding.ItemCollectBinding

/**
 * Created by AbelTarazona on 23/10/2021
 */
class CollectAdapter :
    ListAdapter<Collect, CollectAdapter.Holder>(CollectDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = parent.inflate(R.layout.item_collect)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)

        holder.bind(item)

    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemCollectBinding.bind(view)

        fun bind(data: Collect) {
            with(binding) {
                textView25.text = "${data.tokens} tokens"
                textView26.text = data.description
            }
        }
    }

}

class CollectDiffCallback : DiffUtil.ItemCallback<Collect>() {
    override fun areItemsTheSame(oldItem: Collect, newItem: Collect): Boolean {
        return oldItem.description == newItem.description
    }

    override fun areContentsTheSame(oldItem: Collect, newItem: Collect): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}