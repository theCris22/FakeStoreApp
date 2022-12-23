package com.crisnavarro.fakestore.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.crisnavarro.fakestore.R
import com.crisnavarro.fakestore.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private var binding: ActivityHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        initViews()

    }

    private fun initViews() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.home_nav_graph_xml) as NavHostFragment
        navHostFragment.navController
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}