package com.example.covidapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.covidapp.database.DAO.CategoriaDAO;
import com.example.covidapp.database.DAO.ProductoDAO;
import com.example.covidapp.modelo.Categoria;
import com.example.covidapp.modelo.Producto;

@Database(entities = {Producto.class , Categoria.class}, version = 5 ,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    private static String DB_NAME = "dbProductos";
    public abstract ProductoDAO productoDAO();
    public abstract CategoriaDAO categoriaDAO();

    private static AppDataBase sInstance;

    public static AppDataBase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (AppDataBase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }
    private static AppDataBase buildDatabase(Context applicationContext) {
        return Room
                .databaseBuilder(applicationContext, AppDataBase.class,DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}
