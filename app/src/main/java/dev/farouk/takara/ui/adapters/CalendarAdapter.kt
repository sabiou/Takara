package dev.farouk.takara.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.vipulasri.timelineview.TimelineView
import dev.farouk.takara.databinding.ItemEventBinding
import dev.farouk.takara.data.model.Event

/**
 * Created by Farouk on 17/12/2020.
 */
class CalendarAdapter(private val data: List<Event>):
    RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

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

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val event = data[position]
        // date
        holder.date.text = event.date
        // title
        holder.title.text = event.title
    }

    override fun getItemCount(): Int = data.size

    inner class CalendarViewHolder(itemView: ItemEventBinding, viewType: Int)
        : RecyclerView.ViewHolder(itemView.root) {
        val date = itemView.textTimelineDate
        val title = itemView.textTimelineTitle
        private val timeline = itemView.timeline

        init {
            timeline.initLine(viewType)
        }
    }
}