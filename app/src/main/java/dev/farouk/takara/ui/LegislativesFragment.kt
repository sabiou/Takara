package dev.farouk.takara.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.farouk.takara.databinding.FragmentLegislativesBinding

class LegislativesFragment : Fragment() {

    private var _binding: FragmentLegislativesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLegislativesBinding.inflate(inflater, container, false)
        return binding.root
    }
}