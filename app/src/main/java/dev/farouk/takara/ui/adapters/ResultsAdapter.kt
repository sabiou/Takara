package dev.farouk.takara.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.farouk.takara.data.model.Candidate
import dev.farouk.takara.databinding.ItemCandidateBinding

/**
 * Created by Farouk on 04/01/2021.
 */
class ResultsAdapter : ListAdapter<Candidate, RecyclerView.ViewHolder>(CandidateDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CandidateViewHolder(
                ItemCandidateBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val candidate = getItem(position)
        (holder as CandidateViewHolder).bind(candidate)
    }

    class CandidateViewHolder(
            private val binding: ItemCandidateBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Candidate) {
            binding.apply {
                candidate = item
                executePendingBindings()
            }
        }
    }

    private class CandidateDiffCallBack : DiffUtil.ItemCallback<Candidate>() {
        override fun areItemsTheSame(oldItem: Candidate, newItem: Candidate): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Candidate, newItem: Candidate): Boolean {
            return oldItem == newItem
        }

    }
}
