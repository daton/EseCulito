package org.unitec.eseculito

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlumnoListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<AlumnoListAdapter.AlumnoViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var alumnos = emptyList<AlumnoEntity>() // Cached copy of words

    inner class AlumnoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreItemView: TextView = itemView.findViewById(R.id.textView)

        //Aqui irian otras vistas invocadas con el view  elemal y el id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumnoViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return AlumnoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlumnoViewHolder, position: Int) {
        val current = alumnos[position]
        holder.nombreItemView.text = current.nombre
    }

    internal fun setAlumnos(alumnos: List<AlumnoEntity>) {
        this.alumnos = alumnos
        notifyDataSetChanged()
    }

    override fun getItemCount() = alumnos.size
}

