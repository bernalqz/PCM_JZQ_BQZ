package com.example.pcm_jzq_bqz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pcm_jzq_bqz.Clases.cUsuarioServicio;

import io.realm.Realm;

public class RegistroActivity extends AppCompatActivity {

    EditText mNombre, mCorreo, mContrasena, mTelefono, mEdad, mVerificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mNombre = findViewById(R.id.txtRegistroActivityUsuario);
        mCorreo = findViewById(R.id.txtRegistroActivityCorreo);
        mContrasena = findViewById(R.id.txtRegistroActivityContrasena);
        mVerificacion = findViewById(R.id.txtRegistroActivityContrasena02);
        mTelefono = findViewById(R.id.txtRegistroActivityTelefono);
        mEdad = findViewById(R.id.txtRegistroActivityEdad);

        fn_Inicializar();
    }

    public void fn_Regresar(View view)
    {
        this.finish();
    }

    private void fn_Inicializar()
    {
        mNombre.setText("");
        mCorreo.setText("");
        mContrasena.setText("");
        mVerificacion.setText("");
        mTelefono.setText("");
        mEdad.setText("");
    }

    public void fn_RegistrarUsuario(View view)
    {
       if(mContrasena.getText().toString().trim().equals(mVerificacion.getText().toString().trim()))
       {
           int Edad = Integer.parseInt(mEdad.getText().toString().trim());
           if(Edad >= 18)
           {
                cUsuarioServicio mServicio = new cUsuarioServicio(Realm.getDefaultInstance());
                mServicio.fn_AgregarUsuario(mNombre.getText().toString(), mCorreo.getText().toString(),
                        mContrasena.getText().toString(), mTelefono.getText().toString(), Edad);
                fn_Inicializar();
               Toast.makeText(this, "Usuario Agregado", Toast.LENGTH_SHORT).show();
           }
           else
           {
               Toast.makeText(this, "Edad no permitida", Toast.LENGTH_SHORT).show();
               Toast.makeText(this, "Usuario no agregado", Toast.LENGTH_SHORT).show();
           }

       }
       else
       {
           Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
           Toast.makeText(this, "Revise los Datos Introducidos", Toast.LENGTH_SHORT).show();
       }

    }



}