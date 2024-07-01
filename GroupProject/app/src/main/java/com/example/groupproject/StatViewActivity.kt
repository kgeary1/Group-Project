package com.example.groupproject

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.AggregateField
import com.google.firebase.firestore.AggregateSource
import com.google.firebase.firestore.*

class StatViewActivity : AppCompatActivity() {

    private lateinit var gridStat : TextView
    private lateinit var numStat : TextView
    private lateinit var mapStat : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.stat_view)

        gridStat = findViewById(R.id.grid_score)
        numStat = findViewById(R.id.num_score)
        mapStat = findViewById(R.id.map_score)

        var firebase = Firebase.firestore

        val fb = firebase.collection("fb")

        val query = firebase.collection("fb")

        var aggregateQuery = query.aggregate(AggregateField.average("grid"))
        aggregateQuery.get(AggregateSource.SERVER).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Aggregate fetched successfully
                val snapshot = task.result
                gridStat.text = buildString {
                            append("Average grid high score : ")
                            append(snapshot.get(AggregateField.average("grid")))
                }
            } else {
                Log.d("MainActivity", "Aggregate failed: ", task.getException())
            }
        }

        aggregateQuery = query.aggregate(AggregateField.average("num"))
        aggregateQuery.get(AggregateSource.SERVER).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Aggregate fetched successfully
                val snapshot = task.result
                numStat.text = buildString {
                    append("Average num high score : ")
                    append(snapshot.get(AggregateField.average("num")))
                }
            } else {
                Log.d("MainActivity", "Aggregate failed: ", task.getException())
            }
        }

        aggregateQuery = query.aggregate(AggregateField.average("map"))
        aggregateQuery.get(AggregateSource.SERVER).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Aggregate fetched successfully
                val snapshot = task.result
                mapStat.text = buildString {
                    append("Average map total score : ")
                    append(String.format("%.2f", snapshot.get(AggregateField.average("map"))))
                }
            } else {
                Log.d("MainActivity", "Aggregate failed: ", task.getException())
            }
        }

    }

    fun goBack(view : View){
        MainActivity.addData = false
        finish()
    }

}