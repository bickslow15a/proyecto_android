package com.example.apppresentacion;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apppresentacion.interfaces.botons_menu_principal;



public class frag_principal extends Fragment {

    Activity actividad;
    CardView card_add_mascota,card_add_cita;
    botons_menu_principal interface_menu_principal;
    public frag_principal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((Inicio2)getActivity()).NavTitulos("Inicio");
        View view = inflater.inflate(R.layout.fragment_frag_principal, container, false);

        card_add_mascota=view.findViewById(R.id.add_dog);
        card_add_cita=view.findViewById(R.id.add_cita);

        card_add_cita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interface_menu_principal.iniciar_insertarcita();

            }
        });

        card_add_mascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_menu_principal.iniciar_frag_add_mascota();

            }
        });


        return view;
    }



    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof Activity){
            actividad=(Activity) context;
            interface_menu_principal = (botons_menu_principal) actividad;
        }
    }


}
