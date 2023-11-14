package com.nimur.mycoachbasketball.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nimur.mycoachbasketball.R
import com.nimur.mycoachbasketball.viewmodel.TeamsViewModel

class TeamsActivity : AppCompatActivity() {
    private lateinit var teamsViewModel: TeamsViewModel
    private lateinit var addTeam: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_team)
        addTeam = findViewById(R.id.addTeamFab)

        teamsViewModel = ViewModelProvider(this).get(TeamsViewModel::class.java)

        // Observa cambios en la lista de equipos.
        teamsViewModel.teamList.observe(this, Observer { teamList ->
            // Actualiza la interfaz de usuario con la lista de equipos recuperada.
            // Por ejemplo, puedes usar un RecyclerView para mostrar la lista.
        })

        // Llama al método fetchTeams en el ViewModel para iniciar la recuperación.
        teamsViewModel.fetchTeams()


        addTeam.setOnClickListener {
            // Inicia la actividad AddTeamActivity al hacer clic en el botón flotante
            val intent = Intent(this@TeamsActivity, AddTeamActivity::class.java)
            startActivity(intent)
        }
    }
}
