package org.lycancypher.customdialog.common.utils

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import org.lycancypher.customdialog.common.utils.Constants.MASK10
import org.lycancypher.customdialog.common.utils.Constants.MASK8
import org.lycancypher.customdialog.common.utils.Constants.MASK9

object TietPhoneMask {
    fun unmask(s: String): String {
        return s.replace("[^0-9]*".toRegex(), "")
    }

    fun insert(editText: TextInputEditText): TextWatcher {
        return object : TextWatcher {
            var isUpdating = false
            var old = ""

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val str = unmask(s.toString())
                val mask: String
                val defaultMask = getDefaultMask(str)

                mask = when (str.length) {
                    10 -> MASK10
                    9 -> MASK9
                    else -> defaultMask
                }

                var mascara = ""
                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }
                var i = 0
                for (m in mask.toCharArray()) {
                    if (m != '#' && str.length > old.length || m != '#' && str.length < old.length && str.length != i) {
                        mascara += m
                        continue
                    }
                    mascara += try {
                        str[i]
                    } catch (e: java.lang.Exception) {
                        break
                    }
                    i++
                }
                isUpdating = true
                editText.setText(mascara)
                editText.setSelection(mascara.length)
            }

            override fun afterTextChanged(s: Editable?) {}
        }
    }

    private fun getDefaultMask(str: String): String {
        var defaultMask = MASK8
        if (str.length > 10) {
            defaultMask = MASK10
        }
        return defaultMask
    }
}