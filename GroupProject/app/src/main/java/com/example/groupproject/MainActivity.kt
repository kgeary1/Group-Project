package com.example.groupproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge

import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var b1 : Button
    private lateinit var line : EditText
    private lateinit var the_message : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //since we hav
        setContentView(R.layout.activity_main)
        var myIntent: Intent = Intent(this, NumberTestActivity::class.java)
        startActivity(myIntent)
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

