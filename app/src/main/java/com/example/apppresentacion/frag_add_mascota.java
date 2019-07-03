package com.example.apppresentacion;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.apppresentacion.interfaces.botons_menu_principal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;


public class frag_add_mascota extends Fragment implements View.OnClickListener {

    EditText txtAddNombre, txtAddIdCliente,txtAddFecha;
    RadioButton radioM, radioH;
    Spinner cboAddRaza;
    FloatingActionButton agregar_mascota;
    private int day, month, year;

    public String id_cliente_R;

    public frag_add_mascota() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_add_mascota, container, false);

        radioM = view.findViewById(R.id.edit_m);
        radioH = view.findViewById(R.id.edit_h);
        cboAddRaza = view.findViewById(R.id.cboAddRaza);
        txtAddNombre = view.findViewById(R.id.txtAddNombre);
        txtAddFecha =  view.findViewById(R.id.txtAddFecha);
        txtAddIdCliente =  view.findViewById(R.id.txtAddIdCliente);
        agregar_mascota = view.findViewById(R.id.agregar_mascota);
        id_cliente_R = getFromSharedPreferences("id_cliente");

        txtAddFecha.setInputType(InputType.TYPE_NULL);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.razas, android.R.layout.simple_spinner_item);

        cboAddRaza.setAdapter(adapter);

        txtAddIdCliente.setText(id_cliente_R);
        txtAddFecha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
                            txtAddFecha.setText(year_now+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                        }
                    },year,month,day);
                    datePickerDialog.show();

                }
            }
        });

        txtAddFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                day=c.get(Calendar.DAY_OF_MONTH);
                month=c.get(Calendar.MONTH);
                year=c.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year_now, int monthOfYear, int dayOfMonth) {
                        txtAddFecha.setText(year_now+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        agregar_mascota.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {


        final String id_cliente = txtAddIdCliente.getText().toString();
        final String nombre_mascota = txtAddNombre.getText().toString();
        final String raza = cboAddRaza.getSelectedItem().toString();
        String sexo = null;
        final String f_nac = txtAddFecha.getText().toString();


        if (radioM.isChecked()){
            sexo="1";
        }

        if(radioH.isChecked()){
            sexo="0";
        }

        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonresponse = new JSONObject(response);
                    boolean success = jsonresponse.getBoolean("success");
                    if (success){
                        Snackbar.make(getView(),"Mascota agregada correctamente", Snackbar.LENGTH_LONG)
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

        addMascotaRequest Add_Mascota = new addMascotaRequest(id_cliente,nombre_mascota,raza,sexo,f_nac, respoListener);

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(Add_Mascota);






    }


    private String getFromSharedPreferences(String key){
        SharedPreferences sharedPref = getActivity().getSharedPreferences("login",Context.MODE_PRIVATE);
        return sharedPref.getString(key,"");
    }


}
