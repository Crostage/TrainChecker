package com.crostage.trainchecker.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.crostage.trainchecker.R
import com.crostage.trainchecker.data.network.RetrofitBuilder
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RetrofitBuilder.getClient() //убрать куданть в апликаейшон слой

        navigationViewSetup()

    }

    private fun navigationViewSetup() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigationView)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
    }
}

