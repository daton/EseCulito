package org.unitec.eseculito


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new_alumno.*

class NewAlumnoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_alumno)




        boton_guardar.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(texytoNombre.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val nombre = texytoNombre.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, nombre)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}
