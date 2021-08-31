package com.example.covidapp.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.covidapp.R;
import com.example.covidapp.adaptadores.AdapterProducto;
import com.example.covidapp.database.AppDataBase;
import com.example.covidapp.modelo.Categoria;
import com.example.covidapp.modelo.Producto;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class Section1 extends Fragment {
    private AdapterProducto adapterProducto;
    private RecyclerView recyclerViewProducto;
    /**/private ArrayList<Producto> lstProducto;
    private FloatingActionButton floatingActionButton;
    private AppDataBase mdatabase;

    public Section1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_section1, container, false);
        mdatabase = AppDataBase.getInstance(getContext()) ;
        recyclerViewProducto = view.findViewById(R.id.recyclerViewSection1);
        lstProducto = new ArrayList<>();
        //cargar datos
        cargarDatosFromDataBase();
        //mostrar datos
        mostrarDatos();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floatingActionButton = view.findViewById(R.id.button_floating);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialogComponentInputProduct(view);
            }
        });

        adapterProducto.setOnItemClickListener(new AdapterProducto.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Log.d("LOG", "Item Clicked");
                AlertDialogComponentUpdateProduct(view,position);
            }

            @Override
            public void OnDeleteClick(int position) {
                Log.d("LOG: ","Objeto id : "+adapterProducto.getItem(position).getId());
                mdatabase.productoDAO().delete(adapterProducto.getItem(position));
                adapterProducto.removeItem(position);
            }
        });

    }

    /*private void cargarDatos() {
        lstProducto.add(new Producto("Fideos", "50", "30", true, "Pastas", R.drawable.ic_person_outline_black_24dp));
        lstProducto.add(new Producto("Ravioles", "40", "25", true, "Pastas", R.drawable.ic_person_outline_black_24dp));
        lstProducto.add(new Producto("Galletitas", "20", "10", true, "Dulce", R.drawable.ic_person_outline_black_24dp));
        lstProducto.add(new Producto("Bizcochitos", "15", "5", true, "Dulce", R.drawable.ic_person_outline_black_24dp));
    }*/
    private void cargarDatosFromDataBase(){
        if (mdatabase.productoDAO().count() != 0 ) {
            Log.d("LOG", "HAY DATOS "+mdatabase.productoDAO().count());
            lstProducto =  (ArrayList<Producto>) mdatabase.productoDAO().getAllProducts();
        }
        else Log.d("LOG", "NO HAY DATOS "+mdatabase.productoDAO().count());
    }

    private void mostrarDatos() {
        recyclerViewProducto.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterProducto = new AdapterProducto(getContext(), lstProducto);
        recyclerViewProducto.setAdapter(adapterProducto);
    }

    private void AlertDialogComponentInputProduct(View view) {
        View mview = LayoutInflater.from(getContext()).inflate(R.layout.alert_dialog_addproduct, (RelativeLayout) view.findViewById(R.id.layout_dialog_container));
        final EditText producto = mview.findViewById(R.id.add_nombre);
        final EditText precio = mview.findViewById(R.id.add_precio_publico);

        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(Objects.requireNonNull(getContext()))
                .setTitle("Nuevo Producto")
                .setView(mview);

        alertDialogBuilder
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int position = adapterProducto.getItemCount();
                        Producto mproducto = new Producto(producto.getText().toString(), precio.getText().toString(), "0", true, 2, R.drawable.ic_person_outline_black_24dp); // categoria 1 = id FK , crearla por defecto , para los que no tienen categoria
                        long id = mdatabase.productoDAO().insert(mproducto);
                        mproducto.setId(id); // Seteo el ID porque en NEW el incrementable es 0 , y necesito el ultimo de la base de datos
                        adapterProducto.addItem(position, mproducto);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        alertDialogBuilder.show();
    }

    private void AlertDialogComponentUpdateProduct(View view , final int position) {
        View mview = LayoutInflater.from(getContext()).inflate(R.layout.alert_dialog_addproduct, (RelativeLayout) view.findViewById(R.id.layout_dialog_container));
        final EditText producto = mview.findViewById(R.id.add_nombre);
        final EditText precio = mview.findViewById(R.id.add_precio_publico);

        Producto productoAdapter = adapterProducto.getItem(position);
        producto.setText(adapterProducto.getItem(position).getNombre());
        precio.setText(adapterProducto.getItem(position).getPrecioPublico());

        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(Objects.requireNonNull(getContext()))
                .setTitle("Modificar Producto")
                .setView(mview);

        alertDialogBuilder
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Producto productoAdapter = adapterProducto.getItem(position);

                        productoAdapter.setNombre(producto.getText().toString());
                        productoAdapter.setPrecioPublico(precio.getText().toString());
                        adapterProducto.modifyItem(position, productoAdapter);
                        mdatabase.productoDAO().update(productoAdapter);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        alertDialogBuilder.show();
    }
}
