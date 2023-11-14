package com.nimur.mycoachbasketball.config

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase

class EquipoApp: Application() {
    companion object{
        lateinit var db:EquipoDb
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            EquipoDb::class.java,
            "equipo"
        ).build()
    }
}