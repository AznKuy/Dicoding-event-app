package com.example.dicodingeventapp.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingeventapp.data.database.FavoriteEvent
import com.example.dicodingeventapp.databinding.FragmentFavoriteBinding
import com.example.dicodingeventapp.ui.adapter.FavoriteEventAdapter
import com.example.dicodingeventapp.ui.detail.DetailActivity


class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val favoriteViewModel: FavoriteViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visibility = View.VISIBLE

        val adapter = FavoriteEventAdapter(object : FavoriteEventAdapter.OnItemClickListener {
            override fun onItemClick(favoriteEvent: FavoriteEvent) {
                // move to detail activity
                val intent = Intent(requireActivity(), DetailActivity::class.java).apply {
                    putExtra("eventId", favoriteEvent.id.toInt())
                }
                startActivity(intent)
            }
        })
        binding.rvFavorites.layoutManager = LinearLayoutManager(context)
        binding.rvFavorites.adapter = adapter
        favoriteViewModel.favoriteEvents.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            adapter.setFavoriteEvents(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}