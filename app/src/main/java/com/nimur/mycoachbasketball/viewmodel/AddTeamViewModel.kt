package com.nimur.mycoachbasketball.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nimur.mycoachbasketball.config.Constantes
import com.nimur.mycoachbasketball.config.EquipoApp.Companion.db
import com.nimur.mycoachbasketball.model.entidades.Equipo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AddTeamViewModel : ViewModel() {

    var id = MutableLiveData<Long>()
    var nombre = MutableLiveData<String>()
    var categoria = MutableLiveData<String>()
    var operacion = Constantes.OPERACION_INSERTAR
    var operacionExitosa = MutableLiveData<Boolean>()

    fun guardarEquipo(){
        if (validarInformacion()){

            var mEquipo = Equipo(0,nombre.value!!,categoria.value!!)
            when(operacion){
                Constantes.OPERACION_INSERTAR -> {
                    viewModelScope.launch {
                        val result = withContext(Dispatchers.IO){
                            db.equipoDao().insert(
                                arrayListOf<Equipo>(mEquipo)
                            )

                        }
                        operacionExitosa.value = result.isNotEmpty()
                    }

                }
                Constantes.OPERACION_EDITAR->{
                    mEquipo.idEquipo = id.value!!
                    viewModelScope.launch{
                        val result = withContext(Dispatchers.IO){
                            db.equipoDao().update(mEquipo)
                        }
                        operacionExitosa.value = (result>0)
                    }
                }

                Constantes.OPERACION_ELIMINAR->{
                    mEquipo.idEquipo = id.value!!
                    viewModelScope.launch{
                        val result = withContext(Dispatchers.IO){
                            db.equipoDao().delete(mEquipo)
                        }
                        operacionExitosa.value = (result>0)
                    }
                }

            }

        } else {
            operacionExitosa.value = false

        }

    }

    fun cargarDatos() {
        viewModelScope.launch {
            var equipo = withContext(Dispatchers.IO){
                db.equipoDao().getById(id.value!!)

            }

            nombre.value = equipo.nombreEquipo
            categoria.value = equipo.categoriaEquipo

        }
    }


    private fun validarInformacion():Boolean {
        //devuelve true si la información no es nula ni vacía
        return !( nombre.value.isNullOrEmpty() ||
                categoria.value.isNullOrEmpty()
                )
    }

    fun eliminarEquipo() {
        var mEquipo = Equipo(id.value!!,"","")
        viewModelScope.launch {
            var result = withContext(Dispatchers.IO){
                db.equipoDao().delete(mEquipo)
            }
            operacionExitosa.value = (result>0)
        }
    }
}
