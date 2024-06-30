package com.example.groupproject

class MapGame {
    private var closestDistance : Double = -1.0
    var right : Double = -66.95
    var left : Double = -124.67
    var top : Double = 49.38
    var bottom : Double = 25.84

    fun getClosestDistance() : Double {
        return closestDistance
    }

    fun setClosestDistance(cd : Double) {
        closestDistance = cd
    }

    fun checkDistance(lat1 : Double, long1 : Double, lat2 : Double, long2 : Double) : Boolean {
        if(calculateDistance(lat1, long1, lat2, long2) <= 10) {
            return true
        } else {
            return false
        }
    }

    fun calculateDistance (lat1 : Double, long1 : Double, lat2 : Double, long2 : Double) : Double {
        return Math.sqrt(Math.pow(lat2 - lat1, 2.0) + Math.pow(long2 - long1, 2.0))
    }

}