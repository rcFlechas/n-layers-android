package com.example.adnceiba.utilities

import android.content.Context
import android.content.DialogInterface
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.adnceiba.R

object Dialog {

    fun basic(context: Context, message: String, title: Int = R.string.title_alert_warning, titleButtonPositive: Int = R.string.title_button_ok, closure: (() -> Unit)? = null) {
        val dialog = AlertDialog.Builder(context, R.style.DialogTheme)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(titleButtonPositive) { dialog, _ ->
                dialog.dismiss()
                closure?.invoke()
            }
            .create()

        dialog.show()

        val messageView = dialog.findViewById<TextView>(android.R.id.message)
        messageView?.gravity = Gravity.CENTER

        val titleView = dialog.findViewById<TextView>(androidx.appcompat.R.id.alertTitle)
        titleView?.textSize = 18F

        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
    }
}