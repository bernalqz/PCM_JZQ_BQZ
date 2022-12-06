package com.example.pcm_jzq_bqz.Clases;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class cSector extends RealmObject {
    @PrimaryKey
    private int CodigoSector;
    private String Nombre;
    private String Canton;

    @Override
    public String toString() {
        return Nombre;
    }

    public int getCodigoSector() {
        return CodigoSector;
    }

    public void setCodigoSector(int codigoSector) {
        CodigoSector = codigoSector;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCanton() {
        return Canton;
    }

    public void setCanton(String canton) {
        Canton = canton;
    }
}
