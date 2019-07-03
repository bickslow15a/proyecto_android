package com.example.apppresentacion;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PerfilClienteRequest extends StringRequest {
    private static final String Editar_REQUEST_URL="https://clinicaveterinariamican.000webhostapp.com/proyecto%20mican/proyecto2/editar.php";
    private Map<String,String> params;
    public PerfilClienteRequest(String username, String password, String nombres, String direccion, int telefono, String idusuarios, String idcliente, Response.Listener<String> listener){
        super(Request.Method.POST, Editar_REQUEST_URL, listener, null);
        params=new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        params.put("nombres",nombres);
        params.put("direccion",direccion);
        params.put("telefono",telefono+"");
        params.put("idusuarios",idusuarios);
        params.put("idcliente",idcliente);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
