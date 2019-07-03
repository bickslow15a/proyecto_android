package com.example.apppresentacion;



import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class addMascotaRequest extends StringRequest {
    private static final String Editar_REQUEST_URL="https://clinicaveterinariamican.000webhostapp.com/proyecto%20mican/proyecto2/Add_dog.php";
    private Map<String,String> params;
    public addMascotaRequest(String id_cliente, String nombre_mascota, String raza, String sexo, String f_nac, Response.Listener<String> listener){
        super(Request.Method.POST, Editar_REQUEST_URL, listener, null);
        params=new HashMap<>();
        params.put("id_cliente",id_cliente);
        params.put("nombre_mascota",nombre_mascota);
        params.put("raza",raza);
        params.put("sexo",sexo);
        params.put("f_nac",f_nac);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }


}
