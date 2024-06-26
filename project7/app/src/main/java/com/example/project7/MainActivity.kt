//Kyle Geary, David Abili, Rithvik Malladi

package com.example.project7

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import javax.xml.parsers.SAXParserFactory


class MainActivity : AppCompatActivity() {

    private lateinit var gameView : GameView
    private var sbHeight : Int = 0
    private var balloons = Balloons()

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
            var balloon = Balloon( item.getX(), item.getY(), item.getRadius())
            balloons.addBalloon(balloon)
        }

        var resourceId : Int = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            this.sbHeight = getResources().getDimensionPixelSize(resourceId);
        }

        gameView = GameView(this, balloons)

        setContentView( gameView )

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        if(event.action == MotionEvent.ACTION_DOWN && balloons.tap(event, this.sbHeight)){
            gameView.postInvalidate()
            if(balloons.getWon()){
                val toast : Toast = Toast.makeText( this, "YOU WON",
                    Toast.LENGTH_LONG )
                toast.show()
            }
            return true
        }
        else{
            if(balloons.getTaps() == 0 && !balloons.getWon()) finish()
        }
        return false
    }
}