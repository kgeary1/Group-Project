package com.example.project7


class Balloon {
    private var x: Int = 0
    private var y: Int = 0
    private var rad: Int = 0

    constructor(newX: Int, newY: Int, newRad : Int) {
        setX(newX)
        setY(newY)
        setRadius(newRad)
    }

    fun setX(newTitle: Int) {
        x = newTitle
    }

    fun setY(newLink: Int) {
        y = newLink
    }

    fun setRadius(newLink: Int) {
        rad = newLink
    }


    fun getX(): Int {
        return x
    }

    fun getY(): Int {
        return y
    }

    fun getRadius(): Int {
        return rad
    }

    override fun toString(): String {
        return "$x; $y; $rad"
    }

}