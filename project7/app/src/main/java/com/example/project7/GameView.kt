package com.example.project7

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class GameView : View {
    private var paint : Paint
    private var balloons : Balloons

    constructor(context : Context, balloons : Balloons) : super (context) {
        paint = Paint()
        paint.color = Color.BLUE
        paint.strokeWidth = 20.0f
        paint.isAntiAlias = true

        this.balloons = balloons
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var b = balloons.getBalloons()
        for(balloon in b) {
            canvas.drawCircle(balloon.getX().toFloat(), balloon.getY().toFloat(), balloon.getRadius().toFloat(), paint)
        }
    }
}