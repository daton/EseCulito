package org.unitec.eseculito

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {
 val nuevaActovityCodigoRequerido=1
    private lateinit var alumnoViewModel:AlumnoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


  val recucleView=  recyclerview as RecyclerView
        val adaptador=AlumnoListAdapter(this)
  recucleView.adapter=adaptador
        recucleView.layoutManager= LinearLayoutManager(this)

        alumnoViewModel=ViewModelProviders.of(this)
            .get(AlumnoViewModel::class.java)

        alumnoViewModel.todosAlumnos.observe(this, Observer{alumnos->
            alumnos?.let{adaptador.setAlumnos(it)}
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewAlumnoActivity::class.java)
            startActivityForResult(intent, nuevaActovityCodigoRequerido)
        }

        }



    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == nuevaActovityCodigoRequerido && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val alumno = AlumnoEntity(data.getStringExtra(NewAlumnoActivity.EXTRA_REPLY),data.getStringExtra(NewAlumnoActivity.EXTRA_REPLY),data.getStringExtra(NewAlumnoActivity.EXTRA_REPLY))
                alumnoViewModel.insertar(alumno)
            }
        } else {
            Toast.makeText(
                applicationContext,
                "no guardardo",
                Toast.LENGTH_LONG
            ).show()
        }
    }


}
