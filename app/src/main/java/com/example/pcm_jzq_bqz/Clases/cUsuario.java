package com.example.pcm_jzq_bqz.Clases;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class cUsuario extends RealmObject {
    @PrimaryKey
    private int CodigoUsuario;

    private String Nombre;
    private String Correo;
    private String Contrasena;
    private String Telefono;
    private int Edad;

    @Override
    public String toString()
    {
        return Nombre;
    }

    public int getCodigoUsuario() {
        return CodigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        CodigoUsuario = codigoUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }
}
