package com.adit.sibili

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =  supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController


        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        setupWithNavController(bottomNav, navController)


//        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black))
//        window.statusBarColor = ContextCompat.getColor(this, R.color.black)



    }
}