package com.nimur.mycoachbasketball.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nimur.mycoachbasketball.model.TeamModel

class TeamsViewModel : ViewModel() {
    private val _teamList = MutableLiveData<List<TeamModel>>()
    val teamList: LiveData<List<TeamModel>> get() = _teamList

    fun fetchTeams() {
        val database = FirebaseDatabase.getInstance()
        val teamsReference = database.getReference("teams")

        teamsReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val teams = mutableListOf<TeamModel>()

                for (teamSnapshot in snapshot.children) {
                    val team = teamSnapshot.getValue(TeamModel::class.java)
                    team?.let { teams.add(it) }
                }

                _teamList.value = teams
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar el error si es necesario
            }
        })
    }
}
