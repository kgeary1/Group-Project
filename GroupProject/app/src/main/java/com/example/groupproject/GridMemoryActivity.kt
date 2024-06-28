package com.example.groupproject

import android.content.Context
import android.graphics.Color
import android.graphics.Color.BLACK
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.text.Layout
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setMargins
import org.w3c.dom.Text


class GridMemoryActivity : AppCompatActivity() {

    private lateinit var grid : GridLayout
    private lateinit var gridMem : GridMemory
    private lateinit var rel : RelativeLayout
    private lateinit var tv : TextView
    private var handler : ButtonHandler = ButtonHandler()
    private var width : Int = 0
    private var input : HashSet<GridButton> = HashSet<GridButton>()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("aaaaa", "New Activity")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.grid_memory)

        tv = findViewById(R.id.highScore)

        width = resources.displayMetrics.widthPixels

        Log.i("aaaaa", "Before Build")

        gridMem = GridMemory(this)
        rel = findViewById(R.id.rel1)
        buildGridByCode(gridMem.getSize())
    }

    fun buildGridByCode(size : Int) {

        Log.i("aaaaa", "0")

        grid = GridLayout(this)

        Log.i("aaaaa", "0.0")
        grid.rowCount = size
        grid.columnCount = size

        Log.i("aaaaa", grid.columnCount.toString())

        var w = width/size

        val layoutParams = GridLayout.LayoutParams()
        layoutParams.setMargins(0, 180, 0, 0)

        Log.i("aaaaa", "2")

        var buttons = Array<Array<GridButton>> ( size, { row -> Array<GridButton>( size, { col -> GridButton( this, row, col ) } )} )
        for( i in 0 .. buttons.size - 1 ) {
            for( j in 0 .. buttons[i].size - 1 ) {
                buttons[i][j].background.setTint(Color.CYAN)
                buttons[i][j].setOnClickListener( handler )
                grid.addView( buttons[i][j], w, w )
            }
        }

        Log.i("aaaaa", "3")

        grid.layoutParams = layoutParams
        rel.addView(grid)
        //setContentView(R.layout.grid_memory)
        gridMem.setPattern(buttons)
        showPattern()

        Log.i("aaaaa", "4")


    }

    fun showPattern(){
        var set : HashSet<GridButton> = gridMem.getPattern()
        for(b in set){
            b.background.setTint(BLACK)
        }
        Handler().postDelayed({
            for(b in set){
                b.background.setTint(Color.CYAN)
            }
        }, 3000)

    }

    fun update(button : GridButton){
        button.background.setTint(BLACK)
        if(!gridMem.check(button)){
            reset(button)
        }
        else if(input.size < gridMem.getSquares() - 1){
            input.add(button)
        }
        else if(input.add(button)){
            newLevel()
        }
    }

    fun reset(view: View){
        gridMem = GridMemory(this )
        input = HashSet<GridButton>()
        rel.removeView(grid)
        buildGridByCode(gridMem.getSize())
    }

    fun newLevel(){
        if(gridMem.highScore()) gridMem.setPreferences(this)
        tv.text = buildString {
            append("High Score: ")
            append(gridMem.getScore())
        }
        gridMem.updateSquares()
        gridMem.resetPattern()
        input = HashSet<GridButton>()
        rel.removeView(grid)
        buildGridByCode(gridMem.getSize())
    }

    fun home(view : View) {
        finish()
    }

    inner class ButtonHandler : View.OnClickListener {
        override fun onClick(b : View?){
            var button : GridButton = b as GridButton
            update( button )
        }
    }

}






