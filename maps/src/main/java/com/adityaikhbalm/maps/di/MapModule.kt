package com.adityaikhbalm.maps.di

import com.adityaikhbalm.maps.MapActivity
import com.adityaikhbalm.maps.R
import com.adityaikhbalm.maps.controller.LocationController
import com.google.android.gms.maps.SupportMapFragment
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val mapModule = module {
//    scope<MapActivity>{
//        scoped { params ->
//            LocationController(
//                androidContext(),
//                params.get(),
//                params.get(),
//                params.get()
//            )
//        }
//    }
}