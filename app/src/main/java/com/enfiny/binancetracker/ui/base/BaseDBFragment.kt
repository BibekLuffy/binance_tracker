package com.enfiny.binancetracker.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.bibekluffy.binancetracker.BR

abstract class BaseDBFragment<VM : ViewModel, B : ViewDataBinding> :
    Fragment() {

    protected lateinit var binding: B
    protected lateinit var baseViewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseViewModel = getViewModel()
        binding = getFragmentBinding(inflater, container)
        binding.apply {
            setVariable(getBindingVariable(), baseViewModel)
            lifecycleOwner = viewLifecycleOwner
            executePendingBindings()
        }
        return binding.root
    }


    /**
     * get view model
     */
    abstract fun getViewModel(): VM

    /**
     * get view binding
     */
    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    open fun getBindingVariable(): Int = BR.viewModel
}