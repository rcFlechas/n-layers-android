package com.example.adnceiba.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adnceiba.R
import com.example.adnceiba.binds.CarBind
import com.example.adnceiba.binds.MotorCycleBind
import com.example.adnceiba.binds.VehicleBind
import com.example.adnceiba.databinding.FragmentAddVehicleBinding
import com.example.adnceiba.extensions.observeEvent
import com.example.adnceiba.ui.UIState
import com.example.adnceiba.viewmodels.AddVehicleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddVehicleFragment : Fragment() {

    private var _binding: FragmentAddVehicleBinding? = null
    private val binding get() = _binding

    private val addVehicleViewModel: AddVehicleViewModel by viewModel()

    private fun saveVehicleLiveDataHandler() {

        addVehicleViewModel.saveLiveData.observeEvent(viewLifecycleOwner) { uiState ->

            when (uiState) {
                is UIState.OnLoading -> {
                    isLoading(uiState.loading)
                }
                is UIState.OnSuccess -> {
                    val isSave = uiState.data
                    if (isSave) requireActivity().onBackPressed()
                }
                is UIState.OnError -> {
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddVehicleBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvents()
        saveVehicleLiveDataHandler()
    }

    private fun setupEvents() {

        binding?.saveVehicleButton?.setOnClickListener {
            addVehicleViewModel.save(setupVehicleBind())
        }
    }

    private fun setupVehicleBind(): VehicleBind {

        return when (binding?.typeRadioGroup?.checkedRadioButtonId) {
            R.id.option_motorcycle -> {
                MotorCycleBind(
                    register = binding?.addRegisterEditText?.text.toString(),
                    cylinderCapacity = binding?.addCylinderCapacityEditText?.text.toString().toInt()
                )
            }
            else -> {
                CarBind(
                    register = binding?.addRegisterEditText?.text.toString()
                )
            }
        }
    }

    private fun isLoading(loading: Boolean) {
        binding?.progressBar?.visibility = if (loading) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}