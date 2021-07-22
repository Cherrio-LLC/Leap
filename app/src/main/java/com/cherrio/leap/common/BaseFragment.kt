package com.cherrio.leap.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B: ViewBinding>: Fragment() {
    private var _binding: B? = null
    protected val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getBinding(inflater,container)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        useViews()
    }

    abstract fun getBinding(layoutInflater: LayoutInflater, container: ViewGroup?): B
    abstract fun useViews()

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()

    }
}