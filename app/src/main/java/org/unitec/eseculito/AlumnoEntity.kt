package org.unitec.eseculito

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "alumno")
data class AlumnoEntity(@PrimaryKey
                        @ColumnInfo(name="id") val id: String = UUID.randomUUID().toString(),
                        @ColumnInfo(name = "nombre")val nombre:String,
                        @ColumnInfo(name="email")val email:String)
