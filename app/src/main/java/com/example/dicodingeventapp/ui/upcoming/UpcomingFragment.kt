@file:Suppress("unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "RedundantSuppression", "RedundantSuppression", "RedundantSuppression", "RedundantSuppression",
    "RedundantSuppression", "RedundantSuppression", "RedundantSuppression", "RedundantSuppression",
    "RedundantSuppression", "RedundantSuppression", "RedundantSuppression", "RedundantSuppression"
)

package com.example.dicodingeventapp.ui.upcoming

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingeventapp.data.remote.response.ListEventsItem
import com.example.dicodingeventapp.ui.detail.DetailActivity
import com.example.dicodingeventapp.ui.adapter.EventAdapter
import com.example.dicodingeventapp.databinding.FragmentUpcomingBinding


@Suppress("unused")
class UpcomingFragment : Fragment(), EventAdapter.OnItemClickListener {
    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = _binding!!
    private val upcomingViewModel: UpcomingViewModel by viewModels()
    private lateinit var eventAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventAdapter = EventAdapter(this)

        binding.rvEvent.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = eventAdapter
        }

        upcomingViewModel.upcomingEvent.observe(viewLifecycleOwner) { events ->
            eventAdapter.submitList(events)
        }

        upcomingViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(event: ListEventsItem) {
        val intent = Intent(requireActivity(), DetailActivity::class.java).apply {
            putExtra("eventId", event.id)
        }
        startActivity(intent)
    }
}