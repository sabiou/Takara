package dev.farouk.takara.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.farouk.takara.ui.LegislativesFragment
import dev.farouk.takara.ui.PresidentialFragment

/**
 * Created by Farouk on 25/12/2020.
 */

const val PRESIDENTIAL_PAGE_INDEX = 0
const val LEGISLATIVE_PAGE_INDEX = 1

class ResultsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    // Mapping of the ViewPager page indexes to their respective Fragments
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        PRESIDENTIAL_PAGE_INDEX to { PresidentialFragment() },
        LEGISLATIVE_PAGE_INDEX to { LegislativesFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

}