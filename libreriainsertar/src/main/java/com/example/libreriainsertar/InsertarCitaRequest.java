package com.example.libreriainsertar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InsertarCitaRequest extends StringRequest {
    private static final String Insertar_REQUEST_URL="https://clinicaveterinariamican.000webhostapp.com/proyecto%20mican/proyecto2/Add_cita.php";
    private Map<String,String> params;
    public InsertarCitaRequest (String id_mascota, String f_registro, String f_cita, String sintoma, String observaciones, Response.Listener<String> listener){
        super(Request.Method.GET, Insertar_REQUEST_URL, listener, null);
        params=new HashMap<>();
        params.put("id_mascota",id_mascota);
        params.put("f_registro",f_registro);
        params.put("f_cita",f_cita);
        params.put("sintoma",sintoma);
        params.put("observaciones",observaciones);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
