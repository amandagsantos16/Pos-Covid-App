package com.amanda.poscovid.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.amanda.poscovid.R
import com.amanda.poscovid.databinding.FragmentHomeBinding
import com.amanda.poscovid.ui.fragment.BaseAppFragment

class HomeFragment : BaseAppFragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraBottomNavController()
    }

    private fun configuraBottomNavController() {
        val navController = findNavController(binding.root.getViewById(R.id.home_fragment))
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.bem_vindo, R.id.login, R.id.cadastrarLogin))

        setupActionBarWithNavController(activity as AppCompatActivity, navController, appBarConfiguration)
        binding.mainNavView.setupWithNavController(navController)
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

}