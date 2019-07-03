package com.example.apppresentacion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Login extends AppCompatActivity {
    EditText txtUsuario, txtPassword;
    Button btnInicio, btnRegistrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnInicio = (Button) findViewById(R.id.btnInicio);
        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(Login.this, Registrarse.class);
                Login.this.startActivity(intentReg);
            }
        });

        btnInicio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final String username = txtUsuario.getText().toString();
                final String password = txtPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                String nombres = jsonResponse.getString("nombres");
                                String direccion = jsonResponse.getString("direccion");
                                int telefono = jsonResponse.getInt("telefono");
                                String usuarios = jsonResponse.getString("usuarios");
                                String password = jsonResponse.getString("password");
                                String id_cliente = jsonResponse.getString("id_cliente");
                                String id_usuario = jsonResponse.getString("id_usuario");
                                Toast.makeText(getApplicationContext(), "SESIÓN INICIADA", Toast.LENGTH_SHORT).show();
                                saveLoginSharedPreferences(nombres,direccion,telefono,usuarios,password,id_cliente,id_usuario);
                                Intent intentLogin = new Intent(Login.this, Inicio2.class);
                                intentLogin.putExtra("nombres",nombres);
                                intentLogin.putExtra("direccion",direccion);
                                intentLogin.putExtra("telefono",telefono);
                                intentLogin.putExtra("usuarios",usuarios);
                                intentLogin.putExtra("password",password);
                                intentLogin.putExtra("id_usuario",id_usuario);
                                intentLogin.putExtra("id_cliente",id_cliente);
                                Login.this.startActivity(intentLogin);
                                finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setMessage("Error Login")
                                        .setNegativeButton("Reintentar", null)
                                        .create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                queue.add(loginRequest);

            }
        });


    }

    private void saveLoginSharedPreferences(String nombres, String direccion, int telefono, String usuarios, String password, String id_cliente, String id_usuario){
        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
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