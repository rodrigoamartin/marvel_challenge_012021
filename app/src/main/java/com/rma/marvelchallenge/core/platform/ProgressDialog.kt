package com.rma.marvelchallenge.core.platform

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.rma.marvelchallenge.R

class ProgressDialog(private var context: Context?) {

    private var dialog: Dialog? = null

    fun show(){
        if (context != null) {
            if (dialog != null && dialog?.isShowing == true) return
            dialog = Dialog(context!!)
            dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog?.setContentView(R.layout.dialog_progress)
            dialog?.setCancelable(false)
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.show()
        }
    }

    fun dismiss(){
        if (dialog != null && dialog?.isShowing == true && context != null)
            dialog?.dismiss()
    }

    fun attach(context: Context?) {
        this.context = context
    }
}