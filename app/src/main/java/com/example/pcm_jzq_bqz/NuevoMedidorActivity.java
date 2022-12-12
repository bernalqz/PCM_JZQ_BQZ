package com.example.pcm_jzq_bqz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.pcm_jzq_bqz.Clases.cMedidorServicio;

import io.realm.Realm;

public class NuevoMedidorActivity extends AppCompatActivity {

    EditText mSector, mConsecutivo, mCliente, mSecuencia;
    RadioButton mActivo, mInactivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_medidor);

        mSector = findViewById(R.id.txtMedidorSector);
        mConsecutivo = findViewById(R.id.txtMedidorConsecutivo);
        mCliente = findViewById(R.id.txtMedidorDuenio);
        mSecuencia = findViewById(R.id.txtMedidorSecuencia);
        mActivo = findViewById(R.id.rbMedidorActivo);
        mInactivo = findViewById(R.id.rbMedidorInactivo);

        fn_CargarSharePreferences();

    }

    @Override
    public void onResume()
    {
        super.onResume();
        fn_CargarSharePreferences();
    }

    private void fn_Inicializar()
    {
        mConsecutivo.setText("");
        mCliente.setText("");
        mSecuencia.setText("");
        mActivo.setChecked(false);
        mInactivo.setChecked(false);
    }

    //

    public void fn_Regresar(View view)
    {
        this.finish();
    }

    //
    public void fn_AgregarMedidor(View view)
    {
        if(mSector.equals(""))
        {
            Toast.makeText(this, "Agrege un número de sector", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(mConsecutivo.equals(""))
            {
                Toast.makeText(this, "Agregue un número de sector y un consecutivo", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(mCliente.equals(""))
                {
                    Toast.makeText(this, "Agregue un número de sector, un consecutivo y " +
                            "el nombre del cliente", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(mSecuencia.equals(""))
                    {
                        Toast.makeText(this, "Agregue un número de sector, un consecutivo, " +
                                "el nombre del cliente y la secuencia", Toast.LENGTH_SHORT).show();
                    }
                    if(mActivo.isChecked() == false & mInactivo.isChecked() == false)
                    {
                        Toast.makeText(this, "Seleccione Activo o Inactivo", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        try
                        {


                        }catch(Exception e)
                        {

                        }




                    }
                }
            }
        }




    }

    private void fn_CargarSharePreferences()
    {
        SharedPreferences mCodigo = getSharedPreferences("CodigoSector", Context.MODE_PRIVATE);
        String mCodigoObtenido = mCodigo.getString("Codigo", "Sector nulo");
        mSector.setText(mCodigoObtenido);
    }


}