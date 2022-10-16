package org.lycancypher.customdialog.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import org.lycancypher.customdialog.R
import org.lycancypher.customdialog.databinding.ActivityMainBinding
import org.lycancypher.customdialog.mainModule.view.adapters.OnClickListenerMain

class MainActivity : AppCompatActivity(), OnClickListenerMain {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupNavigationView()
    }

    fun setupNavigationView() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        title = navController.currentDestination?.label as String
    }

    override fun showDialog(dialogFragment: DialogFragment) {
        supportFragmentManager.findFragmentByTag("Dialog").let { fragment ->
            fragment ?: let {
                supportFragmentManager.beginTransaction().let { transition ->
                    dialogFragment.show(transition, "Dialog")
                }
            }
        }
    }
}