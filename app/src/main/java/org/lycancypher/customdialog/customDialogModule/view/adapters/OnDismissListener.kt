package org.lycancypher.customdialog.customDialogModule.view.adapters

import android.app.Dialog

interface OnDismissListener {
    fun onClickDismiss(type: Int, dialog: Dialog?, msg: String)
}