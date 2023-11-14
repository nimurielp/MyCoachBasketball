package com.nimur.mycoachbasketball.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nimur.mycoachbasketball.config.EquipoApp.Companion.db
import com.nimur.mycoachbasketball.model.entidades.Equipo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel:ViewModel() {
    val equipoList = MutableLiveData<List<Equipo>?>()
    var parametroBusqueda = MutableLiveData<String>()


    fun iniciar() {

        viewModelScope.launch {
            equipoList.value = withContext(Dispatchers.IO) {
                /* db.equipoDao().insert(arrayListOf<Equipo>(
                    Equipo(0,"C.P. Bosco Servitopo", "Cadete"),
                    Equipo(0,"C.P. Bosco Exaclean", "Junior")
                ))*/
                db.equipoDao().getAll()
            }


            }

        }

    }
