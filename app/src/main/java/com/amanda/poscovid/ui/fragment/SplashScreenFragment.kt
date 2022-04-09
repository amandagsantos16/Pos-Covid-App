package com.amanda.poscovid.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amanda.poscovid.databinding.FragmentSplashScreenBinding


class SplashScreenFragment : BaseAppFragment() {

    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSplashScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed(Runnable { //This method will be executed once the timer is over
            navigateTo(SplashScreenFragmentDirections.splashScreenParaMain())
        }, 3000)
    }
}