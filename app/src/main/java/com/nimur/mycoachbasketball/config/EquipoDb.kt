package com.nimur.mycoachbasketball.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nimur.mycoachbasketball.dao.EquipoDao
import com.nimur.mycoachbasketball.model.entidades.Equipo

@Database(
    entities = [Equipo::class],
    version = 1
)
abstract class EquipoDb:RoomDatabase() {
    abstract fun equipoDao():EquipoDao
}