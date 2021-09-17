package com.crostage.trainchecker.presentation.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.crostage.trainchecker.R
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Главная активити приложения, содержит контейнер для фрагментов и панель табов
 *
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

