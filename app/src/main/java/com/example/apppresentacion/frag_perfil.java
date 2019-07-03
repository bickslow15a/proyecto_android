package com.example.apppresentacion;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class frag_perfil extends Fragment implements View.OnClickListener {
    EditText txtUsuarioEdit, txtPassEdit, txtNombresEdit, txtTelefonoEdit, txtDireccionEdit, txtIdusuarios, txtIdcliente;
    Button btnEditarDatos;
    public String nombres_R,direccion_R,usuarios_R,password_R,id_cliente_R,id_usuario_R;
    public int telefono_R;

    public frag_perfil() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_frag_perfil,container,false);
        btnEditarDatos = view.findViewById(R.id.btnEditarDatos);
        txtUsuarioEdit = view.findViewById(R.id.txtUsuarioEdit);
        txtPassEdit = view.findViewById(R.id.txtPassEdit);
        txtTelefonoEdit = view.findViewById(R.id.txtTelefonoEdit);
        txtDireccionEdit = view.findViewById(R.id.txtDireccionEdit);
        txtNombresEdit = view.findViewById(R.id.txtNombresEdit);
        txtIdusuarios = view.findViewById(R.id.txtIdusuarios);
        txtIdcliente = view.findViewById(R.id.txtIdcliente);
        btnEditarDatos.setOnClickListener(this);

        nombres_R = getFromSharedPreferences("nombres");
        direccion_R = getFromSharedPreferences("direccion");
        telefono_R =  getFromSharedPreferencesInt("telefono");
        usuarios_R = getFromSharedPreferences("usuarios");
        password_R = getFromSharedPreferences("password");
        id_cliente_R = getFromSharedPreferences("id_cliente");
        id_usuario_R = getFromSharedPreferences("id_usuario");



        txtNombresEdit.setText(nombres_R);
        txtDireccionEdit.setText(direccion_R);
        txtTelefonoEdit.setText(String.valueOf(telefono_R));
        txtUsuarioEdit.setText(usuarios_R);
        txtPassEdit.setText(password_R);
        txtIdcliente.setText(id_cliente_R);
        txtIdusuarios.setText(id_usuario_R);

        return view;

    }

    private String getFromSharedPreferences(String key){
        SharedPreferences sharedPref = getActivity().getSharedPreferences("login",Context.MODE_PRIVATE);
        return sharedPref.getString(key,"");
    }
    private int getFromSharedPreferencesInt(String key){
        SharedPreferences sharedPref = getActivity().getSharedPreferences("login",Context.MODE_PRIVATE);
        return sharedPref.getInt(key,0);
    }


    @Override
    public void onClick(View view) {
        final String username = txtUsuarioEdit.getText().toString();
        final String password = txtPassEdit.getText().toString();
        final String nombres = txtNombresEdit.getText().toString();
        final int telefono = Integer.parseInt(txtTelefonoEdit.getText().toString());
        final String direccion = txtDireccionEdit.getText().toString();
        final String idusuarios = txtIdusuarios.getText().toString();
        final String idclient = txtIdcliente.getText().toString();
        saveLoginSharedPreferences(nombres,direccion,telefono,username,password,idclient,idusuarios);
        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonresponse = new JSONObject(response);
                    boolean success = jsonresponse.getBoolean("success");
                    if (success){
                        Toast.makeText(getActivity().getApplicationContext(), "EDITADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("ERROR EDITAR")
                                .setNegativeButton("Reintentar", null)
                                .create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        PerfilClienteRequest PerfilClienteRequest = new PerfilClienteRequest(username, password, nombres, direccion,telefono,idusuarios, idclient, respoListener);

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(PerfilClienteRequest);


    }

    private void saveLoginSharedPreferences(String nombres, String direccion, int telefono, String usuarios, String password, String id_cliente, String id_usuario){
        SharedPreferences sharedPref = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("telefono", telefono);
        editor.putString("nombres",nombres);
        editor.putString("direccion",direccion);
        editor.putString("usuarios",usuarios);
        editor.putString("password",password);
        editor.putString("id_cliente",id_cliente);
        editor.putString("id_usuario",id_usuario);
        editor.apply();
    }





}
