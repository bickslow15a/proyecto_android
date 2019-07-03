package com.example.apppresentacion;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registrarse extends AppCompatActivity implements View.OnClickListener {
        EditText txtUsuario, txtPass, txtNombres, txtTelefono, txtDireccion;
        Button btnRegistrarse;

    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        btnRegistrarse = (Button)findViewById(R.id.btnRegistrarse);
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtPass = (EditText) findViewById(R.id.txtPass);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtDireccion = (EditText) findViewById(R.id.txtDireccion);
        txtNombres = (EditText) findViewById(R.id.txtNombres);

        btnRegistrarse.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        final String username = txtUsuario.getText().toString();
        final String password = txtPass.getText().toString();
        final String nombres = txtNombres.getText().toString();
        final int telefono = Integer.parseInt(txtTelefono.getText().toString());
        final String direccion = txtDireccion.getText().toString();

        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonresponse = new JSONObject(response);
                    boolean success = jsonresponse.getBoolean("success");
                    if (success){
                        Toast.makeText(getApplicationContext(), "REGISTRADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Registrarse.this, Login.class);
                        Registrarse.this.startActivity(intent);
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(Registrarse.this);
                        builder.setMessage("Error Registro")
                                .setNegativeButton("Reintentar", null)
                                .create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RegistrarseRequest RegistrarseRequest = new RegistrarseRequest(username, password, nombres, direccion,telefono, respoListener);

            RequestQueue queue = Volley.newRequestQueue(Registrarse.this);
            queue.add(RegistrarseRequest);



    }
}


