package com.nimur.mycoachbasketball.view


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.nimur.mycoachbasketball.adaptadores.EquipoAdapter
import com.nimur.mycoachbasketball.config.Constantes
import com.nimur.mycoachbasketball.databinding.ActivityViewTeamBinding
import com.nimur.mycoachbasketball.dialogos.BorrarDialogo
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

        binding.lifecycleOwner = this
        binding.modelo = viewModel

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

        binding.etBuscar.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(s.toString().isNotEmpty()){
                    viewModel.buscarEquipo()
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }


}
