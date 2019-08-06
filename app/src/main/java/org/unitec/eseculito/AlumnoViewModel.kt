package org.unitec.eseculito

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch

class AlumnoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AlumnoRepository
 
    val todosAlumnos: LiveData<List<AlumnoEntity>>

    init {
        val alumnoDao = AlumnoRoomDatabase.getDatabase(application, viewModelScope).alumnoDao()
        repository = AlumnoRepository(alumnoDao)
        todosAlumnos = repository.todosAlumnos
    }

    /**
     * 
     */
    fun insertar(alumno: AlumnoEntity) = viewModelScope.launch {
        repository.insertar(alumno)
    }
}
