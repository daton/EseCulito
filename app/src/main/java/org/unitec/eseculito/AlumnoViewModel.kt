package org.unitec.eseculito

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch

class AlumnoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AlumnoRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val todosAlumnos: LiveData<List<AlumnoEntity>>

    init {
        val alumnoDao = AlumnoRoomDatabase.getDatabase(application, viewModelScope).alumnoDao()
        repository = AlumnoRepository(alumnoDao)
        todosAlumnos = repository.todosAlumnos
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertar(alumno: AlumnoEntity) = viewModelScope.launch {
        repository.insertar(alumno)
    }
}
