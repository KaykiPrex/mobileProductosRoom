package com.example.covidapp.adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidapp.R;
import com.example.covidapp.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class AdapterProducto extends RecyclerView.Adapter<AdapterProducto.ViewHolderProducto> {
    private LayoutInflater inflater;
    /**/private ArrayList<Producto> lstProducto;
    private OnItemClickListener mListener ;

    // Listener -----------------------------------------------------------
    public interface OnItemClickListener {
        void OnItemClick(int position);
        void OnDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    //----------------------------------------------------------------------
    /**/public AdapterProducto(Context context, ArrayList<Producto> lstProducto){
        this.inflater = LayoutInflater.from(context);
        this.lstProducto = lstProducto ;
    }

    @NonNull
    @Override
    public ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lista_section1, parent,false);

        return new ViewHolderProducto(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducto viewHolderProducto, int position) {

        String nombre = lstProducto.get(position).getNombre();
        String precio_publico = "$ "+lstProducto.get(position).getPrecioPublico();
        Integer imagen = lstProducto.get(position).getImagenid();
        viewHolderProducto.nombre.setText(nombre);
        viewHolderProducto.precioPublico.setText(precio_publico);
        viewHolderProducto.imagen.setImageResource(imagen);

    }

    public void addItem(int position , Producto item){
        lstProducto.add(position,item);
        notifyItemInserted(position);
    }

    public void removeItem(int position){
        lstProducto.remove(position);
        notifyItemRemoved(position);

    }

    public void modifyItem(int position , Producto item){
        lstProducto.set(position, item);
        notifyDataSetChanged();
    }

    public void restoreItem(int position, Producto item){
        lstProducto.add(position,item);
        notifyItemInserted(position);
    }

    @Override
    public int getItemCount() {
        return lstProducto.size();
    }

    public Producto getItem(int position){
        return lstProducto.get(position);
    }

    //////////////////////////***********CLASS****************//////////////////////////////
    public class ViewHolderProducto extends RecyclerView.ViewHolder{
        TextView nombre,precioPublico;
        ImageView imagen,delete;


        public ViewHolderProducto(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre_producto);
            precioPublico = itemView.findViewById(R.id.precio_publico);
            imagen = itemView.findViewById(R.id.img_producto);
            delete = itemView.findViewById(R.id.img_delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!= null){
                        int position = getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION){
                            listener.OnItemClick(position);
                        }
                    }
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!= null){
                        int position = getAdapterPosition();
                        Log.d("LOG", "Tocaste la imagen "+position);
                        if (position!= RecyclerView.NO_POSITION){
                            listener.OnDeleteClick(position);
                        }
                    }
                }
            });

        }
    }
}
