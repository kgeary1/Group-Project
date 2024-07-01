package com.example.groupproject

import android.annotation.SuppressLint
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.location.Geocoder.GeocodeListener
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlin.random.Random

class MapGameActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map : GoogleMap
    private lateinit var timer : CountDownTimer
    private lateinit var timerView : TextView
    private lateinit var latLngView : TextView
    private lateinit var playerClick : TextView
    private lateinit var mapScore : TextView
    private lateinit var currLatLng : LatLng
    private lateinit var mapGame : MapGame
    private lateinit var actual : Marker
    private lateinit var guess : Marker
    private var started : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_map)
        currLatLng = LatLng(0.0, 0.0)
        timerView = findViewById(R.id.timer)
        latLngView = findViewById(R.id.location)
        playerClick = findViewById(R.id.player_click)
        mapScore = findViewById(R.id.map_score)
        mapGame = MapGame(this)

        mapScore.text = "Total Score:\n" + String.format("%.2f", mapGame.getScore())

        Toast.makeText(this, "Tap the map and get as close to the target as possible!", Toast.LENGTH_LONG).show()

        val mapFragment : SupportMapFragment = supportFragmentManager.findFragmentById( R.id.map ) as SupportMapFragment

        mapFragment.getMapAsync( this )
    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0
        val mh : MapClickHandler = MapClickHandler()
        map.setOnMapClickListener(mh)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(37.0, -96.0), 4.0f))
    }

    inner class MapClickHandler : GoogleMap.OnMapClickListener {
        override fun onMapClick(p0: LatLng) {
            currLatLng = p0
            Log.w("MapGameActivity", p0.toString())
            playerClick.text = "Your Tap\nLat: " + String.format("%.2f", p0.latitude) + "\nLon: " + String.format("%.2f", p0.longitude)
        }

    }

    fun endMessage(bool : Boolean, dist : Double) {
        if(bool) {
            Toast.makeText(this, "You Win! Your offset was: " + String.format("%.2f", dist), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "You Lose! Your offset was: " + String.format("%.2f", dist), Toast.LENGTH_LONG).show()
        }
    }

    fun goHome(view: View) {
        finish()
    }

    fun setPref() {
        mapGame.setPreferences(this)
    }

    fun startGame(view: View) {
        val lat : Double = Random.nextDouble(mapGame.bottom, mapGame.top)
        val lon : Double = Random.nextDouble(mapGame.left, mapGame.right)
        if(started) {
            actual.remove()
            guess.remove()
        }
        playerClick.text = ""
        latLngView.text = "Target:\nLat: " + String.format("%.2f", lat) + "\nLon: " + String.format("%.2f", lon)
        timerView.text = "20"
        timer = object : CountDownTimer(20000, 1) {
            override fun onTick(millisUntilFinished: Long) {
                timerView.text = (millisUntilFinished/1000.0).toString()
                if (mapGame.checkDistance(lat, lon, currLatLng.latitude, currLatLng.longitude)) {
                    onFinish()
                    this.cancel()
                }
            }

            override fun onFinish() {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat, lon), 4.0f))
                actual = map.addMarker(MarkerOptions().position(LatLng(lat, lon)).title("Actual Place"))!!
                guess = map.addMarker(MarkerOptions().position(currLatLng).title("Last User Click"))!!
                started = true
                val dist : Double = mapGame.calculateDistance(lat, lon, currLatLng.latitude, currLatLng.longitude)
                if (mapGame.checkDistance(lat, lon, currLatLng.latitude, currLatLng.longitude)) {
                    endMessage(true, dist)
                } else {
                    endMessage(false, dist)
                    timerView.text = (0.000).toString()
                }
                mapGame.setScore(mapGame.getScore() + dist)
                setPref()
                mapScore.text = "Total Score:\n" + String.format("%.2f", mapGame.getScore())
                currLatLng = LatLng(mapGame.bottom - 1, mapGame.right + 1)
            }
        }
        timer.start()
        timerView.text = ""
    }
}