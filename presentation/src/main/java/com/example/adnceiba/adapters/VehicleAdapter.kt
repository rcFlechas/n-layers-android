package com.example.adnceiba.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adnceiba.binds.MotorCycleBind
import com.example.adnceiba.binds.VehicleBind
import com.example.adnceiba.databinding.ItemVehicleBinding

class VehicleAdapter(
    val clickClosure: (VehicleBind) -> Unit
) : CustomAdapter<VehicleBind, VehicleAdapter.ViewHolder>() {

    private var dataItems = arrayListOf<VehicleBind>()

    fun setData(usersBind: List<VehicleBind>) {
        dataItems.clear()
        dataItems.addAll(usersBind)
        elements.clear()
        elements.addAll(usersBind)
        notifyDataSetChanged()
    }

    fun clearData() {
        this.dataItems.clear()
        this.elements.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemVehicleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return elements.count()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val userBind = elements[position]

        when (holder) {
            is ViewHolder -> holder.bind(userBind)
        }
    }

    inner class ViewHolder(private val binding: ItemVehicleBinding) : BaseViewHolder<VehicleBind>(binding.root) {

        override fun bind(item: VehicleBind) {

            binding.descriptionRegister.text = item.register
            binding.titleLabel.text = item.label
            if (item is MotorCycleBind) {
                binding.contentCylinderCapacity.visibility = View.VISIBLE
                binding.descriptionCylinderCapacity.text = item.cylinderCapacity.toString()
            }
        }
    }
}