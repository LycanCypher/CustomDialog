package org.lycancypher.customdialog.common.utils

import android.content.Context
import com.google.android.material.textfield.TextInputLayout
import org.lycancypher.customdialog.R

object Utils {
    fun getIcon(type: Int?) =
        when (type) {
            100 -> {
                R.drawable.ic_success
            }
            200 -> {
                R.drawable.ic_question
            }
            300 -> {
                R.drawable.ic_alert
            }
            400 -> {
                R.drawable.ic_write
            }
            else -> {
                R.drawable.ic_error
            }
        }


    fun getBackgroundIcon(type: Int?) =
        when (type) {
            100 -> {
               R.drawable.background_icon_success
            }
            200 -> {
                R.drawable.background_icon_question
            }
            300 -> {
                R.drawable.background_icon_alert
            }
            400 -> {
                R.drawable.background_icon_input
            }
            else -> {
                R.drawable.background_icon_error
            }
        }

    fun getColor(type: Int?) =
        when (type) {
            100 -> {
                R.color.success_dialog
            }
            200 -> {
                R.color.confirm_dialog
            }
            300 -> {
                R.color.alert_dialog
            }
            400 -> {
                R.color.input_dialog
            }
            else -> {
                R.color.error_dialog
            }
        }

    fun getTitle(type: Int?) =
        when (type) {
            100 -> {
                R.string.title_success
            }
            200 -> {
                R.string.title_confirm
            }
            300 -> {
                R.string.title_caution
            }
            400 -> {
                R.string.title_input
            }
            else -> {
                R.string.title_error
            }
        }

    fun validateFields(mContext: Context, vararg textFields: TextInputLayout): Boolean {
        var isValid = true

        for (textField in textFields) {
            if (textField.editText?.text.toString().trim().isEmpty()) {
                textField.error = mContext.getString(R.string.requerido)
                textField.editText!!.requestFocus()
                isValid = false
            } else {
                textField.error = null
            }
        }
        return isValid
    }

    fun getDeviceWidth(mContext: Context, factor: Float): Float {
        var width: Float
        mContext.resources.displayMetrics.let {
            val dpWidth = it.widthPixels / it.density
            width = dpWidth / factor
        }
        return width
    }

    fun getDeviceHeight(mContext: Context, factor: Float): Float {
        var height: Float
        mContext.resources.displayMetrics.let {
            val dpHeight = it.heightPixels / it.density
            height = dpHeight / factor
        }
        return height
    }
}