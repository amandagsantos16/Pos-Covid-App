package com.amanda.poscovid.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.amanda.poscovid.R
import com.amanda.poscovid.databinding.ActivityHomeBinding
import com.amanda.poscovid.preferences.ITokenPreferenceHelper
import com.amanda.poscovid.preferences.IUserPreferenceHelper
import com.amanda.poscovid.ui.viewModel.HomeViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var binding: ActivityHomeBinding
    private val userPreference by inject<IUserPreferenceHelper>()
    private val tokenPreference by inject<ITokenPreferenceHelper>()
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHome.toolbar)

        navController = findNavController(R.id.nav_host_fragment_content_home)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.home, R.id.nav_logoff, R.id.nav_login), binding.drawerLayout)
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
            }
            false
        }
        navController.addOnDestinationChangedListener { _, _, _ ->
            if (isLoagado()) viewModel.atualizaNotificacoes()
        }
    }

    override fun onResume() {
        super.onResume()
        setNavHeader()
        setNavMenu()
        if (::notificacaoItem.isInitialized) {
            validMenuItem()
        }
    }

    private fun validMenuItem() {
        notificacaoItem.isVisible = isLoagado()
    }

    private fun setNavHeader() {
        val headerView = binding.navView.getHeaderView(0)
        val headerTextView = headerView.findViewById<TextView>(R.id.nav_header_textView)

        if (isLoagado()) {
            headerTextView.visibility = VISIBLE
            headerTextView.text = userPreference.email
        } else {
            headerTextView.visibility = GONE
        }
    }

    private fun isLoagado() = userPreference.email.isNotEmpty()

    private fun setNavMenu() {
        binding.navView.menu.let { menu ->
            menu.findItem(R.id.nav_logoff)?.isVisible = isLoagado()
            menu.findItem(R.id.nav_login)?.isVisible = !isLoagado()
        }
    }

    private lateinit var notificacaoItem: MenuItem

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        notificacaoItem = menu.findItem(R.id.menu_notificacao)
        notificacaoItem.setOnMenuItemClickListener {
            viewModel.notificacoesLidas()
            navController.navigate(R.id.action_global_notificacoes)
            true
        }
        validMenuItem()
        viewModel.temNotificacao.observe(this) {
            notificacaoItem.setIcon(if (it) R.drawable.ic_notification_active else R.drawable.ic_notification)
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}