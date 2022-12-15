package com.example.pcm_jzq_bqz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pcm_jzq_bqz.Clases.cMedidor;
import com.example.pcm_jzq_bqz.Clases.cMedidorServicio;
import com.example.pcm_jzq_bqz.Clases.cSector;
import com.example.pcm_jzq_bqz.Clases.cSectorServicio;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;

public class LecturasActivity extends AppCompatActivity {

    cMedidorServicio mServicioMedidor = new cMedidorServicio(Realm.getDefaultInstance());
    cSectorServicio mServicioSector = new cSectorServicio(Realm.getDefaultInstance());
    cMedidor mMedidorObjeto = new cMedidor();
    cSector mSectorObjeto = new cSector();
    List<cMedidor> mListaMedidores;
    int i = 0;

    TextView mFecha, mSector, mCodigoMedidor, mLecturaAnterior, mNombreCliente;
    EditText mLectura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturas);

        mFecha = findViewById(R.id.tvLAFecha);
        mSector = findViewById(R.id.tvLASector);
        mNombreCliente = findViewById(R.id.tvLADuenio);
        mCodigoMedidor = findViewById(R.id.tvLACodigoMedidor);
        mLectura = findViewById(R.id.txtLectura);
        mLecturaAnterior = findViewById(R.id.tvLALecturaAnterior);
        mFecha.setText(getFecha());

        fn_TraerMedidores();
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    // ---------------------------------------------------------------------------------------------
    private void fn_TraerMedidores()
    {
        SharedPreferences mCodigo = getSharedPreferences("CodigoSector", Context.MODE_PRIVATE);
        String mCodigoObtenido = mCodigo.getString("Codigo", "Sector nulo");
        int _CodigoSector = Integer.parseInt(mCodigoObtenido);

        mListaMedidores = mServicioMedidor.fn_CargarListaMedidores(_CodigoSector);
        mMedidorObjeto = mListaMedidores.get(i);
        mSectorObjeto = mServicioSector.fn_BuscarSectorPorCodigo(_CodigoSector);

        mSector.setText("Sector: " + mSectorObjeto.getNombre());
        mNombreCliente.setText("Nombre de cliente: " + mMedidorObjeto.getNombreCliente());
        mCodigoMedidor.setText("Código de medidor: " + String.valueOf(mMedidorObjeto.getCodigoMedidor()));
        mLecturaAnterior.setText("Lectura anterior: " + String.valueOf(mMedidorObjeto.getLectura()));

    }
    //----------------------------------------------------------------------------------------------
    public void fn_Regresar(View view)
    {
        this.finish();
    }

    // ---------------------------------------------------------------------------------------------
    private String getFecha()
    {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        return formattedDate;
    }

    // ---------------------------------------------------------------------------------------------
    public void fn_RegistrarLectura(View view)
    {
        try
        {
            int _Lectura = Integer.parseInt(mLectura.getText().toString());
            mMedidorObjeto = mListaMedidores.get(i);
            mServicioMedidor.fn_RegistrarLectura(mMedidorObjeto.getCodigoMedidor(),
                    mFecha.getText().toString(), _Lectura);
            Toast.makeText(this, "Registro correcto", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show();
        }
    }

// -------------------------------------------------------------------------------------------------
    public void fn_Siguiente(View view)
    {
        i++;
        mMedidorObjeto = mListaMedidores.get(i);

        mSector.setText("Sector: " + mSectorObjeto.getNombre());
        mCodigoMedidor.setText("Código de medidor: " + String.valueOf(mMedidorObjeto.getCodigoMedidor()));
        mNombreCliente.setText("Nombre de cliente: " + mMedidorObjeto.getNombreCliente());
        mLecturaAnterior.setText("Lectura anterior: " + String.valueOf(mMedidorObjeto.getLectura()));
    }


    public void fn_Anterior(View view)
    {
        i--;
        mMedidorObjeto = mListaMedidores.get(i);

        mSector.setText("Sector: " + mSectorObjeto.getNombre());
        mCodigoMedidor.setText("Código de medidor: " + String.valueOf(mMedidorObjeto.getCodigoMedidor()));
        mNombreCliente.setText("Nombre de cliente: " + mMedidorObjeto.getNombreCliente());
        mLecturaAnterior.setText("Lectura anterior: " + String.valueOf(mMedidorObjeto.getLectura()));
    }

// -------------------------------------------------------------------------------------------------






}