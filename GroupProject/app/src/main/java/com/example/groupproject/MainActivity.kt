package com.example.groupproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

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
}