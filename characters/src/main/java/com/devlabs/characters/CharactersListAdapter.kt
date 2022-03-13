package com.devlabs.characters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devlabs.domain.entity.Character
import com.devlabs.domain.entity.Movie

class CharactersListAdapter(
    private val onCharacterClicked: () -> (Unit)
): ListAdapter<Character, CharactersListAdapter.Holder>(CharactersListAdapter) {

    private var characterList = mutableListOf<Character>()

    fun addItemsToList(list: List<Character>) {
        characterList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate((R.layout.item_character), parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(characterList[position], onCharacterClicked)
    }

    override fun getItemCount(): Int = characterList.size


    private companion object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    inner class Holder(view: View): RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.item_character_name)

        fun bind(character: Character, characterClicked: () -> (Unit)) {
            tvTitle.text = character.name

            itemView.setOnClickListener {
                characterClicked()
            }
        }
    }
}