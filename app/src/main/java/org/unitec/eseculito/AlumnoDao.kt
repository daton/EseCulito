package org.unitec.eseculito

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AlumnoDao{
    @Query("SELECT * from alumno ORDER BY nombre ASC")
    fun getTodosOrdenados(): LiveData<List<AlumnoEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar(alumno: AlumnoEntity)

    @Query("DELETE FROM alumno")
    suspend fun borrarTodos()
}