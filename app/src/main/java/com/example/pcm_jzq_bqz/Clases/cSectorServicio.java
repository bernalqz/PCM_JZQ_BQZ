package com.example.pcm_jzq_bqz.Clases;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class cSectorServicio {

    private Realm mRealm;

    public cSectorServicio(Realm _Realm)
    {
        this.mRealm = _Realm;
    } //Constructor

    public List<cSector> fn_ListaSectores()
    {
        RealmResults<cSector> mResultado = mRealm.where(cSector.class).findAll();
        return mRealm.copyFromRealm(mResultado);
    }

    private final static int fn_CalcularCodigoSector()
    {
        Realm mRealm = Realm.getDefaultInstance();
        Number mCodigoActual = mRealm.where(cSector.class).max("CodigoSector");
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

    public cSector fn_BuscarSectorPorCodigo(int _Codigo)
    {
        cSector mSector = mRealm.where(cSector.class).equalTo("CodigoSector",_Codigo).findFirst();
        return mSector;
    }

    public boolean fn_AgregarSector(String _Nombre, String _Canton)
    {
        try
        {
            int mCodigo = fn_CalcularCodigoSector();
            mRealm.beginTransaction();
            cSector mSector = mRealm.createObject(cSector.class,mCodigo);
            mSector.setNombre(_Nombre);
            mSector.setCanton(_Canton);
            mRealm.commitTransaction();
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public boolean fn_ActualizarSector(int _CodigoSector, String _Nombre, String _Canton)
    {
        try
        {
            cSector mSector = fn_BuscarSectorPorCodigo(_CodigoSector);
            if(mSector != null)
            {
                mRealm.beginTransaction();
                mSector.setNombre(_Nombre);
                mSector.setCanton(_Canton);
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

    public boolean fn_EliminarSector(int _CodigoSector)
    {
        try
        {
            cSector mSector = fn_BuscarSectorPorCodigo(_CodigoSector);
            if(mSector != null)
            {
                mRealm.beginTransaction();
                mSector.deleteFromRealm();
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

}
