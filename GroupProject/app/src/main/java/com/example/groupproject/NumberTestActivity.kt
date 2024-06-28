package com.example.groupproject

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class NumberTestActivity : AppCompatActivity() {

    private lateinit var back_button : Button
    private lateinit var confirm_button : Button
    private lateinit var enter_num : EditText
    private lateinit var num_test : Number_Mem_Test
    private lateinit var score : TextView
    private lateinit var rnd_num : TextView
    private lateinit var pBar : ProgressBar
    private lateinit var timer : CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number)

        back_button = findViewById(R.id.back_button)
        confirm_button = findViewById(R.id.confirm_num)
        enter_num = findViewById(R.id.edit_num)
        score = findViewById(R.id.score)
        rnd_num = findViewById(R.id.rndNum)
        pBar = findViewById(R.id.determinateBar)

        confirm_button.setOnClickListener { check_number() }
        back_button.setOnClickListener { back() }

        resetNum()

    }

    fun showNum() {
        rnd_num.text = num_test.gen_Number(this).toString()
        hideNum()
    }

    fun hideNum() {
        var i = 0
        timer = object : CountDownTimer(3000, 50) {
            override fun onTick(millisUntilFinished: Long) {
                Log.v("Log_tag", "Tick of Progress$i$millisUntilFinished")
                i++
                pBar.setProgress(i as Int * 100 / (3000 / 60))
            }

            override fun onFinish() {
                i++
                pBar.setProgress(100)
                rnd_num.text = ""
                enter_num.setEnabled(true)
                enter_num.inputType = 2
                enter_num.requestFocus()
            }
        }
        timer.start()
    }

    fun resetNum() {
        num_test = Number_Mem_Test(this)
        enter_num.text.clear()
        enter_num.setEnabled(false)
        enter_num.inputType = EditorInfo.TYPE_NULL
        score.text = buildString {
            append("Score : ")
            append(0)
            append("\n")
            append("High Score : ")
            append(num_test.getScore())
        }
        showNum()
    }

    fun check_number() {
        if(num_test.check(enter_num.text.toString().toInt())) {
            enter_num.text.clear()
            enter_num.setEnabled(false)
            enter_num.inputType = EditorInfo.TYPE_NULL
            if(num_test.highScore()) num_test.setPreferences(this)
            score.text = buildString {
                append("Score : ")
                append(num_test.getNumAmount())
                append("\n")
                append("High Score : ")
                append(num_test.getScore())
            }

            showNum()
        } else {
            resetNum()
        }
    }

    fun back() {
        finish()
    }

}