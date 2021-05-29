package com.adityaikhbalm.maps

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.adityaikhbalm.maps.controller.LocationController
import com.adityaikhbalm.maps.databinding.ActivityMapBinding
import com.adityaikhbalm.maps.databinding.ButtonMapBinding
import com.adityaikhbalm.maps.utils.Utility.toastShow
import com.adityaikhbalm.maps.ui.setCustomAppBar
import com.google.android.gms.maps.SupportMapFragment

class MapActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapBinding
    private lateinit var locationController: LocationController
    private var isPaused = false

    private val registrationToAskPermission: ActivityResultLauncher<String> =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            if (it) {
//                locationController.enableLocationUpdate()
            } else {
                toastShow(R.string.location_permission_not_granted)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val button = ButtonMapBinding.bind(binding.root)

        lifecycleScope.setCustomAppBar(binding, button, this, window)
        searchController()

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        locationController = LocationController(
            this, mapFragment, registrationToAskPermission, button
        )
    }

    private fun searchController() {
        binding.searchLayout.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onPause() {
        super.onPause()
        isPaused = true
        locationController.removeLocationUpdate()
    }

    override fun onResume() {
        super.onResume()
//        if (isPaused && locationController.locationPermission && !locationController.dragListener)
//            locationController.enableLocationUpdate()
    }
}