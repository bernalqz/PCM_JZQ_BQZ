package com.example.pcm_jzq_bqz.Clases;

import io.realm.Realm;

public class cUsuarioServicio {

    private Realm mRealm; //Es como el conector a la Base de Datos
    //----------------------------------------------------------------------------------------------
    public cUsuarioServicio(Realm _Realm)
    {
        this.mRealm = _Realm;
    }
    //----------------------------------------------------------------------------------------------
    private final static int fn_CalcularCodigoUsuario()
    {
        Realm mRealm = Realm.getDefaultInstance();
        Number mCodigoActual = mRealm.where(cUsuario.class).max("CodigoUsuario");
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
    //----------------------------------------------------------------------------------------------
    public boolean fn_AgregarUsuario(String _Nombre, String _Correo, String _Contrasena, String _Telefono, int _Edad)
    {
        try
        {
            int mCodigo = fn_CalcularCodigoUsuario();
            mRealm.beginTransaction();
            cUsuario mUsuario = mRealm.createObject(cUsuario.class,mCodigo);
            mUsuario.setCodigoUsuario(mCodigo);
            mUsuario.setNombre(_Nombre);
            mUsuario.setCorreo(_Correo);
            mUsuario.setContrasena(_Contrasena);
            mUsuario.setTelefono(_Telefono);
            mUsuario.setEdad(_Edad);
            mRealm.commitTransaction();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    //----------------------------------------------------------------------------------------------
    public void fn_VerificarUsuario(String _Usuario, String _Password)
    {
        cUsuario mUsuarioDB = new cUsuario();
        mUsuarioDB = mRealm.where(cUsuario.class).equalTo("Correo", _Usuario).findFirst();

        if(mUsuarioDB.getCorreo().equals(_Usuario))
        {
            if(mUsuarioDB.getContrasena().equals(_Password))
            {

            }
        }
    }
    //----------------------------------------------------------------------------------------------
    public cUsuario fn_BuscarUsuarioPorNombre(String _Nombre)
    {
        cUsuario mUsuario = mRealm.where(cUsuario.class).equalTo("Nombre",_Nombre).findFirst();
        return mUsuario;
    }
    //----------------------------------------------------------------------------------------------
    public cUsuario fn_BuscarUsuarioPorCorreo(String _Correo)
    {
        cUsuario mUsuario = mRealm.where(cUsuario.class).equalTo("Correo",_Correo).findFirst();
        return mUsuario;
    }
    //----------------------------------------------------------------------------------------------
    public cUsuario fn_BuscarUsuarioPorContrasena(String _Contrasena)
    {
        cUsuario mUsuario = mRealm.where(cUsuario.class).equalTo("Contrasena",_Contrasena).findFirst();
        return mUsuario;
    }
    //----------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------
}
