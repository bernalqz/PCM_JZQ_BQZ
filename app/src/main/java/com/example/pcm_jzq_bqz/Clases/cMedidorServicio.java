package com.example.pcm_jzq_bqz.Clases;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class cMedidorServicio {

    private Realm mRealm; 
    
//    



    public List<cMedidor> fn_ListaMedidores()
    {
        RealmResults<cMedidor> mResultado = mRealm.where(cMedidor.class).findAll();
        return mRealm.copyFromRealm(mResultado);
    }

    private final static int fn_CalcularCodigoMedidor()
    {
        Realm mRealm = Realm.getDefaultInstance();
        Number mCodigoActual = mRealm.where(cMedidor.class).max("CodigoMedidor");
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

    public boolean fn_AgregarMedidor(String _Nombre, String _Canton)
    {
        try
        {
            int mCodigo = fn_CalcularCodigoMedidor();
            mRealm.beginTransaction();
            cMedidor mMedidor = mRealm.createObject(cMedidor.class,mCodigo);
            mMedidor.setNombre(_Nombre);
            mMedidor.setCanton(_Canton);
            mRealm.commitTransaction();
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public boolean fn_ActualizarMedidor(int _CodigoMedidor, String _Nombre, String _Canton)
    {
        try
        {
            cMedidor mMedidor = fn_BuscarMedidorPorCodigo(_CodigoMedidor);
            if(mMedidor != null)
            {
                mRealm.beginTransaction();
                mMedidor.setNombre(_Nombre);
                mMedidor.setCanton(_Canton);
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
    
    
    
    
    
//    
}
