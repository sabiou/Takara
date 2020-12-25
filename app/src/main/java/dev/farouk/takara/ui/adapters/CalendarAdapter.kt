package dev.farouk.takara.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.vipulasri.timelineview.TimelineView
import dev.farouk.takara.databinding.ItemEventBinding
import dev.farouk.takara.data.model.Event

/**
 * Created by Farouk on 17/12/2020.
 */
class CalendarAdapter: ListAdapter<Event, RecyclerView.ViewHolder>(EventDiffCallBack()) {

    private lateinit var mLayoutInflater: LayoutInflater

    override fun getItemViewType(position: Int): Int {
        return TimelineView.getTimeLineViewType(position, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        if(!::mLayoutInflater.isInitialized) {
            mLayoutInflater = LayoutInflater.from(parent.context)
        }
        return CalendarViewHolder(
            ItemEventBinding.inflate(
                mLayoutInflater,
                parent, false), viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val event = getItem(position)
        (holder as CalendarViewHolder).bind(event)
    }

    inner class CalendarViewHolder(private val binding: ItemEventBinding, viewType: Int)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Event) {
            binding.apply {
                event = item
                executePendingBindings()
            }
        }

        private val timeline = binding.timeline

        init {
            timeline.initLine(viewType)
        }
    }

    private class EventDiffCallBack : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }

    }
}