package org.lycancypher.customdialog.infoModule.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import org.lycancypher.customdialog.BR
import org.lycancypher.customdialog.R
import org.lycancypher.customdialog.databinding.FragmentInfoBinding
import org.lycancypher.customdialog.infoModule.view.adapters.OnClickListenerInfo

class InfoFragment : Fragment(), OnClickListenerInfo {
    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_info, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewmodel()
    }

    private fun setupViewmodel() {
        binding.setVariable(BR.listener, this)
        binding.setVariable(BR.data, InfoFragmentArgs.fromBundle(requireArguments()).data)
    }

    override fun onClickBack() {
        val action = InfoFragmentDirections.actionInfoFragmentToHomeFragment()
        findNavController().navigate(action)
    }
}