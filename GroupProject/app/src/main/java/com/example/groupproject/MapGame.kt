package com.example.groupproject

import android.content.Context
import android.content.SharedPreferences

class MapGame {
    private var score : Double = 0.0
    var right : Double = 180.0
    var left : Double = -180.0
    var top : Double = 80.0
    var bottom : Double = -80.0

    constructor(context: Context) {
        val pref : SharedPreferences =
            context.getSharedPreferences( context.packageName + "_preferences" , Context.MODE_PRIVATE )

        score = pref.getFloat(MainActivity.PREF_SCORE_MAP, 0.0F).toDouble()
    }

    fun getScore() : Double {
        return score
    }

    fun setScore(cd : Double) {
        score = cd
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

    fun setPreferences( context : Context ) {
        val pref : SharedPreferences = context.getSharedPreferences( context.packageName + "_preferences" , Context.MODE_PRIVATE )
        val editor : SharedPreferences.Editor = pref.edit()

        editor.putFloat(MainActivity.PREF_SCORE_MAP, score.toFloat())

        editor.commit()
    }

}