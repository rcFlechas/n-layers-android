package com.example.adnceiba.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.adnceiba.R
import com.example.adnceiba.binds.CarBind
import com.example.adnceiba.binds.MotorCycleBind
import com.example.adnceiba.binds.VehicleBind
import com.example.adnceiba.databinding.FragmentAddVehicleBinding
import com.example.adnceiba.extensions.observeEvent
import com.example.adnceiba.extensions.onChange
import com.example.adnceiba.filters.CustomFilter
import com.example.adnceiba.ui.OnError
import com.example.adnceiba.ui.OnLoading
import com.example.adnceiba.ui.OnSuccess
import com.example.adnceiba.utilities.Dialog
import com.example.adnceiba.viewmodels.AddVehicleViewModel
import com.example.domain.entities.Vehicle
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddVehicleFragment : Fragment() {

    private var _binding: FragmentAddVehicleBinding? = null
    private val binding get() = _binding

    private val addVehicleViewModel: AddVehicleViewModel by viewModel()

    private fun saveVehicleLiveDataHandler() {

        addVehicleViewModel.saveLiveData.observeEvent(viewLifecycleOwner) { uiState ->

            when (uiState) {
                is OnLoading -> {
                    isLoading(uiState.loading)
                }
                is OnSuccess -> {
                    isLoading( false)
                    val isSave = uiState.data
                    if (isSave) requireActivity().onBackPressed()
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
        _binding = FragmentAddVehicleBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickEvents()
        setupOnChangeEvents()
        setupFilters()
        saveVehicleLiveDataHandler()
    }

    private fun setupOnChangeEvents() {
        binding?.addRegisterEditText?.onChange { text ->
            isRegisterValid(text)
        }
        binding?.typeRadioGroup?.setOnCheckedChangeListener { _, checkedId ->
            clearCylinderCapacityEditText()
            setVisibilityContentCylinderCapacity(checkedId)
            isTypeVehicleValid(checkedId)
        }
        binding?.addCylinderCapacityEditText?.onChange { text ->
            isCylinderCapacity(text)
        }
    }

    private fun setupFilters() {
        binding?.addRegisterEditText?.filters = arrayOf(
                CustomFilter.emojiFilter,
                CustomFilter.lengthFilter(Vehicle.REGISTER_LENGTH)
        )
    }

    private fun setupClickEvents() {

        binding?.saveVehicleButton?.setOnClickListener {
            if (checkFields()) {
                addVehicleViewModel.save(setupVehicleBind())
            }
        }
    }

    private fun setVisibilityContentCylinderCapacity(checkedId: Int) {
        binding?.contentCylinderCapacity?.visibility = when (checkedId) {
            R.id.option_motorcycle -> View.VISIBLE
            else -> View.GONE
        }
    }

    private fun clearCylinderCapacityEditText() {
        binding?.addCylinderCapacityEditText?.text?.clear()
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

    private fun checkFields(): Boolean {

        val isRegisterValid = isRegisterValid(
            binding?.addRegisterEditText?.text.toString()
        )

        val isTypeVehicleValid = isTypeVehicleValid(binding?.typeRadioGroup?.checkedRadioButtonId)

        return if(binding?.contentCylinderCapacity?.visibility == View.VISIBLE) {

            val isCylinderCapacity = isCylinderCapacity(
                binding?.addCylinderCapacityEditText?.text.toString()
            )

            !(!isRegisterValid || !isTypeVehicleValid || !isCylinderCapacity)
        } else {
            !(!isRegisterValid || !isTypeVehicleValid)
        }
    }

    private fun isRegisterValid(text: String?) = mandatoryFieldBackground(
        field = Pair(binding?.addRegisterEditText, binding?.addRegisterTextView),
        isValid = text?.trim()?.isNotEmpty() ?: false
    )

    private fun isTypeVehicleValid(checkedId: Int?) = mandatoryFieldBackground(
        field = Pair(binding?.typeRadioGroup, binding?.addTypeTextView),
        isValid =  checkedId != IS_NOT_CHECK
    )

    private fun isCylinderCapacity(text: String?) = mandatoryFieldBackground(
        field = Pair(binding?.addCylinderCapacityEditText, binding?.addCylinderCapacityTextView),
        isValid = text?.trim()?.isNotEmpty() ?: false
    )

    private fun mandatoryFieldBackground(field: Pair<View?, TextView?>, isValid: Boolean): Boolean {
        val (element, title) = field
        when (element) {
            is EditText -> {
                if (isValid) {
                    element.setBackgroundColor(resources.getColor(R.color.gray_EEEEEE))
                    title?.setTextColor(resources.getColor(R.color.primaryColor))
                } else {
                    element.background = ContextCompat.getDrawable(requireContext(), R.drawable.border_error_edit_text)
                    title?.setTextColor(ContextCompat.getColor(requireContext(), R.color.red_EB0240))
                }
            }
            is TextView -> {
                if (isValid) {
                    title?.setTextColor(resources.getColor(R.color.primaryColor))
                } else {
                    title?.setTextColor(ContextCompat.getColor(requireContext(), R.color.red_EB0240))
                }
            }
            is RadioGroup -> {
                val radios = ArrayList<RadioButton>()
                for (i in element.childCount downTo 0 step 1) {
                    val view = element.getChildAt(i)
                    if (view is RadioButton) {
                        radios.add(view)
                    }
                }

                if (isValid) {
                    radios.forEach {
                        it.background = ContextCompat.getDrawable(requireContext(), android.R.color.transparent)
                    }
                    title?.setTextColor(resources.getColor(R.color.primaryColor))
                } else {
                    radios.forEach {
                        it.background = ContextCompat.getDrawable(requireContext(), R.drawable.border_switch_form)
                    }
                    title?.setTextColor(ContextCompat.getColor(requireContext(), R.color.red_EB0240))
                }
            }
        }
        return isValid
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

    companion object {
        private const val IS_NOT_CHECK = -1
    }
}