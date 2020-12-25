package dev.farouk.takara.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.farouk.takara.databinding.FragmentPresidentialBinding

class PresidentialFragment : Fragment() {

    private var _binding: FragmentPresidentialBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPresidentialBinding.inflate(inflater, container, false)
        return binding.root
    }
}