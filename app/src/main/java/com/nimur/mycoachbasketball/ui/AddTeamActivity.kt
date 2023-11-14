package com.nimur.mycoachbasketball.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.google.android.material.textfield.TextInputEditText
import com.nimur.mycoachbasketball.R
import com.nimur.mycoachbasketball.config.Constantes
import com.nimur.mycoachbasketball.databinding.ActivityAddTeamBinding
import com.nimur.mycoachbasketball.dialogos.BorrarDialogo
import com.nimur.mycoachbasketball.view.TeamsActivity
import com.nimur.mycoachbasketball.viewmodel.AddTeamViewModel

class AddTeamActivity : AppCompatActivity(), BorrarDialogo.BorrarListener {
    lateinit var binding: ActivityAddTeamBinding
    lateinit var viewModel: AddTeamViewModel
    lateinit var dialogoBorrar: BorrarDialogo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dialogoBorrar = BorrarDialogo(this)
        viewModel = ViewModelProvider(this).get()
        viewModel.operacion = intent.getStringExtra(Constantes.OPERACION_KEY)!!
        binding.modelo = viewModel
        binding.lifecycleOwner = this

        viewModel.operacionExitosa.observe(this, Observer {
            if(it){
                mostrarMensaje("Operación Exitosa")
                irAVerEquipos()
            }else {
                mostrarMensaje("Ocurrió un error")
            }
        })

        if(viewModel.operacion.equals(Constantes.OPERACION_EDITAR)){
            viewModel.id.value = intent.getLongExtra(Constantes.ID_EQUIPO_KEY,0)
            viewModel.cargarDatos()
            binding.linearEditar.visibility = View.VISIBLE
            binding.saveButton.visibility = View.GONE
        } else {
            binding.linearEditar.visibility = View.GONE
            binding.saveButton.visibility = View.VISIBLE
        }

        binding.deleteButton.setOnClickListener {
            mostrarDialogo()
        }
    }

    private fun irAVerEquipos() {
        val intent = Intent(applicationContext,TeamsActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun mostrarMensaje(s: String) {
        Toast.makeText(applicationContext,s,Toast.LENGTH_LONG).show()
    }

    override fun borrarEquipo() {
        viewModel.eliminarEquipo()
    }

    private fun mostrarDialogo(){
        dialogoBorrar.show(supportFragmentManager,"Dialogo Borrar")
    }
}
