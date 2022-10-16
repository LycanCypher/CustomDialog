package org.lycancypher.customdialog.homeModule.view

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import org.lycancypher.customdialog.BR
import org.lycancypher.customdialog.R
import org.lycancypher.customdialog.customDialogModule.view.CustomDialogFragment
import org.lycancypher.customdialog.customDialogModule.view.adapters.OnDismissListener
import org.lycancypher.customdialog.databinding.FragmentHomeBinding
import org.lycancypher.customdialog.homeModule.view.adapters.OnClickListener
import org.lycancypher.customdialog.mainModule.view.adapters.OnClickListenerMain

class HomeFragment : Fragment(), OnClickListener, OnDismissListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var listener: OnClickListenerMain

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupListeners()

    }

    private fun setupViewModel() {
        binding.setVariable(BR.listener, this)
    }

    private fun setupListeners() {
        listener = activity as OnClickListenerMain
    }



    override fun onClickSuccess() {
        listener.showDialog(CustomDialogFragment(getString(R.string.msg_success), 100, "", this))
    }

    override fun onClickError() {
        listener.showDialog(CustomDialogFragment(getString(R.string.msg_error), 500, "", this))
    }

    override fun onClickAlert() {
        listener.showDialog(CustomDialogFragment(getString(R.string.msg_caution), 300, "", this))
    }

    override fun onClickConfirm() {
        listener.showDialog(CustomDialogFragment(getString(R.string.msg_question), 200, "", this))
    }

    override fun onClickInput(text: Boolean) {
        if (text) {
            listener.showDialog(CustomDialogFragment("", 400, getString(R.string.hint_name), this))
        } else {
            listener.showDialog(CustomDialogFragment("", 400, getString(R.string.hint_phone), this))
        }
    }

    override fun onClickDismiss(type: Int, dialog: Dialog?, msg: String) {
        if (type == 200) {
            Snackbar.make(binding.root, getString(R.string.action_released), Snackbar.LENGTH_LONG).show()
        } else if (type == 400) {
            val action = HomeFragmentDirections.actionHomeFragmentToInfoFragment(
                msg
            )
            findNavController().navigate(action)
        } else {

        }
        dialog?.dismiss()
    }
}