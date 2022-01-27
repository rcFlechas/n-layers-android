package com.example.adnceiba.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.adnceiba.R
import com.example.adnceiba.binds.PlaceBind
import com.example.adnceiba.databinding.ItemPlaceBinding
import com.example.domain.enum.State
import com.example.domain.extensions.dateFormat

class PlaceAdapter(
    val longClickClosure: (PlaceBind) -> Unit
) : CustomAdapter<PlaceBind, PlaceAdapter.ViewHolder>() {

    private var dataItems = arrayListOf<PlaceBind>()

    fun setData(placesBind: List<PlaceBind>) {
        dataItems.clear()
        dataItems.addAll(placesBind)
        elements.clear()
        elements.addAll(placesBind)
        notifyDataSetChanged()
    }

    fun clearData() {
        this.dataItems.clear()
        this.elements.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return elements.count()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val placeBind = elements[position]

        when (holder) {
            is ViewHolder -> {
                holder.bind(placeBind)
                holder.events(placeBind)
            }
        }
    }

    inner class ViewHolder(private val binding: ItemPlaceBinding) : BaseViewHolder<PlaceBind>(binding.root) {

        override fun bind(item: PlaceBind) {

            binding.descriptionVehicle.text = item.vehicle.register
            binding.descriptionType.text = "TODO"
            binding.descriptionDateBusy.text = item.timeBusy.busyDate.dateFormat("dd/MM/yyyy HH:mm")
            binding.descriptionDateFree.text = item.timeBusy.busyDate.dateFormat("dd/MM/yyyy HH:mm")
            binding.descriptionTotalPay.text = "${item.totalPay} COP"
            binding.titleLabel.text = item.state.name
        }

        fun events(item: PlaceBind) {

            if (item.state == State.BUSY) {

                binding.root.setOnCreateContextMenuListener { menu, _, _ ->
                    val deleteOption = menu.add(R.string.title_button_free)
                    deleteOption.setOnMenuItemClickListener {
                        longClickClosure(item)
                        false
                    }
                }
            }
        }
    }
}