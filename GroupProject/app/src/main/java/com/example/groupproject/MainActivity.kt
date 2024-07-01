
package com.example.groupproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.*
import java.util.HashMap

class MainActivity : AppCompatActivity() {

    companion object {
        internal const val PREF_SCORE_GRID:
                String = "grid high score"
        internal const val PREF_SCORE_NUM :
                String = "num high score"
        internal const val PREF_SCORE_MAP :
                String = "map high score"
        lateinit var key : String
        var score : Int = 0
        var addData = false
    }

    private lateinit var b1 : Button
    private lateinit var line : EditText
    private lateinit var the_message : TextView
    private lateinit var ad : InterstitialAd
    private lateinit var firebase : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        firebase = Firebase.firestore
        val fb = firebase.collection("fb")

    }

    fun launchNumTest(view : View){
        var myIntent: Intent = Intent(this, NumberTestActivity::class.java)
        startActivity(myIntent)
    }

    fun launchGridTest(view : View){
        var intent : Intent = Intent( this, GridMemoryActivity::class.java)
        this.startActivity(intent)
    }

    fun launchMapGame(view : View){
        var intent : Intent = Intent( this, MapGameActivity::class.java)
        this.startActivity(intent)
    }

    fun launchStatView(view : View){
        var intent : Intent = Intent( this, StatViewActivity::class.java)
        this.startActivity(intent)
    }

    fun modData() {
        var res = 0
        if (res == 0) {
            var myIntent: Intent = Intent(this, NumberTestActivity::class.java)
            startActivity(myIntent)
        } else {
            the_message.text = "NUMBER MUST BE BETWEEN 1 AND 25"
        }
    }

    inner class AdLoad : InterstitialAdLoadCallback() {

        override fun onAdLoaded(p0: InterstitialAd) {
            super.onAdLoaded(p0)
            ad = p0
            ad.show(this@MainActivity)
        }

        override fun onAdFailedToLoad(p0: LoadAdError) {
            super.onAdFailedToLoad(p0)
        }
    }

    override fun onRestart() {
        super.onRestart()
        var adUnitId : String = "ca-app-pub-3940256099942544/1033173712"
        var adRequest : AdRequest = AdRequest.Builder().build()
        var adLoad : AdLoad = AdLoad()
        InterstitialAd.load( this, adUnitId, adRequest, adLoad )


        if(addData) {
            var data = HashMap<String, Int>()
            data.put(key, score)
            firebase.collection("fb").add(data)
                .addOnSuccessListener { documentReference ->
                    Log.d("MainActivity", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("MainActivity", "Error adding document", e)
                }
        }
    }
}
