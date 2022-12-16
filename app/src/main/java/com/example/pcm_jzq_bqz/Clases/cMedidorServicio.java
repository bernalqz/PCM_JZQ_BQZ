package com.example.pcm_jzq_bqz.Clases;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class cMedidorServicio {

    private Realm mRealm;

    public cMedidorServicio(Realm _Realm)
    {
        this.mRealm = _Realm;
    }
//
    public List<cMedidor> fn_CargarListaMedidores(int _CodigoSector)
    {
        RealmResults<cMedidor> mResultado = mRealm.where(cMedidor.class).equalTo("CodigoSector",_CodigoSector).findAll();
        return mRealm.copyFromRealm(mResultado);
    }
//
public List<cMedidor> fn_CargarListaMedidoresSinLectura()
{
    int ValLectura = 0;
    RealmResults<cMedidor> mResultado = mRealm.where(cMedidor.class).equalTo("Lectura",ValLectura).findAll();
    return mRealm.copyFromRealm(mResultado);
}
//
public List<cMedidor> fn_CargarListaMedidoresConLectura()
{
    int ValLectura = 0;
    RealmResults<cMedidor> mResultado = mRealm.where(cMedidor.class).notEqualTo("Lectura",ValLectura).findAll();
    return mRealm.copyFromRealm(mResultado);
}


//
    private final static int fn_CalcularCodigoMedidor()
    {
        Realm mRealm = Realm.getDefaultInstance();
        Number mCodigoActual = mRealm.where(cMedidor.class).max("Secuencia");
        int mCodigoNuevo;
        if(mCodigoActual == null)
        {
            mCodigoNuevo = 1;
        }
        else
        {
            mCodigoNuevo = mCodigoActual.intValue() + 1;
        }
        return mCodigoNuevo;
    }

    public cMedidor fn_BuscarMedidorPorCodigo(int _Codigo)
    {
        cMedidor mMedidor = mRealm.where(cMedidor.class).equalTo("CodigoMedidor",_Codigo).findFirst();
        return mMedidor;
    }

    // -------------------------**** Esta funcion no se usa, con derechos de autor Benal ;)
    Number mMedidormin = 1;
    public cMedidor fnBuscarMedidorPorSectoryCodigo(int _CodSector) {
        Realm mRealm = Realm.getDefaultInstance();
        cMedidor mResultado = null;


        cMedidor mMedidor;
        do {
            mMedidor = mRealm.where(cMedidor.class).equalTo("Secuencia", mMedidormin.intValue()).findFirst();
            if (mMedidor.equals(null)) {

                mMedidormin = mMedidormin.intValue() + 1;
            } else {

                if ((int) (mMedidor.getCodigoSector()) == (_CodSector)) {
                    mResultado = mMedidor;
                    mMedidormin = mMedidormin.intValue() + 1;

                } else {
                    mMedidormin = mMedidormin.intValue() + 1;
                }
            }
        }
        while ((mResultado != mMedidor) & (mMedidormin.intValue() < 100));

        return mResultado;

    }

    //--------------------------****

    public boolean fn_AgregarMedidor(int _CodigoSector, String _Fecha, String _Nombre, String _Estado)
    {
        try
        {
            int mCodigo = fn_CalcularCodigoMedidor();
            mRealm.beginTransaction();
            cMedidor mMedidor = mRealm.createObject(cMedidor.class,mCodigo);
            mMedidor.setCodigoSector(_CodigoSector);
            mMedidor.setSecuencia(mCodigo);
            //mMedidor.setCodigoMedidor(mCodigo);
            mMedidor.setFecha(_Fecha);
            mMedidor.setNombreCliente(_Nombre);
            mMedidor.setEstado(_Estado);
            mMedidor.setFechaLectura("0");
            mMedidor.setLectura(0);
            mRealm.commitTransaction();
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public boolean fn_ActualizarMedidor(int _CodigoMedidor, String _Nombre,int _Lectura, String _Estado )
    {
        try
        {
            cMedidor mMedidor = fn_BuscarMedidorPorCodigo(_CodigoMedidor);
            if(mMedidor != null)
            {
                mRealm.beginTransaction();
                mMedidor.setNombreCliente(_Nombre);
                mMedidor.setLectura(_Lectura);
                mMedidor.setEstado(_Estado);
                mRealm.commitTransaction();
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception ex)
        {
            return false;
        }

    }
//----------------

    public cMedidor buscarMedidorXNombre(String _Nombre)
    {
        cMedidor mMedidor = mRealm.where(cMedidor.class).equalTo("NombreCliente",_Nombre).findFirst();
        return mMedidor;
    }

 //----------------
    public boolean fn_EliminarMedidor(int _CodigoMedidor)
    {
        try
        {
            cMedidor mMedidor = fn_BuscarMedidorPorCodigo(_CodigoMedidor);
            if(mMedidor != null)
            {
                mRealm.beginTransaction();
                mMedidor.deleteFromRealm();
                mRealm.commitTransaction();
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception ex)
        {
            return false;
        }
    }

    public boolean fn_RegistrarLectura(int _CodigoMedidor, String _FechaLectura, int _Lectura)
    {
        try
        {
            cMedidor mMedidor = fn_BuscarMedidorPorCodigo(_CodigoMedidor);
            if(mMedidor != null)
            {
                mRealm.beginTransaction();
                mMedidor.setFechaLectura(_FechaLectura);
                mMedidor.setLectura(_Lectura);
                mRealm.commitTransaction();
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception ex)
        {
            return false;
        }
    }

//
}
