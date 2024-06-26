package com.example.project7

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.View
import android.widget.RelativeLayout

class GameView : View {
    private var x : ArrayList<Int> = ArrayList<Int> ()
    private var y : ArrayList<Int> = ArrayList<Int> ()
    private var radius : ArrayList<Int> = ArrayList<Int> ()
    private lateinit var paint : Paint
    private lateinit var balloons : Balloons
    private lateinit var rl: RelativeLayout

    constructor(context : Context, x :  ArrayList<Int>, y :  ArrayList<Int>, rad :  ArrayList<Int>) : super (context) {
        paint = Paint()
        paint.color = Color.BLUE
        paint.strokeWidth = 20.0f
        paint.isAntiAlias = true

        this.x = x
        this.y = y
        this.radius = rad

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var i = 0

        while (i < x.size) {
            canvas.drawCircle(x.get(i).toFloat() , y.get(i).toFloat(), radius.get(i).toFloat(), paint)
            i += 1
        }

    }
}