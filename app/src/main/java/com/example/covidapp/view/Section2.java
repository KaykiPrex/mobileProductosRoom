package com.example.covidapp.view;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.covidapp.R;
import com.example.covidapp.adaptadores.AdapterListProducto;
import com.example.covidapp.database.AppDataBase;
import com.example.covidapp.modelo.Categoria;
import com.example.covidapp.modelo.CategoryWithProducts;
import com.example.covidapp.modelo.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class Section2 extends Fragment {
    private ArrayList<Categoria> listaOBJCategoria;
    private ArrayList<String> listaCategorias;
    private AppDataBase mdatabase;
    private String myCategory ;
    private ArrayAdapter<String> adapterListProductos ;
    private ListView listViewProducto;
    private AdapterListProducto adapterListProducto ;
    private RecyclerView recyclerViewListProducto ;

    public Section2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_section2, container, false);


        mdatabase = AppDataBase.getInstance(getContext()) ;
        listaOBJCategoria = new ArrayList<>();
        cargarDatosFromDataBase();
        obtenerListaCategorias();

        ArrayAdapter<CharSequence> adapterCategory = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,listaCategorias);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerCategory = view.findViewById(R.id.categoria_spinner);
        spinnerCategory.setAdapter(adapterCategory);


        final ArrayList<String> listProductos = new ArrayList<String>();

        listViewProducto = view.findViewById(R.id.productos_listview);

       // cargarListaProductos(listProductos,"galletitas");
       // adapterListProductos = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,listProductos);

       // mostrarDatosListView(listViewProducto , adapterListProductos);
        final ArrayList<Producto> mLista = new ArrayList<>();
        mostrarDatos(mLista);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               // listProductos.clear();
                //cargarListaProductos(listProductos,adapterView.getItemAtPosition(i).toString());
                //adapterListProductos.notifyDataSetChanged();
                mLista.clear();
                cargarListaProductosObjetos(mLista, adapterView.getItemAtPosition(i).toString());
                adapterListProducto.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return  view ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void cargarDatosFromDataBase(){
        if (mdatabase.categoriaDAO().count() == 0 ) {
            Categoria cat1= new Categoria("Galletitas", "", "", 0);
            Categoria cat2= new Categoria("Gaseosas", "", "", 0);
            Categoria cat3= new Categoria("Lacteos", "", "", 0);

            mdatabase.categoriaDAO().insert(cat1);
            mdatabase.categoriaDAO().insert(cat2);
            mdatabase.categoriaDAO().insert(cat3);

        }
        listaOBJCategoria = (ArrayList<Categoria>) mdatabase.categoriaDAO().getAllCategories();
    }

    private void cargarListaProductos(ArrayList<String> listProductos ,String categoria){
        List<CategoryWithProducts> categoryWithProducts = mdatabase.categoriaDAO().getAllProducts(categoria);
        for (CategoryWithProducts c : categoryWithProducts) {
            Log.d("TAG", "Categoria > "+c.categoria.getNombre()) ;
            for (Producto p : c.productos) {
                Log.d("TAG","Producto > "+ p.getNombre());

                listProductos.add(p.getNombre());
            }
        }

    }

    private void cargarListaProductosObjetos(ArrayList<Producto> listProductos ,String categoria){
        List<CategoryWithProducts> categoryWithProducts = mdatabase.categoriaDAO().getAllProducts(categoria);
        for (CategoryWithProducts c : categoryWithProducts) {
            Log.d("TAG", "Categoria > "+c.categoria.getNombre()) ;
            for (Producto p : c.productos) {
                Log.d("TAG","Producto > "+ p.getNombre());

                listProductos.add(p);
            }
        }

    }

    private void obtenerListaCategorias(){
        listaCategorias = new ArrayList<String>();
        listaCategorias.add("Seleccione ...");

        for (Categoria cat : listaOBJCategoria) {
            listaCategorias.add(cat.getNombre());
        }
    }

    private void mostrarDatos(ArrayList<Producto> lista){

        adapterListProducto = new AdapterListProducto(getContext(),R.layout.lista_section2, lista);
        listViewProducto.setAdapter(adapterListProducto);
    }
    private void mostrarDatosListView(ListView listView , ArrayAdapter<String> adapter){
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
