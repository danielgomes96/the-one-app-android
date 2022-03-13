package com.devlabs.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devlabs.domain.entity.Quote

class QuotesAdapter: ListAdapter<Quote, QuotesAdapter.Holder>(QuotesAdapter) {

    private var quoteList = mutableListOf<Quote>()

    fun addItemsToList(list: List<Quote>) {
        quoteList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate((R.layout.item_quote), parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(quoteList[position])
    }

    override fun getItemCount(): Int = quoteList.size


    private companion object : DiffUtil.ItemCallback<Quote>() {
        override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem == newItem
        }
    }

    inner class Holder(view: View): RecyclerView.ViewHolder(view) {
        private val tvDialog: TextView = view.findViewById(R.id.item_quote_dialog_text)

        fun bind(quote: Quote) {
            tvDialog.text = quote.dialog
        }
    }
}