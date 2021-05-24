package com.enfiny.binancetracker.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseDBFragment<VM : ViewModel, B : ViewDataBinding> : Fragment() {
    private lateinit var binding: ViewDataBinding
    private lateinit var baseViewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(inflater, container)
        baseViewModel = getViewModel()
        return binding.root
    }

    abstract fun getViewModel(): VM

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B
}