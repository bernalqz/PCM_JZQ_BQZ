package com.example.pcm_jzq_bqz.Clases;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class cMedidor extends RealmObject {
    @PrimaryKey
    private int Codigo;

    private int CodigoSector;
    private int Secuencia;
    private String Fecha;
    private String NombreCliente;
    private String Lectura;
    private String Estado;

    public String toString()
    {
        return NombreCliente;
    }







}
