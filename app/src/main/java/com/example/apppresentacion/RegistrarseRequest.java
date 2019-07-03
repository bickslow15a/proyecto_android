package com.example.apppresentacion;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistrarseRequest extends StringRequest {
    private static final String REGISTRAR_REQUEST_URL="https://clinicaveterinariamican.000webhostapp.com/proyecto%20mican/proyecto2/registrarse.php";
    private Map<String,String> params;
    public RegistrarseRequest(String username, String password, String nombres, String direccion, int telefono, Response.Listener<String> listener){
        super(Request.Method.POST, REGISTRAR_REQUEST_URL, listener, null);
        params=new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        params.put("nombres",nombres);
        params.put("direccion",direccion);
        params.put("telefono",telefono+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
