package org.lycancypher.customdialog.customDialogModule.view

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.window.layout.WindowMetricsCalculator
import org.lycancypher.customdialog.BR
import org.lycancypher.customdialog.R
import org.lycancypher.customdialog.common.utils.TietPhoneMask
import org.lycancypher.customdialog.common.utils.Utils.validateFields
import org.lycancypher.customdialog.customDialogModule.view.adapters.OnClickListener
import org.lycancypher.customdialog.customDialogModule.view.adapters.OnDismissListener
import org.lycancypher.customdialog.databinding.FragmentCustomDialogBinding
import java.util.*

class CustomDialogFragment(
    private val message: String,
    private val tipo: Int,
    private val pista: String,
    private val listener: OnDismissListener
) : DialogFragment(), OnClickListener {
    private lateinit var binding: FragmentCustomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_custom_dialog, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewmodel()
    }

    override fun onStart() {
        super.onStart()

        if (showsDialog) {
            setupSize()
        }
        if (pista.isNotEmpty() && pista.lowercase(Locale.getDefault()).contains("telefónico")) {
            binding.tietText.addTextChangedListener(TietPhoneMask.insert(binding.tietText))
        } else {
            binding.tietText.addTextChangedListener { validateFields(requireContext(), binding.tilText) }
        }
    }

    private fun setupViewmodel() {
        //NOTA: no funciona with(binding)
        binding.setVariable(BR.listener, this)
        binding.setVariable(BR.type, tipo)
        binding.setVariable(BR.msg, message)
        binding.setVariable(BR.hint, pista)
        binding.setVariable(BR.textoApariencia, resources.getBoolean(R.bool.texto_apariencia_xxxhdpi))
    }

    private fun setupSize() {
        val windowMetrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(requireActivity())
        val currentBounds = windowMetrics.bounds
        val width = currentBounds.width()
        val height = currentBounds.height()
        dialog?.window?.setLayout(width, height)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(requireContext(), android.R.color.transparent)))
    }

    override fun onClickOk(ok: Boolean) {
        if (ok) {
            if (tipo == 400) {
                if (validateFields(requireContext(), binding.tilText)) {
                    listener.onClickDismiss(tipo, dialog, binding.tietText.text.toString())
                } else {
                    dismiss()
                }
            } else {
                listener.onClickDismiss(tipo, dialog, binding.tietText.text.toString())
            }
        } else {
            dismiss()
        }
    }

}