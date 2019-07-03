package com.example.apppresentacion.entidades;

import java.io.Serializable;

public class dog implements Serializable {
    private String id,id_cliente,nombre_mascota,raza,sexo,f_nac,foto;
    public dog(String id, String id_cliente, String nombre_mascota, String raza, String sexo, String f_nac, String foto){

        this.id = id;
        this.id_cliente = id_cliente;
        this.nombre_mascota = nombre_mascota;
        this.raza = raza;
        this.sexo = sexo;
        this.f_nac = f_nac;
        this.foto = foto;


    }

    public dog(){

    }

    public String getId(){return id;}
    public void setId(String id){this.id = id;}
    public String getId_cliente(){return id_cliente;}
    public void setId_cliente(String id_cliente){this.id_cliente = id_cliente;}
    public String getNombre_mascota(){return nombre_mascota;}
    public void setNombre_mascota(String nombre_mascota){this.nombre_mascota = nombre_mascota;}
    public String getRaza(){return raza;}
    public void setRaza(String raza){this.raza = raza;}
    public String getSexo(){return sexo;}
    public void setSexo(String sexo){this.sexo = sexo;}
    public String getF_nac(){return f_nac;}
    public void setF_nac(String f_nac){this.f_nac = f_nac;}
    public String getFoto(){return foto;}
    public void setFoto(String foto){this.foto = foto;}


}
