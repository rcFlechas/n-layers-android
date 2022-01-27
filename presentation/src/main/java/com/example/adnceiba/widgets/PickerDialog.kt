package com.example.adnceiba.widgets

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.adnceiba.ItemPicker
import com.example.adnceiba.R
import java.util.concurrent.atomic.AtomicBoolean

class PickerDialog<T : ItemPicker> : DialogFragment() {

    private var selected: T? = null
    private var items: ArrayList<T> = ArrayList()
    internal var title: String? = null
    internal var listener: (T?) -> Unit = {}
    internal var listenerView: (T?) -> Unit = {}
    internal var button: Button? = null
    internal var gravity: Int? = null
    internal var onDismiss = {}

    private val isShowing = AtomicBoolean(false)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val adapter: ArrayAdapter<T> = ArrayAdapter(requireContext(), R.layout.select_dialog_singlechoice)
        for (item in items) {
            adapter.add(item)
        }

        val dialog = AlertDialog.Builder(requireContext(), R.style.DialogTheme)
            .setTitle(title)
            .setSingleChoiceItems(adapter, adapter.getPosition(selected)) { dialog, which ->
                selected = adapter.getItem(which)
                button?.text = selected?.title ?: ""
                listener(selected)
                listenerView(selected)
                dialog.dismiss()
            }
            .setCancelable(true)
            .create()

        gravity?.let {
            setPositionDialog(dialog, gravity = it)
        }

        dialog.show()

        val titleView = dialog.findViewById<TextView>(androidx.appcompat.R.id.alertTitle)
        titleView?.textSize = 18F

        return dialog
    }

    override fun show(manager: FragmentManager, tag: String?) {
        isShowing.set(true)
        super.show(manager, tag)
    }

    override fun onResume() {
        super.onResume()
        isShowing.set(true)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        gravity?.let {
            onDismiss()
        }
        isShowing.set(false)
    }

    fun getIsShowing(): Boolean {
        return isShowing.get()
    }

    fun setSelected(id: Long, execAction: Boolean = true) {
        if (id == -1L) {
            button?.text = ""
            selected = null
            if (execAction) {
                listener(selected)
            }
            listenerView(selected)
        } else {
            for (item in items) {
                if (item.id == id) {
                    selected = item
                    button?.text = item.title
                    if (execAction) {
                        listener(selected)
                    }
                    listenerView(selected)
                    break
                }
            }
        }
    }

    fun getSelected(): T? {
        return selected
    }

    fun setData(items: ArrayList<T>) {
        this.items = ArrayList(items)
    }

    private fun setPositionDialog(dialog: Dialog,x: Int = 0, y: Int = 0,gravity: Int = 0) {
        val windowDialog = dialog.window
        windowDialog?.let {
            val windowParameter: WindowManager.LayoutParams = it.attributes
            if (x == 0 || y == 0){
                windowParameter.gravity = gravity
            }else{
                windowParameter.x = x
                windowParameter.y = y
            }
            windowParameter.flags = windowParameter.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
            it.attributes = windowParameter
        }
    }
}