package com.example.covidapp.modelo;

import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity( tableName = Categoria.TABLENAME)
public class Categoria {
    public static final String TABLENAME = "categoria";
    public static final String COLUMN_ID = BaseColumns._ID;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    private long id ;
    @ColumnInfo(name = "nombre")
    private String nombre ;
    @ColumnInfo(name = "descripcion")
    private String descripcion;
    @ColumnInfo(name = "detalle")
    private String detalle;
    @ColumnInfo(name = "imagenId")
    private int imagenId;


    public Categoria(String nombre, String descripcion, String detalle, int imagenId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.detalle = detalle;
        this.imagenId = imagenId;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }
}
