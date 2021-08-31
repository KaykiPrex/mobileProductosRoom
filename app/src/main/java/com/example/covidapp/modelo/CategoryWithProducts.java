package com.example.covidapp.modelo;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CategoryWithProducts {
    @Embedded public Categoria categoria;
    @Relation(
            parentColumn = Categoria.COLUMN_ID,
            entityColumn = "categoriaId"
    )
    public List<Producto> productos ;
}
