package com.example.covidapp.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.covidapp.modelo.Categoria;
import com.example.covidapp.modelo.CategoryWithProducts;

import java.util.List;

@Dao
public interface CategoriaDAO {

    @Transaction
    @Query("SELECT * FROM "+Categoria.TABLENAME)
    List<CategoryWithProducts> getAllCategoriesWithProducts();

    @Transaction
    @Query("SELECT * FROM "+Categoria.TABLENAME+
            " WHERE Categoria.nombre =:category")
    List<CategoryWithProducts> getAllProducts(String category);

    @Query("SELECT COUNT(*) FROM "+ Categoria.TABLENAME)
    int count();

    @Query("SELECT* FROM "+Categoria.TABLENAME)
    List<Categoria> getAllCategories();

    @Insert
    long insert(Categoria categoria);

    @Update
    void update(Categoria categoria);

    @Delete
    void delete(Categoria categoria);
}
