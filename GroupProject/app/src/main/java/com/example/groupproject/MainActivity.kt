
package com.example.groupproject

import android.content.Intent
import android.os.Bundle
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

class MainActivity : AppCompatActivity() {

    companion object {
        internal const val PREF_SCORE_GRID:
                String = "grid high score"
        internal const val PREF_SCORE_NUM :
                String = "num high score"
    }

    private lateinit var b1 : Button
    private lateinit var line : EditText
    private lateinit var the_message : TextView
    private lateinit var ad : InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
    }

    fun launchNumTest(view : View){
        var myIntent: Intent = Intent(this, NumberTestActivity::class.java)
        startActivity(myIntent)
    }

    fun launchGridTest(view : View){
        var intent : Intent = Intent( this, GridMemoryActivity::class.java)
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
    }
}
