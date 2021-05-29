package com.adityaikhbalm.maps.controller

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.adityaikhbalm.maps.databinding.ButtonMapBinding
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class LocationController(
    private val context: Context,
    mapFragment: SupportMapFragment?,
    private val registrationToAskPermission: ActivityResultLauncher<String>,
    private val button: ButtonMapBinding,
) : OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var latLng: LatLng
    private var locationMarker: Marker? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    var dragListener = false

    init {
        mapFragment?.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap ?: return

        enableLocation()
        enableButton()
        markerListener()
    }

    private fun enableLocation() {
        @RequiresApi(Build.VERSION_CODES.M)
        if (locationPermission) {
            if (PermissionUtils.isLocationEnabled(context)) {
//                enableLocationUpdate()
            } else {
                PermissionUtils.showGPSNotEnabledDialog(context)
            }
        } else {
            registrationToAskPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    @SuppressLint("MissingPermission")
    fun enableLocationUpdate() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        val locationRequest = LocationRequest().setInterval(10000).setFastestInterval(10000)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)

        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.myLooper()
        )
    }

    fun removeLocationUpdate() {
        val fused = ::fusedLocationProviderClient.isInitialized
        if (fused) fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            for (location in locationResult.locations) {
                if (locationMarker != null) {
                    locationMarker?.remove()
                }

                latLng = LatLng(location.latitude, location.longitude)
                locationMarker = map.addMarker(
                    MarkerOptions().position(latLng).draggable(true)
                )
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            }
        }
    }

    private fun enableButton() {
        button.btnGps.setOnClickListener {
            val gps = ::latLng.isInitialized
            if (gps) map.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        }

        button.btnZoomIn.setOnClickListener {
            map.animateCamera(CameraUpdateFactory.zoomIn())
        }

        button.btnZoomOut.setOnClickListener {
            map.animateCamera(CameraUpdateFactory.zoomOut())
        }
    }

    private fun markerListener() {
        map.setOnMarkerDragListener(object : OnMarkerDragListener {
            override fun onMarkerDragStart(marker: Marker?) {
                dragListener = true
                removeLocationUpdate()
            }

            override fun onMarkerDrag(marker: Marker?) {
                Log.d(this.javaClass.name, "drag")
            }

            override fun onMarkerDragEnd(marker: Marker?) {
                marker?.position?.let {
                    latLng = it
                    map.animateCamera(CameraUpdateFactory.newLatLng(it))
                }
            }
        })
    }

    val locationPermission =
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED
}