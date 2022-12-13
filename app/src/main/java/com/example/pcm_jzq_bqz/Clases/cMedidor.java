package com.example.pcm_jzq_bqz.Clases;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class cMedidor extends RealmObject {
    @PrimaryKey
    private int CodigoMedidor;

    private int CodigoSector;
    private int Secuencia;
    private String Fecha;
    private String NombreCliente;
    private String Lectura;
    private String Estado;
    private String FechaLectura;

    @Override
    public String toString()
    {
        return NombreCliente;
    }

    public int getCodigo() {
        return CodigoMedidor;
    }

    public int getCodigoSector() {
        return CodigoSector;
    }

    public void setCodigoSector(int codigoSector) {
        CodigoSector = codigoSector;
    }

    public int getSecuencia() {
        return Secuencia;
    }

    public void setSecuencia(int secuencia) {
        Secuencia = secuencia;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        NombreCliente = nombreCliente;
    }

    public String getLectura() {
        return Lectura;
    }

    public void setLectura(String lectura) {
        Lectura = lectura;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public int getCodigoMedidor() {
        return CodigoMedidor;
    }

    public void setCodigoMedidor(int codigoMedidor) {
        CodigoMedidor = codigoMedidor;
    }

    public String getFechaLectura() {
        return FechaLectura;
    }

    public void setFechaLectura(String fechaLectura) {
        FechaLectura = fechaLectura;
    }
}
