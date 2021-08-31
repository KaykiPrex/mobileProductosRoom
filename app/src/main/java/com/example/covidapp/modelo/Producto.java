package com.example.covidapp.modelo;

import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = Producto.TABLE_NAME , foreignKeys = @ForeignKey(entity = Categoria.class,
                                                                    parentColumns = Categoria.COLUMN_ID,
                                                                    childColumns = "categoriaId",
                                                                    onDelete = ForeignKey.NO_ACTION) )
public class Producto {
    public static final String TABLE_NAME = "producto";
    public static final String COLUMN_ID = BaseColumns._ID;


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    private long id;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "precioPublico")
    private String precioPublico;
    @ColumnInfo(name = "precioProveedor")
    private String precioProveedor;
    @ColumnInfo(name = "stock")
    private boolean stock;
    @ColumnInfo(index = true , name = "categoriaId")
    private long categoriaId;
    @ColumnInfo(name = "imagenid")
    private Integer imagenid;

    @Ignore public Producto() {
    }

    public Producto(String nombre, String precioPublico, String precioProveedor, boolean stock, long categoriaId, Integer imagenid) {
        this.nombre = nombre;
        this.precioPublico = precioPublico;
        this.precioProveedor = precioProveedor;
        this.stock = stock;
        this.categoriaId = categoriaId;
        this.imagenid = imagenid;
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

    public String getPrecioPublico() {
        return precioPublico;
    }

    public void setPrecioPublico(String precioPublico) {
        this.precioPublico = precioPublico;
    }

    public String getPrecioProveedor() {
        return precioProveedor;
    }

    public void setPrecioProveedor(String precioProveedor) {
        this.precioProveedor = precioProveedor;
    }

    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }

    public long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoria(long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Integer getImagenid() {
        return imagenid;
    }

    public void setImagenid(Integer imagenid) {
        this.imagenid = imagenid;
    }
}
