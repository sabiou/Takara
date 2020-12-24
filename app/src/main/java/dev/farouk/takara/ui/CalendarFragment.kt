package dev.farouk.takara.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.farouk.takara.data.model.Event
import dev.farouk.takara.databinding.FragmentCalendarBinding
import dev.farouk.takara.ui.adapters.CalendarAdapter

class CalendarFragment : Fragment() {

    private lateinit var adapter: CalendarAdapter
    private val dataList = ArrayList<Event>()
    private lateinit var layoutManager: LinearLayoutManager

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)

        setTimelineItems()
        subscribeUi()

        return binding.root
    }

    private fun setTimelineItems() {
        dataList.add(Event("05/12/2020","Ouverture Campagne élection présidentielle 1er Tour et Législatives"))
        dataList.add(Event("25/12/2020","Clôture campagne électorale Présidentielle 1er Tour et Législatives"))
        dataList.add(Event("27/12/2020","JOUR DU SCRUTIN pour les élections présidentielle 1er tour et Législatives"))
        dataList.add(Event("28/12/2020","Proclamation et diffusion des résultats globaux provisoires pour l'élection présidentielle 1er tour et Législatives par la CENI "))
        dataList.add(Event("03/01/2021", "Proclamation des résultats définitifs de l’élection Présidentielle 1er tour par la Cour constitutionnelle "))
        dataList.add(Event("03/01/2021","Proclamation des résultats définitifs des élections législatives par la Cour Constitutionnelle"))
        dataList.add(Event("01/02/2021","Ouverture de la Campagne électorale - Election Présidentielle 2nd tour"))
        dataList.add(Event("18/02/2021","Clôture de la campagne électorale - élection présidentielle 2nd tour"))
        dataList.add(Event("20/02/2021","JOUR DU SCRUTIN - élection présidentielle 2nd tour"))
        dataList.add(Event("22/02/2021 ","Proclamation et diffusion des résultats globaux provisoires par la CENI - présidentielle 2nd tour"))
        dataList.add(Event("28/02/2021","Proclamation des résultats définitifs - présidentielle 2nd tour"))
    }

    private fun subscribeUi() {
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.events.layoutManager = layoutManager
        adapter = CalendarAdapter(dataList)
        binding.events.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}