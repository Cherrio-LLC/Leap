package com.cherrio.leap.features.splash

import android.Manifest
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.cherrio.leap.R
import com.cherrio.leap.common.BaseFragment
import com.cherrio.leap.databinding.FragmentSplashBinding

class SplashFragment: BaseFragment<FragmentSplashBinding>(){

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                runSplash()
            } else {
                //Toast Something
            }
        }

    override fun getBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(layoutInflater,container,false)
    }

    override fun useViews() {
        initPermissions()
    }


    private fun initPermissions(){
        when(ContextCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        )) {
            PackageManager.PERMISSION_GRANTED -> {
                runSplash()
            }
            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)

            }

        }
    }
    private fun runSplash(){
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_newsFragment)
        },2000)
    }


}