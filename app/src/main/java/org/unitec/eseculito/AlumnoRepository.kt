package org.unitec.eseculito

import androidx.lifecycle.LiveData

class  AlumnoRepository(private val  dao:AlumnoDao){
    val todosAlumnos: LiveData<List<AlumnoEntity>> = dao.getTodosOrdenados()


    suspend fun insertar(alumno:AlumnoEntity) {
        dao.insertar(alumno)
    }
}