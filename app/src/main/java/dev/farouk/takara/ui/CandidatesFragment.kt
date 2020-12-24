package dev.farouk.takara.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.transition.MaterialFadeThrough
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding
import dev.farouk.takara.databinding.FragmentCandidatesBinding
import dev.farouk.takara.ui.adapters.CandidatesAdapter
import dev.farouk.takara.ui.viewmodels.CandidatesViewModel

@AndroidEntryPoint
class CandidatesFragment : Fragment() {

    private val viewModel: CandidatesViewModel by viewModels()

    private lateinit var binding: FragmentCandidatesBinding

    private val transitionDuration by lazy {
        resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCandidatesBinding.inflate(inflater, container, false)
        val adapter = CandidatesAdapter()
        binding.candidatesList.adapter = adapter

        subscribeUi(adapter)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        applyEdgeToEdge()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough().apply { duration = transitionDuration }
        exitTransition = MaterialFadeThrough().apply { duration = transitionDuration }
    }

    private fun subscribeUi(adapter: CandidatesAdapter) {
        viewModel.candidates.observe(viewLifecycleOwner) { candidates ->
            adapter.submitList(candidates)
        }
    }

    private fun applyEdgeToEdge() {
        binding.candidatesList.applySystemWindowInsetsToPadding(left = true, top = true, right = true)
    }
}