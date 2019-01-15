package com.miwoapp.starwarsweather

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var locationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestLocationpermission()
        getUserLocation()
    }

    private fun getUserLocation() {
        locationClient = LocationServices.getFusedLocationProviderClient(this)

        if(ActivityCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestLocationpermission()
        } else {
            locationClient.lastLocation.addOnSuccessListener { location ->
                getUserCity(location.longitude, location.latitude)
            }
        }
    }

    private fun getUserCity(longitude: Double, latitude: Double) {
        val geocoder = Geocoder(this@MainActivity, Locale.getDefault())
        var addresses: ArrayList<Address> = geocoder.getFromLocation(latitude, longitude, 1) as ArrayList<Address>

        if(addresses.size > 0){
            val city = addresses.get(0)
            city
        }
    }

    private fun requestLocationpermission() {
        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)
    }
}

