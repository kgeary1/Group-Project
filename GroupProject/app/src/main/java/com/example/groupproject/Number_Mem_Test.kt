package com.example.groupproject

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import java.security.KeyStore.TrustedCertificateEntry
import kotlin.random.Random

class Number_Mem_Test {


    private var num_amount = 0
    private var score = 0
    private var the_number = 0

    constructor(context: Context) {
        var pref : SharedPreferences =
            context.getSharedPreferences( context.packageName + "_preferences" , Context.MODE_PRIVATE )

        score = pref.getInt(MainActivity.PREF_SCORE_NUM, 0 )
    }

    fun getScore() : Int {
        return score
    }

    fun highScore() : Boolean {
        if(num_amount > score) {
            score = num_amount
            return true
        }
        return false
    }

    fun getNumAmount() : Int {
        return num_amount
    }


    fun gen_Number(context : Context) : Int{
        var num = ""
        var i = 0

        num_amount++

        while (i < num_amount) {
            num += "" + Random.nextInt(1, 10)
            i++
        }

        the_number = num.toInt()

        return the_number
    }

    fun check(num : Int) : Boolean {
        if (num == the_number)
            return true
        else
            return false
    }

    fun setPreferences( context : Context ) {
        var pref : SharedPreferences =
            context.getSharedPreferences( context.packageName + "_preferences" , Context.MODE_PRIVATE )
        var editor : SharedPreferences.Editor = pref.edit()

        editor.putInt(MainActivity.PREF_SCORE_NUM, score )

        editor.commit()
    }

}