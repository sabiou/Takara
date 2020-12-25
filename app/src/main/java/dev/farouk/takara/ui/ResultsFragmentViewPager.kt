package dev.farouk.takara.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import dev.farouk.takara.R
import dev.farouk.takara.databinding.FragmentResultsViewPagerBinding
import dev.farouk.takara.ui.adapters.LEGISLATIVE_PAGE_INDEX
import dev.farouk.takara.ui.adapters.PRESIDENTIAL_PAGE_INDEX
import dev.farouk.takara.ui.adapters.ResultsPagerAdapter

@AndroidEntryPoint
class ResultsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentResultsViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager
        viewPager.adapter = ResultsPagerAdapter(this)

        // set text on tabs
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        return binding.root
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            PRESIDENTIAL_PAGE_INDEX -> getString(R.string.presidential)
            LEGISLATIVE_PAGE_INDEX -> getString(R.string.legislative)
            else -> null
        }
    }
}