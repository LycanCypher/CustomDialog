package org.lycancypher.customdialog.common.adapters

import android.text.InputType
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import androidx.core.widget.TextViewCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.resources.TextAppearance
import com.google.android.material.textfield.TextInputEditText
import org.lycancypher.customdialog.R
import java.util.*

@BindingAdapter("isGone")
fun bindingIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) View.GONE else View.VISIBLE
}

@BindingAdapter("isGoneT")
fun bindIsGoneT(view: View, type: Int) {
    view.visibility = if (type == 200 || type == 400) View.VISIBLE else View.GONE
}

@BindingAdapter("layout_constraintGuide_percent")
fun setLayoutConstraintGuidePercent(view: Guideline, type: Int) {
    val params = view.layoutParams as ConstraintLayout.LayoutParams
    when (view.id) {
        R.id.guide1bV -> {
            val percent = if (type == 200 || type == 400) 0.52f else 0.05f
            params.guidePercent = percent
        }
        R.id.guide1H -> {
            val percent = if (type == 400) 0.25f else 0.35f
            params.guidePercent = percent
        }
    }
    view.layoutParams = params
}

@BindingAdapter("layout_constraintGuide_percent2")
fun setLayoutConstraintGuidePercent2(view: Guideline, type: Int) {
    val percent = if (type == 400) 0.25f else 0.35f
    val params = view.layoutParams as ConstraintLayout.LayoutParams
    params.guidePercent = percent
    view.layoutParams = params
}

@BindingAdapter("apariencia_texto")
fun setTextAppareance(view: TextView, active: Boolean) {
    if (active) {
        TextViewCompat.setTextAppearance(view, R.style.TextAppearance_CustomDialog_Caption)
    } else {
        TextViewCompat.setTextAppearance(view, R.style.TextAppearance_CustomDialog_Body1)
    }
}

@BindingAdapter("android:inputType")
fun setInputType(view: TextInputEditText, hint: String) {
    if (hint.lowercase(Locale.getDefault()).contains("telefónico")) {
        view.inputType = InputType.TYPE_CLASS_PHONE
    } else {
        view.inputType = InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
    }
}

@BindingAdapter("android:layout_width")
fun setLayoutWidth(view: ImageView, width: Float) {
    val layoutParams = view.layoutParams
    layoutParams.width = (width * view.resources.displayMetrics.density).toInt()
    view.layoutParams = layoutParams
    view.invalidate()
}

@BindingAdapter("android:layout_height")
fun setLayoutHeight(view: ImageView, height: Float) {
    val layoutParams = view.layoutParams
    layoutParams.height = (height * view.resources.displayMetrics.density).toInt()
    view.layoutParams = layoutParams
    view.invalidate()
}




