package com.rma.marvelchallenge.ui.home.events

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rma.marvelchallenge.R
import com.rma.marvelchallenge.core.platform.DateUtils
import com.rma.marvelchallenge.core.platform.IconHelper
import com.rma.marvelchallenge.domain.Comic

class EventItemAdapter(
    private var items: MutableList<EventItem>,
    private val context: Context
): RecyclerView.Adapter<EventItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(events: List<EventItem>) {
        items.addAll(events)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val titleLabel: TextView = itemView.findViewById(R.id.eventTitleLabel)
        private val startDateLabel: TextView = itemView.findViewById(R.id.startDateLabel)
        private val endDateLabel: TextView = itemView.findViewById(R.id.endDateLabel)
        private val eventImage: ImageView = itemView.findViewById(R.id.eventImage)
        private val collapseViewButton: ImageButton = itemView.findViewById(R.id.collapseViewButton)
        private var comicContainer: LinearLayout = itemView.findViewById(R.id.comicContainer)
        private var comicItemsContainer: LinearLayout = itemView.findViewById(R.id.comicItemsContainer)

        fun bind(item: EventItem, position: Int) {
            titleLabel.text = item.name
            startDateLabel.text = DateUtils.toString(item.startDate)
            endDateLabel.text = DateUtils.toString(item.endDate)
            IconHelper(context).draw(item.iconUrl, eventImage)

            comicContainer.visibility = if (item.isExpanded) View.VISIBLE else View.GONE
            collapseViewButton.setImageDrawable(
                if (item.isExpanded) ContextCompat.getDrawable(context, R.drawable.ic_collapse) else
                    ContextCompat.getDrawable(context, R.drawable.ic_expand)
            )

            collapseViewButton.setOnClickListener {
                item.isExpanded = !item.isExpanded
                notifyItemChanged(position)
            }

            for (comic in item.comics){
                renderComic(comic)
            }
        }

        private fun renderComic(comic: Comic){
            val view = View.inflate(context, R.layout.item_character_comic, null)
            val nameLabel = view.findViewById<TextView>(R.id.characterComicNameLabel)
            nameLabel.text = comic.name
            if (comic.year.isNotEmpty()){
                val yearLabel = view.findViewById<TextView>(R.id.characterComicYearLabel)
                yearLabel.text = comic.year
            }
            comicItemsContainer.addView(view)
        }
    }
}