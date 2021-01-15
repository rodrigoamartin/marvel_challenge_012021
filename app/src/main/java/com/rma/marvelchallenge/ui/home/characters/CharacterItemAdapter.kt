package com.rma.marvelchallenge.ui.home.characters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.rma.marvelchallenge.R
import com.rma.marvelchallenge.core.platform.IconHelper
import com.rma.marvelchallenge.domain.Character

class CharacterItemAdapter(
    private var items: MutableList<Character>,
    private val context: Context
): RecyclerView.Adapter<CharacterItemAdapter.ViewHolder>() {

    interface Callback{
        fun onCharacterTapped(character: Character)
    }

    private var callback: Callback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addListener(callback: Callback){
        this.callback = callback
    }

    fun addItems(characters: List<Character>) {
        items.addAll(characters)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameLabel: TextView = itemView.findViewById(R.id.nameLabel)
        private val descriptionLabel: TextView = itemView.findViewById(R.id.descriptionLabel)
        private val avatarImage: ImageView = itemView.findViewById(R.id.avatarImage)
        private val container: ConstraintLayout = itemView.findViewById(R.id.container)

        fun bind(item: Character) {
            nameLabel.text =  item.name
            descriptionLabel.text = item.description

            // draw avatar
            val iconHelper = IconHelper(context)
            iconHelper.draw(item.iconUrl, avatarImage)

            container.setOnClickListener {
                callback?.onCharacterTapped(item)
            }
        }
    }
}