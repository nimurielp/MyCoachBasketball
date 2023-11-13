package com.nimur.mycoachbasketball.model.entidades

import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Equipo(
    @PrimaryKey(autoGenerate = true)
    val idEquipo: Long = 0L,
    var nombreEquipo: String,
    var categoriaEquipo: String,
    var LogoEquipo: Image
)