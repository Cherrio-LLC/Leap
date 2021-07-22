package com.cherrio.leap.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.cherrio.leap.R
import com.cherrio.leap.common.BaseFragment
import com.cherrio.leap.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>() {

    override fun getBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater,container,false)
    }

    override fun useViews() {
        val navhost = childFragmentManager.findFragmentById(R.id.bottom_nav_frag) as? NavHostFragment
        val controller = navhost?.navController

        controller?.graph?.forEach { graph ->
                println("Destination ${graph.label}")
            }

        binding.bottomNav.setupWithNavController(controller!!)
        NavigationUI.setupWithNavController(binding.bottomNav,controller)
    }

}