package com.example.adnceiba.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adnceiba.R
import com.example.adnceiba.adapters.VehicleAdapter
import com.example.adnceiba.databinding.FragmentVehiclesBinding
import com.example.adnceiba.extensions.observeEvent
import com.example.adnceiba.ui.UIState
import com.example.adnceiba.viewmodels.VehiclesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class VehiclesFragment : Fragment() {

    private var _binding: FragmentVehiclesBinding? = null
    private val binding get() = _binding

    private val vehiclesViewModel: VehiclesViewModel by viewModel()
    private lateinit var  vehicleAdapter: VehicleAdapter

    private fun allVehiclesLiveDataHandler() {

        vehiclesViewModel.allVehiclesLiveData.observeEvent(viewLifecycleOwner) { uiState ->

            when (uiState) {
                is UIState.OnLoading -> {
                    isLoading(uiState.loading)
                }
                is UIState.OnSuccess -> {
                    val data = uiState.data
                    vehicleAdapter.clearData()
                    if (data.isNotEmpty()) {
                        dataNoEmpty()
                        vehicleAdapter.setData(data)
                    } else {
                        dataEmpty(getString(R.string.message_list_empty))
                    }
                }
                is UIState.OnError -> {
                    dataEmpty(uiState.error)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVehiclesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupEvents()
        allVehiclesLiveDataHandler()
    }

    override fun onResume() {
        super.onResume()
        vehiclesViewModel.getAllVehicles()
    }

    private fun setupEvents() {

        _binding?.root?.fabAddVehicle?.setOnClickListener {
            findNavController().navigate(R.id.action_vehiclesFragment_to_addVehicleFragment)
        }
    }

    private fun setupAdapter() {
        vehicleAdapter = VehicleAdapter ( clickClosure = {
        })

        vehicleAdapter.setHasStableIds(true)
        binding?.userRecyclerView?.apply {
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = vehicleAdapter
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
        binding?.fabAddVehicle?.visibility = View.GONE
        binding?.includeEmptyView?.root?.visibility = View.VISIBLE
        binding?.includeEmptyView?.emptyTextView?.text = text
    }

    private fun dataNoEmpty() {
        binding?.fabAddVehicle?.visibility = View.VISIBLE
        binding?.includeEmptyView?.root?.visibility = View.GONE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}