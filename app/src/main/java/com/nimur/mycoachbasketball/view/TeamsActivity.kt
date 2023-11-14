package com.nimur.mycoachbasketball.view


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.nimur.mycoachbasketball.adaptadores.EquipoAdapter
import com.nimur.mycoachbasketball.config.Constantes
import com.nimur.mycoachbasketball.databinding.ActivityViewTeamBinding
import com.nimur.mycoachbasketball.ui.AddTeamActivity
import com.nimur.mycoachbasketball.viewmodel.MainViewModel


class TeamsActivity : AppCompatActivity() {
    lateinit var binding: ActivityViewTeamBinding
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this).get()
        viewModel.iniciar()

        binding.teamRecyclerView.apply{
            layoutManager = LinearLayoutManager(applicationContext)
        }

        viewModel.equipoList.observe(this, Observer {
            binding.teamRecyclerView.adapter = EquipoAdapter(it)
        })

        binding.addTeamFab.setOnClickListener {
            val intent = Intent(this, AddTeamActivity::class.java)
            intent.putExtra(Constantes.OPERACION_KEY,Constantes.OPERACION_INSERTAR)
            startActivity(intent)
        }

    }
}
