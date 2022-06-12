package com.amanda.poscovid.ui.activity

import android.app.ProgressDialog
import android.app.ProgressDialog.STYLE_HORIZONTAL
import android.app.ProgressDialog.STYLE_SPINNER
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.amanda.poscovid.R
import com.amanda.poscovid.databinding.ActivityHomeBinding
import com.amanda.poscovid.preferences.ITokenPreferenceHelper
import com.amanda.poscovid.preferences.IUserPreferenceHelper
import com.amanda.poscovid.ui.fragment.home.HomeFragmentDirections
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    private val userPreference by inject<IUserPreferenceHelper>()
    private val tokenPreference by inject<ITokenPreferenceHelper>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHome.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_home)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.home, R.id.nav_agenda, R.id.nav_logoff, R.id.nav_login), binding.drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_login -> {
                    startActivity(
                        Intent(this, LoginActivity::class.java)
                    )
                }
                R.id.nav_logoff -> {
                    tokenPreference.clearPrefs()
                    userPreference.clearPrefs()
                    recreate()
                }
                R.id.nav_agenda -> {

                }
            }
            false
        }
    }

    override fun onResume() {
        super.onResume()
        setNavHeader()
        setNavMenu()
    }

    private fun setNavHeader() {
        val headerView = binding.navView.getHeaderView(0)
        val headerTextView = headerView.findViewById<TextView>(R.id.nav_header_textView)

        if (userPreference.email.isNotEmpty()) {
            headerTextView.visibility = VISIBLE
            headerTextView.text = userPreference.email
        } else {
            headerTextView.visibility = GONE
        }
    }

    private fun setNavMenu() {
        binding.navView.menu.let { menu ->
            menu.findItem(R.id.nav_agenda)?.isVisible = userPreference.id.isNotEmpty()
            menu.findItem(R.id.nav_logoff)?.isVisible = userPreference.id.isNotEmpty()
            menu.findItem(R.id.nav_login)?.isVisible = userPreference.id.isEmpty()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}