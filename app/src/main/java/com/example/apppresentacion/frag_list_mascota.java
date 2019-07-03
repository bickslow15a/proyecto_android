package com.example.apppresentacion;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.apppresentacion.R;
import com.example.apppresentacion.entidades.dog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class frag_list_mascota extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {
    public String id_cliente_R;
    ArrayList<dog> listDatos;

    RecyclerView list_dog;

    ProgressDialog progress;

    RequestQueue re;

    JsonObjectRequest Requestob;

    public frag_list_mascota() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_list_mascota, container, false);

        list_dog = view.findViewById(R.id.list_dog);
        list_dog.setLayoutManager(new LinearLayoutManager(this.getContext()));
        list_dog.setHasFixedSize(true);
        id_cliente_R = getFromSharedPreferences("id_cliente");
        re = Volley.newRequestQueue(getContext());

        cargar(id_cliente_R);

        listDatos = new ArrayList<>();

        return view;

    }

    private void cargar(String id_cliente) {

        String url="https://clinicaveterinariamican.000webhostapp.com/proyecto%20mican/proyecto2/list_dog.php?id_cliente="+id_cliente;
        Requestob = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        re.add(Requestob);

    }




    private String getFromSharedPreferences(String key){
        SharedPreferences sharedPref = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        return sharedPref.getString(key,"");
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        dog Dog=null;

        JSONArray json=response.optJSONArray("mascota");
        try{
            for(int i=0;i<json.length();i++){
                Dog= new dog();
                JSONObject jsonObject=null;

                jsonObject=json.getJSONObject(i);
                Dog.setId(jsonObject.optString("id"));
                Dog.setId_cliente(jsonObject.optString("id_cliente"));
                Dog.setNombre_mascota(jsonObject.optString("nombre_mascota"));
                Dog.setRaza(jsonObject.optString("raza"));
                Dog.setSexo(jsonObject.optString("sexo"));
                Dog.setF_nac(jsonObject.optString("f_nac"));
                Dog.setFoto(jsonObject.optString("foto"));

                listDatos.add(Dog);


            }
            adaptador_list_mascota adapter=new adaptador_list_mascota(listDatos);
            list_dog.setAdapter(adapter);
        }catch (JSONException e){
            e.printStackTrace();

        }
    }
}
