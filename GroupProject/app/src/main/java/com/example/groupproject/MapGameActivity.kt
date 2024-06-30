package com.example.groupproject

import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.location.Geocoder.GeocodeListener
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapGameActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map : GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_map)

        var mapFragment : SupportMapFragment = supportFragmentManager.findFragmentById( R.id.map ) as SupportMapFragment

        mapFragment.getMapAsync( this )
    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0
        var whiteHouse : LatLng = LatLng( 38.8977, -77.0366  )
        var camera : CameraUpdate = CameraUpdateFactory.newLatLngZoom( whiteHouse, 18.0f )
        map.moveCamera( camera)
    }

    fun goHome(view: View) {
        finish()
    }


}