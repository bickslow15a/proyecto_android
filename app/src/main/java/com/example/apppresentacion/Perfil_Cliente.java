package com.example.apppresentacion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Perfil_Cliente extends AppCompatActivity implements View.OnClickListener {
    EditText txtUsuarioEdit, txtPassEdit, txtNombresEdit, txtTelefonoEdit, txtDireccionEdit, txtIdusuarios, txtIdcliente;
    Button btnEditarDatos;
    public String nombres_R,direccion_R,usuarios_R,password_R,id_cliente_R,id_usuario_R,telefono_R;

    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil__cliente);
        btnEditarDatos = (Button)findViewById(R.id.btnEditarDatos);
        txtUsuarioEdit = (EditText) findViewById(R.id.txtUsuarioEdit);
        txtPassEdit = (EditText) findViewById(R.id.txtPassEdit);
        txtTelefonoEdit = (EditText) findViewById(R.id.txtTelefonoEdit);
        txtDireccionEdit = (EditText) findViewById(R.id.txtDireccionEdit);
        txtNombresEdit = (EditText) findViewById(R.id.txtNombresEdit);
        txtIdusuarios = (EditText)findViewById(R.id.txtIdusuarios);
        txtIdcliente = (EditText)findViewById(R.id.txtIdcliente);
        btnEditarDatos.setOnClickListener(this);
/*
        Intent intent = getIntent();
        nombres_R=intent.getStringExtra("nombres");
        direccion_R=intent.getStringExtra("direccion");
        telefono_R=intent.getIntExtra("telefono",-1);
        usuarios_R=intent.getStringExtra("usuarios");
        password_R=intent.getStringExtra("password");
        id_cliente_R = intent.getStringExtra("id_cliente");
        id_usuario_R = intent.getStringExtra("id_usuario");

        txtNombresEdit.setText(nombres_R);
        txtDireccionEdit.setText(direccion_R);
        txtTelefonoEdit.setText(telefono_R+"");
        txtUsuarioEdit.setText(usuarios_R);
        txtPassEdit.setText(password_R);
        txtIdcliente.setText(id_cliente_R);
        txtIdusuarios.setText(id_usuario_R);

*/

        nombres_R = getFromSharedPreferences("nombres");
        direccion_R = getFromSharedPreferences("direccion");
        telefono_R = getFromSharedPreferences("telefono");
        usuarios_R = getFromSharedPreferences("usuarios");
        password_R = getFromSharedPreferences("password");
        id_cliente_R = getFromSharedPreferences("id_cliente");
        id_usuario_R = getFromSharedPreferences("id_usuario");



        txtNombresEdit.setText(nombres_R);
        txtDireccionEdit.setText(direccion_R);
        txtTelefonoEdit.setText(telefono_R);
        txtUsuarioEdit.setText(usuarios_R);
        txtPassEdit.setText(password_R);
        txtIdcliente.setText(id_cliente_R);
        txtIdusuarios.setText(id_usuario_R);

    }

    private String getFromSharedPreferences(String key){
        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
        return sharedPref.getString(key,"");
    }

/*
    public void onPause(){
        super.onPause();

        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor estado=datos.edit();
        estado.putInt("telefono", telefono_R);
        estado.putString("nombres",nombres_R);
        estado.putString("direccion",direccion_R);
        estado.putString("usuarios",usuarios_R);
        estado.putString("password",password_R);
        estado.putString("id_cliente",id_cliente_R);
        estado.putString("id_usuario",id_usuario_R);

        estado.apply();
    }
    public void onResume(){
        super.onResume();

        SharedPreferences estado = PreferenceManager.getDefaultSharedPreferences(this);

        nombres_R=estado.getString("nombres","");
        direccion_R=estado.getString("direccion","");
        telefono_R=estado.getInt("telefono",0);
        usuarios_R=estado.getString("usuarios","");
        password_R=estado.getString("password","");
        id_cliente_R = estado.getString("id_cliente","");
        id_usuario_R = estado.getString("id_usuario","");

        txtNombresEdit.setText(nombres_R);
        txtDireccionEdit.setText(direccion_R);
        txtTelefonoEdit.setText(telefono_R+"");
        txtUsuarioEdit.setText(usuarios_R);
        txtPassEdit.setText(password_R);
        txtIdcliente.setText(id_cliente_R);
        txtIdusuarios.setText(id_usuario_R);
    }


   cuando se esta ejecutando, recuperar datos
    public void onSaveInstanceState(Bundle estado){
        estado.putInt("telefono", telefono_R);
        estado.putString("nombres",nombres_R);
        estado.putString("direccion",direccion_R);
        estado.putString("usuarios",usuarios_R);
        estado.putString("password",password_R);
        estado.putString("id_cliente",id_cliente_R);
        estado.putString("id_usuario",id_usuario_R);
        super.onSaveInstanceState(estado);
    }

    public void onRestoreInstanceState(Bundle estado){
        super.onRestoreInstanceState(estado);
        nombres_R=estado.getString("nombres");
        direccion_R=estado.getString("direccion");
        telefono_R=estado.getInt("telefono");
        usuarios_R=estado.getString("usuarios");
        password_R=estado.getString("password");
        id_cliente_R = estado.getString("id_cliente");
        id_usuario_R = estado.getString("id_usuario");

        txtNombresEdit.setText(nombres_R);
        txtDireccionEdit.setText(direccion_R);
        txtTelefonoEdit.setText(telefono_R+"");
        txtUsuarioEdit.setText(usuarios_R);
        txtPassEdit.setText(password_R);
        txtIdcliente.setText(id_cliente_R);
        txtIdusuarios.setText(id_usuario_R);

    }
    */

    @Override
    public void onClick(View view) {
        final String username = txtUsuarioEdit.getText().toString();
        final String password = txtPassEdit.getText().toString();
        final String nombres = txtNombresEdit.getText().toString();
        final int telefono = Integer.parseInt(txtTelefonoEdit.getText().toString());
        final String direccion = txtDireccionEdit.getText().toString();
        final String idusuarios = txtIdusuarios.getText().toString();
        final String idclient = txtIdcliente.getText().toString();

        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonresponse = new JSONObject(response);
                    boolean success = jsonresponse.getBoolean("success");
                    if (success){
                        Toast.makeText(getApplicationContext(), "EDITADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(Perfil_Cliente.this);
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

        RequestQueue queue = Volley.newRequestQueue(Perfil_Cliente.this);
        queue.add(PerfilClienteRequest);



    }
}


