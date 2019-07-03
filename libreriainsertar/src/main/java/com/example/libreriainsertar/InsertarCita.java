package com.example.libreriainsertar;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.libreriainsertar.EntidadesLibreria.Mascota;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import com.loopj.android.http.*;


public class InsertarCita extends Fragment implements View.OnClickListener {

    EditText txtInsertarFechaRegistro, txtInsertarFechaCita,txtInsertarSintoma,txtInsertarObservaciones, txtInsertarIdMascota;
    Spinner cboInsertarIdMascota;
    ArrayList<String> ListarMascota;
    ArrayList<Mascota> MascotaList;
    ProgressDialog progress;

    RequestQueue request;

    JsonObjectRequest jsonObjectRequest;

    FloatingActionButton btnInsertarCita;
    private int day, month, year;

    public String id_cliente_R;

    public InsertarCita() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insertar_cita, container, false);

        txtInsertarFechaRegistro= view.findViewById(R.id.txtInsertarFechaRegistro);
        txtInsertarFechaCita =  view.findViewById(R.id.txtInsertarFechaCita);
        txtInsertarSintoma =  view.findViewById(R.id.txtInsertarSintoma);
        txtInsertarObservaciones =  view.findViewById(R.id.txtInsertarIdMascota);
        cboInsertarIdMascota=  view.findViewById(R.id.cboInsertarIdMascota);
        btnInsertarCita = view.findViewById(R.id.btnInsertarCita);
        txtInsertarIdMascota  = view.findViewById(R.id.txtInsertarIdMascota);
        id_cliente_R = getFromSharedPreferences("id_cliente");

        txtInsertarFechaCita.setInputType(InputType.TYPE_NULL);
        txtInsertarFechaRegistro.setInputType(InputType.TYPE_NULL);

        txtInsertarFechaCita.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean tengofocus) {
                if(tengofocus){
                    final Calendar c = Calendar.getInstance();
                    day=c.get(Calendar.DAY_OF_MONTH);
                    month=c.get(Calendar.MONTH);
                    year=c.get(Calendar.YEAR);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year_now, int monthOfYear, int dayOfMonth) {
                            txtInsertarFechaCita.setText(year_now+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                        }
                    },year,month,day);
                    datePickerDialog.show();

                }
            }
        });


        btnInsertarCita.setOnClickListener(this);

        txtInsertarFechaRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                day=c.get(Calendar.DAY_OF_MONTH);
                month=c.get(Calendar.MONTH);
                year=c.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year_now, int monthOfYear, int dayOfMonth) {
                        txtInsertarFechaRegistro.setText(year_now+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {



        final String f_registro = txtInsertarFechaRegistro.getText().toString();
        final String f_cita =  txtInsertarFechaCita.getText().toString();
        final String sintoma =  txtInsertarSintoma.getText().toString();
        final String observaciones =  txtInsertarObservaciones.getText().toString();
        final String id_mascota= cboInsertarIdMascota.getSelectedItem().toString();


        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonresponse = new JSONObject(response);
                    boolean success = jsonresponse.getBoolean("success");
                    if (success){
                        Snackbar.make(getView(),"Cita agregada correctamente", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
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

       InsertarCitaRequest Add_cita = new InsertarCitaRequest(id_mascota,f_registro,f_cita,sintoma,observaciones, respoListener);

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(Add_cita);

    }




    private String getFromSharedPreferences(String key){
        SharedPreferences sharedPref = getActivity().getSharedPreferences("login",Context.MODE_PRIVATE);
        return sharedPref.getString(key,"");
    }
}


