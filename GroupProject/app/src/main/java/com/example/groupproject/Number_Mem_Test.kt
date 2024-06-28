package com.example.groupproject

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import java.security.KeyStore.TrustedCertificateEntry
import kotlin.random.Random

class Number_Mem_Test {

    private var num_amount = 1
    private var score = 0
    private var best_score = 0
    private var the_number = 0

//    constructor(context: Context) {
//        var pref : SharedPreferences =
//            context.getSharedPreferences( context.packageName + "_preferences" , Context.MODE_PRIVATE )
//        setScore( pref.getInt(PREFERENCE_SCORE.toString(), 0 ) )
//    }
//
//
//    fun setScore(s : Int) {
//        best_score = s
//    }

    constructor() {

    }

    fun updateScore() : Int {
        score += 1
        return score
    }


    fun gen_Number(context : Context) {
        var num = ""
        var i = 0

        while (i < num_amount) {
            num += "" + Random.nextInt(1, 10)
            i += 1
        }

        Toast.makeText(context, "" + num, Toast.LENGTH_SHORT).show()
        num_amount += 1
        the_number = num.toInt()
    }

    fun check(num : Int) : Boolean {
        if (num == the_number)
            return true
        else
            return false
    }

//    fun setPreferences( context : Context ) {
//        var pref : SharedPreferences =
//            context.getSharedPreferences( context.packageName + "_preferences" , Context.MODE_PRIVATE )
//        var editor : SharedPreferences.Editor = pref.edit()
//
//        editor.putInt(PREFERENCE_SCORE, best_score )
//
//        editor.commit()
//    }
//    companion object {
//        private const val PREFERENCE_SCORE : String = "score"
//    }

}