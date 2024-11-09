@file:Suppress("unused", "RedundantSuppression")

package com.example.dicodingeventapp.ui.finished

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingeventapp.data.remote.response.ListEventsItem
import com.example.dicodingeventapp.ui.adapter.EventAdapter
import com.example.dicodingeventapp.databinding.FragmentFinishedBinding
import com.example.dicodingeventapp.ui.detail.DetailActivity

@Suppress("unused", "RedundantSuppression", "RedundantSuppression")
class FinishedFragment : Fragment() {
    private var _binding: FragmentFinishedBinding? = null
    private val binding get() = _binding!!
    private val finishedViewModel: FinishedViewModel by viewModels()
    private lateinit var eventAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventAdapter = EventAdapter(object : EventAdapter.OnItemClickListener {
            override fun onItemClick(event: ListEventsItem) {
                // move to detail activity
                val intent = Intent(requireActivity(), DetailActivity::class.java).apply {
                    putExtra("eventId", event.id)
                }
                startActivity(intent)
            }
        })

        binding.rvFinishedEvent.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = eventAdapter
        }

        finishedViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        finishedViewModel.finishedEvent.observe(viewLifecycleOwner) { events ->
            eventAdapter.submitList(events)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}