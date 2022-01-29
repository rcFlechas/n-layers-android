package com.example.adnceiba.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adnceiba.R
import com.example.adnceiba.adapters.PlaceAdapter
import com.example.adnceiba.databinding.FragmentPlacesFreeBinding
import com.example.adnceiba.extensions.observeEvent
import com.example.adnceiba.ui.UIState
import com.example.adnceiba.utilities.Dialog
import com.example.adnceiba.viewmodels.PlacesFreeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlacesFreeFragment : Fragment() {

    private var _binding: FragmentPlacesFreeBinding? = null
    private val binding get() = _binding

    private val placesFreeViewModel: PlacesFreeViewModel by viewModel()
    private lateinit var  placeAdapter: PlaceAdapter

    private fun allPlacesFreeLiveDataHandler() {
        placesFreeViewModel.allPlacesFreeLiveData.observeEvent(viewLifecycleOwner) { uiState ->

            when (uiState) {
                is UIState.OnLoading -> {
                    isLoading(uiState.loading)
                }
                is UIState.OnSuccess -> {
                    isLoading(false)
                    val data = uiState.data
                    placeAdapter.clearData()
                    if (data.isNotEmpty()) {
                        dataNoEmpty()
                        placeAdapter.setData(data)
                    } else {
                        dataEmpty(getString(R.string.message_list_empty))
                    }
                }
                is UIState.OnError -> {
                    isLoading(false)
                    Dialog.basic(requireContext(), uiState.error)
                    dataEmpty(uiState.error)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlacesFreeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        allPlacesFreeLiveDataHandler()
    }

    override fun onResume() {
        super.onResume()
        placesFreeViewModel.getAllPlacesFree()
    }

    private fun setupAdapter() {
        placeAdapter = PlaceAdapter ()
        placeAdapter.setHasStableIds(true)
        binding?.placeRecyclerView?.apply {
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = placeAdapter
        }
    }

    private fun isLoading(loading: Boolean) {
        binding?.progressBar?.visibility = if (loading) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun dataEmpty(text: String = String()) {
        binding?.placeRecyclerView?.visibility = View.GONE
        binding?.includeEmptyView?.root?.visibility = View.VISIBLE
        binding?.includeEmptyView?.emptyTextView?.text = text
    }

    private fun dataNoEmpty() {
        binding?.placeRecyclerView?.visibility = View.VISIBLE
        binding?.includeEmptyView?.root?.visibility = View.GONE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}