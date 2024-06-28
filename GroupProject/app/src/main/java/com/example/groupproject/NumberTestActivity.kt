package com.example.groupproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NumberTestActivity : AppCompatActivity() {

    private lateinit var back_button : Button
    private lateinit var confirm_button : Button
    private lateinit var enter_num : EditText
    private lateinit var num_test : Number_Mem_Test
    private lateinit var score : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number)

        back_button = findViewById(R.id.back_button)
        confirm_button = findViewById(R.id.confirm_num)
        enter_num = findViewById(R.id.edit_num)
        score = findViewById(R.id.score)

        confirm_button.setOnClickListener { check_number() }
        back_button.setOnClickListener { back() }

        num_test = Number_Mem_Test()
        num_test.gen_Number(this)

    }

    fun check_number() {
        if(num_test.check(enter_num.text.toString().toInt())) {
            num_test.gen_Number(this)
            score.text = "Score : " + num_test.updateScore()
        } else {
            back()
        }
    }

    fun back() {
        finish()
    }

}