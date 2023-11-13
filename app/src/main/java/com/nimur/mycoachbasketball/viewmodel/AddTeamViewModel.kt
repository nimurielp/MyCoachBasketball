package com.nimur.mycoachbasketball.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.nimur.mycoachbasketball.model.TeamModel

class AddTeamViewModel : ViewModel() {
    private val _teamAdded = MutableLiveData<Boolean>()
    val teamAdded: LiveData<Boolean> get() = _teamAdded

    fun addTeam(teamName: String, teamCategory: String, numberOfPlayers: String) {
        val database = FirebaseDatabase.getInstance()
        val teamsReference = database.getReference("teams")

        val teamId = teamsReference.push().key // Genera una clave única para el equipo

        val team = TeamModel(teamName, teamCategory, numberOfPlayers)
        teamId?.let {
            teamsReference.child(it).setValue(team)
            _teamAdded.value = true
        }
    }
}
