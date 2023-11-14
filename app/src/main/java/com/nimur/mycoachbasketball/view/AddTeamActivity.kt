package com.nimur.mycoachbasketball.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.nimur.mycoachbasketball.R
import com.nimur.mycoachbasketball.viewmodel.AddTeamViewModel

class AddTeamActivity : AppCompatActivity() {
    private lateinit var saveButton: Button
    private lateinit var teamNameEditText: TextInputEditText
    private lateinit var teamCategoryEditText: TextInputEditText
    private lateinit var numberOfPlayersEditText: TextInputEditText
    private lateinit var addTeamViewModel: AddTeamViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_team)

        addTeamViewModel = ViewModelProvider(this).get(AddTeamViewModel::class.java)

        saveButton = findViewById(R.id.saveButton)
        teamNameEditText = findViewById(R.id.teamNameEditText)
        teamCategoryEditText = findViewById(R.id.teamCategoryEditText)
        numberOfPlayersEditText = findViewById(R.id.numberOfPlayersEditText)

        saveButton.setOnClickListener {
            val teamName = teamNameEditText.text.toString()
            val teamCategory = teamCategoryEditText.text.toString()
            val numberOfPlayers = numberOfPlayersEditText.text.toString()

            // Observa cambios en el resultado de agregar el equipo.
            addTeamViewModel.teamAdded.observe(this, Observer { teamAdded ->
                if (teamAdded) {
                    finish() // Cerrar la actividad después de guardar
                } else {
                    // Manejar el caso de fallo si es necesario
                }
            })

            // Llama al método addTeam en el ViewModel.
            addTeamViewModel.addTeam(teamName, teamCategory, numberOfPlayers)
        }
    }
}
