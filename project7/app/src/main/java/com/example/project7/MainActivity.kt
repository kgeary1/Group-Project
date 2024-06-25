package com.example.project7

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import javax.xml.parsers.SAXParserFactory

class MainActivity : AppCompatActivity() {

    private lateinit var gameView : GameView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        var factory = SAXParserFactory.newInstance()
        var parser = factory.newSAXParser()

        var iStream = resources.openRawResource(R.raw.balloons3)
        var handler = SAXHandler()
        parser.parse(iStream,handler)

        // gets the parsed values and creates the balloons
        var ballons = handler.getArray()
        for (item in ballons) {
            Log.w("MainActivity", "" + item.toString())
            gameView = GameView( this, item.getX(),item.getY(), item.getRadius())
            
        }

        setContentView( gameView )

    }
}