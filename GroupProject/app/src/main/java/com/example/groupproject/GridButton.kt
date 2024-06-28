package com.example.groupproject

import android.content.Context
import androidx.appcompat.widget.AppCompatButton

class GridButton : AppCompatButton {
    private var row : Int = 0
    private var col : Int  = 0

    constructor( context : Context, row: Int, col : Int ) : super( context ) {

        setRow( row )
        setCol( col )
    }

    fun setRow( row : Int ) {
        if( row >= 0  )
            this.row = row
    }

    fun setCol( col : Int ) {
        if( col >= 0 )
            this.col = col
    }

    fun getRow( ) : Int {
        return row
    }

    fun getCol( ) : Int {
        return col
    }
}