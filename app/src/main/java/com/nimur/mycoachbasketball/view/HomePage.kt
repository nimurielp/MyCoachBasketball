package com.nimur.mycoachbasketball.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nimur.mycoachbasketball.R

class HomePage : AppCompatActivity() {

    private lateinit var cardHome: CardView
    private lateinit var cardTeams: CardView
    private lateinit var cardCalendar: CardView
    private lateinit var cardNotes: CardView
    private lateinit var cardExercises: CardView
    private lateinit var cardLogout: CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        cardHome = findViewById(R.id.cardHome)
        cardTeams = findViewById(R.id.cardTeams)
        cardCalendar = findViewById(R.id.cardCalendar)
        cardNotes = findViewById(R.id.cardNotes)
        cardExercises = findViewById(R.id.cardExercises)
        cardLogout = findViewById(R.id.cardLogout)



        cardHome.setOnClickListener {
            showToast("Home Clicked")
        }

        cardTeams.setOnClickListener {
            showToast("Teams Clicked")
            val intent = Intent(this, TeamsActivity::class.java)
            startActivity(intent)
        }

        cardCalendar.setOnClickListener {
            showToast("Calendar Clicked")
        }

        cardNotes.setOnClickListener {
            showToast("Notes Clicked")
        }

        cardExercises.setOnClickListener {
            showToast("Excercises Clicked")
        }

        cardLogout.setOnClickListener {
            showToast("Logout Clicked")
            Firebase.auth.signOut()


            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}