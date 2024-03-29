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
import com.example.adnceiba.binds.*
import com.example.adnceiba.databinding.FragmentPlacesBusyBinding
import com.example.adnceiba.extensions.observeEvent
import com.example.adnceiba.ui.OnError
import com.example.adnceiba.ui.OnLoading
import com.example.adnceiba.ui.OnSuccess
import com.example.adnceiba.utilities.Dialog
import com.example.adnceiba.viewmodels.PlacesBusyViewModel
import com.example.adnceiba.widgets.PickerDialog
import com.example.domain.enum.State
import com.example.domain.valueobjects.TimeBusy
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlacesBusyFragment : Fragment() {

    private var _binding: FragmentPlacesBusyBinding? = null
    private val binding get() = _binding

    private val placesBusyViewModel: PlacesBusyViewModel by viewModel()
    private lateinit var  placeAdapter: PlaceAdapter

    private val vehiclesPickerDialog: PickerDialog<VehicleBind> = PickerDialog()

    private fun allVehiclesLiveDataHandler() {

        placesBusyViewModel.allVehiclesLiveData.observeEvent(viewLifecycleOwner) { uiState ->

            when (uiState) {
                is OnSuccess -> {
                    val data = uiState.data
                    vehiclesPickerDialog.setData(ArrayList(data))
                }
                is OnError -> {
                    Dialog.basic(requireContext(), uiState.error)
                    dataEmpty(uiState.error)
                }
            }
        }
    }

    private fun allPlacesBusyLiveDataHandler() {
        placesBusyViewModel.allPlacesBusyLiveData.observeEvent(viewLifecycleOwner) { uiState ->

            when (uiState) {
                is OnLoading -> {
                    isLoading(uiState.loading)
                }
                is OnSuccess -> {
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
                is OnError -> {
                    isLoading(false)
                    Dialog.basic(requireContext(), uiState.error)
                    dataEmpty(uiState.error)
                }
            }
        }
    }

    private fun savePlaceLiveDataHandler() {

        placesBusyViewModel.saveLiveData.observeEvent(viewLifecycleOwner) { uiState ->

            when (uiState) {
                is OnLoading -> {
                    isLoading(uiState.loading)
                }
                is OnSuccess -> {
                    isLoading( false)
                    val isSave = uiState.data
                    if (isSave) {
                        placesBusyViewModel.getAllPlacesBusy()
                    }
                }
                is OnError -> {
                    isLoading( false)
                    Dialog.basic(requireContext(), uiState.error)
                }
            }
        }
    }

    private fun freePlaceLiveDataHandler() {

        placesBusyViewModel.freePlaceLiveData.observeEvent(viewLifecycleOwner) { uiState ->

            when (uiState) {
                is OnLoading -> {
                    isLoading(uiState.loading)
                }
                is OnSuccess -> {
                    isLoading( false)
                    val isSave = uiState.data
                    if (isSave) {
                        placesBusyViewModel.getAllPlacesBusy()
                    }
                }
                is OnError -> {
                    isLoading( false)
                    Dialog.basic(requireContext(), uiState.error)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlacesBusyBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupEvents()
        setupVehiclesPicker()

        allPlacesBusyLiveDataHandler()
        savePlaceLiveDataHandler()
        freePlaceLiveDataHandler()
        allVehiclesLiveDataHandler()
    }

    override fun onResume() {
        super.onResume()
        placesBusyViewModel.getAllPlacesBusy()
        placesBusyViewModel.getAllVehicles()
    }

    private fun setupEvents() {

        _binding?.fabAddPlace?.setOnClickListener {
            if (!vehiclesPickerDialog.getIsShowing()) {
                vehiclesPickerDialog.show(childFragmentManager, "PickerVehicles")
            }
        }
    }

    private fun setupVehiclesPicker() {
        vehiclesPickerDialog.title = getString(R.string.title_vehicles)
        vehiclesPickerDialog.listener = { vehicleBind ->
            if (vehicleBind != null) {
                placesBusyViewModel.save(setupPlaceBind(vehicleBind))
                vehiclesPickerDialog.setSelected(-1)
            }
        }
    }

    private fun setupPlaceBind(vehicleBind: VehicleBind): PlaceBind {

        return when (vehicleBind) {

            is MotorCycleBind -> {
                PlaceMotorCycleBind(
                    vehicle = vehicleBind,
                    timeBusy = TimeBusy(),
                    state = State.BUSY
                )
            }
            else -> {
                PlaceCarBind(
                    vehicle = vehicleBind as CarBind,
                    timeBusy = TimeBusy(),
                    state = State.BUSY
                )
            }
        }
    }

    private fun setupAdapter() {
        placeAdapter = PlaceAdapter()
        placeAdapter.longClickClosure = { placeBind ->
            placesBusyViewModel.freePlace(placeBind.id)
        }
        placeAdapter.setHasStableIds(true)
        binding?.includePlaceRecyclerView?.placeRecyclerView?.apply {
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
        binding?.includePlaceRecyclerView?.placeRecyclerView?.visibility = View.GONE
        binding?.includeEmptyView?.root?.visibility = View.VISIBLE
        binding?.includeEmptyView?.emptyTextView?.text = text
    }

    private fun dataNoEmpty() {
        binding?.includePlaceRecyclerView?.placeRecyclerView?.visibility = View.VISIBLE
        binding?.includeEmptyView?.root?.visibility = View.GONE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}