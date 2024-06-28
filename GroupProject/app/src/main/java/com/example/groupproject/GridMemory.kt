package com.example.groupproject

import android.content.Context
import android.content.SharedPreferences
import kotlin.random.Random

class GridMemory {

    companion object {
        private const val PREF_PATTERN_SCORE:
                String = "high score"
    }

    private var size = 3
    private var squares = 3
    private var pattern : HashSet<GridButton> = HashSet<GridButton>()
    private var score : Int = 0

    constructor( context : Context? ){

        val pref: SharedPreferences =
            context!!.getSharedPreferences(context.packageName +
                    "_preferences", Context.MODE_PRIVATE)

        score = pref.getInt(PREF_PATTERN_SCORE, 0)

    }

    fun setPreferences( context : Context ) {
        val pref: SharedPreferences =
            context.getSharedPreferences( context.packageName + "_preferences", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = pref.edit()

        editor.putInt( PREF_PATTERN_SCORE, score )

        editor.commit()
    }

    fun highScore() : Boolean{
        if(squares > score){
            score = squares
            return true
        }
        return false
    }

    fun getScore() : Int{
        return score
    }

    fun getSquares() : Int{
        return this.squares
    }

    fun updateSquares(){
        squares++
        if(squares > (size * size)/2){
            size++
        }
    }

    fun getSize() : Int{
        return size
    }

    fun setPattern(buttons : Array<Array<GridButton>>){
        while(pattern.size < squares){
            var i = (0..size - 1).random()
            var j = (0..size - 1).random()
            pattern.add(buttons[i][j])
        }
    }

    fun getPattern() : HashSet<GridButton>{
        return this.pattern
    }

    fun resetPattern(){
        pattern = HashSet<GridButton>()
    }

    fun check(button : GridButton) : Boolean {
        if(pattern.contains(button)){
            return true
        }
        return false
    }

}