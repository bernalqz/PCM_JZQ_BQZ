package com.example.pcm_jzq_bqz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

import com.example.pcm_jzq_bqz.Clases.cMedidorServicio;

import io.realm.Realm;

public class NuevoMedidorActivity extends AppCompatActivity {

    int Dia, Mes, Year;
    EditText mSector, mConsecutivo, mCliente, mSecuencia;
    TextView mFecha;
    RadioButton mActivo, mInactivo;
    String mEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_medidor);

        mSector = findViewById(R.id.txtMedidorSector);
        mCliente = findViewById(R.id.txtMedidorDuenio);
        mActivo = findViewById(R.id.rbMedidorActivo);
        mInactivo = findViewById(R.id.rbMedidorInactivo);
        mFecha = findViewById(R.id.tvFecha);

        mFecha.setText(fn_ObtenerFecha());
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
        mCliente.setText("");
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
            if(mCliente.equals(""))
            {
                Toast.makeText(this, "Agregue un número de sector y " +
                        "el nombre del cliente", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(mActivo.isChecked() == false & mInactivo.isChecked() == false)
                {
                    Toast.makeText(this, "Seleccione Activo o Inactivo", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    try
                    {
                        cMedidorServicio mServicio = new cMedidorServicio(Realm.getDefaultInstance());
                        int mCodigoSector = Integer.parseInt(mSector.getText().toString());

                        if(mActivo.isChecked() == true)
                        {
                            mEstado = "Activo";
                        }
                        else
                        {
                            mEstado = "Inactivo";
                        }

                        mServicio.fn_AgregarMedidor(mCodigoSector, fn_ObtenerFecha(),
                                mCliente.getText().toString(), mEstado);
                        fn_Inicializar();
                        Toast.makeText(this, "Agregado correctamente", Toast.LENGTH_SHORT).show();
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(this, "Error al agregar", Toast.LENGTH_SHORT).show();
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

    public String fn_ObtenerFecha()
    {
        Calendar mFecha = Calendar.getInstance();
        Dia = mFecha.get(Calendar.DAY_OF_MONTH);
        Mes = mFecha.get(Calendar.MONTH);
        Year = mFecha.get(Calendar.YEAR);
        String mDate = Mes + "/" + Dia + "/" + Year;
        return mDate;
    }

}