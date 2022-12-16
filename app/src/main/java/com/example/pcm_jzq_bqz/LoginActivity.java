package com.example.pcm_jzq_bqz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class LoginActivity extends AppCompatActivity {

    //---------------------------------- VARIABLES GLOBALES ----------------------------------------
    EditText mUsuario, mContrasena;
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Realm.init(this);
        RealmConfiguration mRealmConfiguration = new RealmConfiguration.Builder()
                .name("BaseDatos")
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(mRealmConfiguration);

        fn_LoginSharePreferences();

        mUsuario = findViewById(R.id.txtLoginActivityUsuario);
        mContrasena = findViewById(R.id.txtLoginActivityContrasena);
    }
    //----------------------------------------------------------------------------------------------
    /*@Override
    public void onResume()
    {
        super.onResume();
        fn_Inicializar();
    }*/
    //----------------------------------------------------------------------------------------------
    private void fn_Inicializar()
    {
        mUsuario.setText("");
        mContrasena.setText("");
    }
    //----------------------------------------------------------------------------------------------
    private void fn_LoginSharePreferences()
    {
        String Usuario = "Admin12345";
        String Password = fn_DatosRegistro();

        SharedPreferences mPreferencias = getSharedPreferences("DatosLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mPreferencias.edit();
        mEditor.putString("Usuario",Usuario);
        mEditor.putString("Contrasena",Password);
        mEditor.commit();
    }
    //----------------------------------------------------------------------------------------------
    private String fn_DatosRegistro()
    {
        String Password = "";
        int i, mNum;

        for(i = 1; i <= 10; i++)
        {
            mNum = (int) (Math.floor(Math.random() * (0-9))+9);
            Password += String.valueOf(mNum);
        }
        return Password;
    }
    //----------------------------------------------------------------------------------------------
    public void fn_Registro(View view)
    {
        Intent mPantalla = new Intent(this,RegistroActivity.class);
        startActivity(mPantalla);
    }
    //----------------------------------------------------------------------------------------------
    public void fn_VerificarUsuario(View view)
    {
        try
        {
            if(mUsuario.getText().toString().trim().equals(""))
            {
                if(mContrasena.getText().toString().trim().equals(""))
                {
                    Toast.makeText(this, "Digite un usuario y una contraseña", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Digite un nombre de ususario", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                if(mContrasena.getText().toString().trim().equals(""))
                {
                    Toast.makeText(this, "Digite una contraseña", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    fn_Verificar();
                }
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error al verificar", Toast.LENGTH_SHORT).show();
        }
    }
    //----------------------------------------------------------------------------------------------
    private void fn_Verificar()
    {
        SharedPreferences mPreferencias = getSharedPreferences("DatosLogin", Context.MODE_PRIVATE);
        String mUsuario02 = mPreferencias.getString("Usuario", "No hay datos");
        String mPassword02 = mPreferencias.getString("Contrasena", "No hay datos");

        if(mUsuario.getText().toString().equals(mUsuario02))
        {
            if(mContrasena.getText().toString().equals(mPassword02))
            {
                Intent mPantalla = new Intent(this,MainActivity.class);
                startActivity(mPantalla);
            }
        }
    }
    //----------------------------------------------------------------------------------------------
    public void fn_CargarSharePreferences(View view)
    {
        SharedPreferences mPreferencias = getSharedPreferences("DatosLogin", Context.MODE_PRIVATE);
        String mUsuario02 = mPreferencias.getString("Usuario", "No hay datos");
        String mPassword02 = mPreferencias.getString("Contrasena", "No hay datos");

        mUsuario.setText(mUsuario02);
        mContrasena.setText(mPassword02);
        Toast.makeText(this, "Contraseña: " + mPassword02, Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------
}