package com.example.apppresentacion;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apppresentacion.entidades.dog;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

public class adaptador_list_mascota extends RecyclerView.Adapter<adaptador_list_mascota.ViewHolderDatos> {

    List<dog> listDatos;


    public adaptador_list_mascota(List<dog> listDatos) {
        this.listDatos = listDatos;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_dog,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        view.setLayoutParams(layoutParams);

        return new ViewHolderDatos(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ViewHolderDatos holder, int position) {
        holder.nombre_dog.setText(listDatos.get(position).getNombre_mascota());
        holder.raza_dog.setText(listDatos.get(position).getRaza());


        String dtStart = listDatos.get(position).getF_nac();
        Calendar date = Calendar.getInstance();

                String [] date_string = dtStart.split("-");
                date.set(Calendar.YEAR, Integer.parseInt(date_string[0]));
                date.set(Calendar.MONTH, Integer.parseInt(date_string[1]));
                date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date_string[2]));
               date.getTimeInMillis();


        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date.getTimeInMillis());

        Calendar Hoy = Calendar.getInstance();

        int age = Hoy.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if(Hoy.get(Calendar.MONTH)<dob.get(Calendar.YEAR)){
            age=age-1;
            if(age<0){
                age=0;
            }
        }


        String sexo= "Hembra";
        if("1".equals(listDatos.get(position).getSexo())){
             sexo= "Macho";
        }

        holder.edad_dog.setText("Edad: "+Integer.toString(age)+" -");
        holder.sexo_dog.setText(sexo);
        holder.id_dog.setText(listDatos.get(position).getId());
        holder.id_cliente.setText(listDatos.get(position).getId_cliente());
        holder.id_foto.setText(listDatos.get(position).getFoto());
        holder.fecha_oculta.setText(listDatos.get(position).getF_nac());
        holder.sexo_oculto.setText(listDatos.get(position).getSexo());



    }



    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public void removeItem(int position){
        listDatos.remove(position);
        notifyItemRemoved(position);

    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView nombre_dog,raza_dog,edad_dog,sexo_dog,id_dog,id_cliente,id_foto, fecha_oculta, sexo_oculto, btneliminar;

        public ViewHolderDatos(View itemView) {
            super(itemView);

            nombre_dog=itemView.findViewById(R.id.nombre_dog);
            raza_dog=itemView.findViewById(R.id.raza_dog);
            edad_dog=itemView.findViewById(R.id.edad_dog);
            sexo_dog=itemView.findViewById(R.id.sexo_dog);
            id_dog=itemView.findViewById(R.id.id_dog);
            id_cliente=itemView.findViewById(R.id.id_cliente);
            id_foto=itemView.findViewById(R.id.id_foto);
            fecha_oculta=itemView.findViewById(R.id.fecha_oculta);
            sexo_oculto=itemView.findViewById(R.id.sexo_oculto);

        }


    }
}
