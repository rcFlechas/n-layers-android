package com.example.adnceiba.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.adnceiba.R
import com.example.adnceiba.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        configNav()
    }
    private fun configNav() {
        NavigationUI.setupWithNavController(binding.bottomNavigation, Navigation.findNavController(this, R.id.nav_host_fragment))
    }
}