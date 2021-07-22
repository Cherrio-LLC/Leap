package com.cherrio.leap.features.splash

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cherrio.leap.R
import com.cherrio.leap.common.BaseFragment
import com.cherrio.leap.databinding.FragmentSplashBinding

class SplashFragment: BaseFragment<FragmentSplashBinding>(){

    override fun getBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(layoutInflater,container,false)
    }

    override fun useViews() {
       Handler(Looper.getMainLooper()).postDelayed({
          findNavController().navigate(R.id.action_splashFragment_to_newsFragment)
        },2000)
    }
}