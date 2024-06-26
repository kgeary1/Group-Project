package com.example.project7

import android.view.MotionEvent

class Balloons {

    private var balloons : ArrayList<Balloon> = ArrayList<Balloon>()
    private var tapsLeft : Int = 3
    private var won : Boolean = false

    fun addBalloon(balloon: Balloon) {
        this.balloons.add(balloon)
        this.tapsLeft++
    }

    fun getBalloons() : ArrayList<Balloon> {
        return this.balloons
    }

    fun getWon() : Boolean {
        return won
    }

    fun tap(event : MotionEvent, sbHeight : Int) : Boolean {
        val x = event.x
        val y = event.y
        tapsLeft--

        for(b in balloons){
            var distX = (x - b.getX()).toDouble()
            var distY = (y - (b.getY() + sbHeight)).toDouble()

            if(Math.pow(distX, 2.0) + Math.pow(distY, 2.0) <= Math.pow(b.getRadius().toDouble(), 2.0)){
                balloons.remove(b)

                if(balloons.size == 0){
                    won = true
                }

                return true
            }
        }
        return false
    }

    fun getTaps() : Int {
        return this.tapsLeft
    }
}