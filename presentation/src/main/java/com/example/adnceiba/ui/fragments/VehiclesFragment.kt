package com.example.adnceiba.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.adnceiba.R
import com.example.adnceiba.databinding.FragmentVehiclesBinding
import kotlinx.android.synthetic.main.fragment_vehicles.view.*

class VehiclesFragment : Fragment() {

    private var _binding: FragmentVehiclesBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        setupEvents()
    }

    private fun setupEvents() {

        _binding?.root?.fabAddVehicle?.setOnClickListener {
            findNavController().navigate(R.id.action_vehiclesFragment_to_addVehicleFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}