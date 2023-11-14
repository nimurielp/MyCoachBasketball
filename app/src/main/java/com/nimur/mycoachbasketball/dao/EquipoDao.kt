package com.nimur.mycoachbasketball.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nimur.mycoachbasketball.model.entidades.Equipo

@Dao
interface EquipoDao {

    @Query("SELECT * FROM Equipo")
    suspend fun getAll():List<Equipo>

    @Query("SELECT * FROM Equipo WHERE idEquipo = :id")
    suspend fun getById(id:Long):Equipo

    @Query("SELECT * FROM Equipo WHERE nombreEquipo LIKE '%' || :name || '%' OR categoriaEquipo LIKE '%' || :name || '%'")
    suspend fun getByName(name:String):List<Equipo>

    @Insert
    suspend fun insert(equipos:List<Equipo>):List<Long>

    @Update
    suspend fun update(equipo: Equipo):Int

    @Delete
    suspend fun delete(equipo: Equipo):Int
}