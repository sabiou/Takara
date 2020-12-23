package dev.farouk.takara.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.farouk.takara.databinding.FragmentCandidatesBinding
import dev.farouk.takara.ui.adapters.CandidatesAdapter
import dev.farouk.takara.ui.viewmodels.CandidatesViewModel

@AndroidEntryPoint
class CandidatesFragment : Fragment() {

    private val viewModel: CandidatesViewModel by viewModels()

    private lateinit var binding: FragmentCandidatesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCandidatesBinding.inflate(inflater, container, false)
        val adapter = CandidatesAdapter()
        binding.candidatesList.adapter = adapter

        subscribeUi(adapter)
        return binding.root
    }

    private fun subscribeUi(adapter: CandidatesAdapter) {
        viewModel.candidates.observe(viewLifecycleOwner) { candidates ->
            adapter.submitList(candidates)
        }
    }
}