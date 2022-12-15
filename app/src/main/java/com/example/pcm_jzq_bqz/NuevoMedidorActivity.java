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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.example.pcm_jzq_bqz.Clases.cMedidorServicio;

import io.realm.Realm;

public class NuevoMedidorActivity extends AppCompatActivity {

    EditText mSector, mCliente;
    TextView mFecha;
    RadioButton mActivo, mInactivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_medidor);

        //
        mSector = findViewById(R.id.txtMedidorSector);
        mCliente = findViewById(R.id.txteMedidorDuenio);
        mActivo = findViewById(R.id.rbeMedidorActivo);
        mInactivo = findViewById(R.id.rbeMedidorInactivo);
        mFecha = findViewById(R.id.tvFecha);
        mFecha.setText(getFecha());
        fn_CargarSharePreferences();
        fn_Inicializar();
        //
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
        mActivo.setChecked(true);
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
        if(mSector.getText().equals(""))
        {
            Toast.makeText(this, "Agregue un codigo de sector", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(mCliente.getText().equals(""))
            {
                Toast.makeText(this, "Agregue un codigo de sector y nombre del ciente",
                        Toast.LENGTH_SHORT).show();
            }
            else
            {
                String mEstado="";
                if(mActivo.isChecked()==true)
                {
                    mEstado="Activo";
                }
                else
                {
                    mEstado="Inactivo";
                }
                try
                {
                    int _CodigoSector = Integer.parseInt(mSector.getText().toString());
                    String _Fecha = getFecha();

                    cMedidorServicio mServicio = new cMedidorServicio(Realm.getDefaultInstance());
                    mServicio.fn_AgregarMedidor(_CodigoSector, _Fecha, mCliente.getText().toString(), mEstado);
                    Toast.makeText(this, "Agregado: "+mServicio.buscarMedidorXNombre(mCliente.getText().toString()), Toast.LENGTH_SHORT).show();
                    fn_Inicializar();

                }catch (Exception e)
                {
                    Toast.makeText(this, "Error al agregar", Toast.LENGTH_SHORT).show();
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

    private String getFecha()
    {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        return formattedDate.toString();
    }

}