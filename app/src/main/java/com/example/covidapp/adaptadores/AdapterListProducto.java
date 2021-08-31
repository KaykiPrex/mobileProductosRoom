package com.example.covidapp.adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.covidapp.R;
import com.example.covidapp.modelo.Producto;

import java.util.ArrayList;
import java.util.Objects;

public class AdapterListProducto extends ArrayAdapter<Producto> {
    private Context mContext;
    private int mResource;

    public AdapterListProducto(Context context , int resource , ArrayList<Producto> lstProductos ){
        super(context,resource, lstProductos);
        this.mContext = context;
        this.mResource = resource ;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String producto = Objects.requireNonNull(getItem(position)).getNombre();
        String precio = "$ "+Objects.requireNonNull(getItem(position)).getPrecioPublico();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView =inflater.inflate(mResource, parent,false);

        TextView txtProducto = convertView.findViewById(R.id.nombre_producto);
        TextView txtPrecio = convertView.findViewById(R.id.precio_publico);

        txtProducto.setText(producto);
        txtPrecio.setText(precio);

        return convertView;
    }
}
