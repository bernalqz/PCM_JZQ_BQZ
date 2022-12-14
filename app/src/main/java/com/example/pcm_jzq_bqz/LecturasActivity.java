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

import java.util.Calendar;

import io.realm.Realm;

public class LecturasActivity extends AppCompatActivity {



    cMedidorServicio mServicioNedidor = new cMedidorServicio(Realm.getDefaultInstance());
    cMedidor mMedidor = new cMedidor();


    TextView mFecha, mSector, mCodigoMedidor, mConsecutivoMedidor;
    EditText mLectura;
    Calendar mlFecha;
    int Dia, Mes, Anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturas);

        mFecha = findViewById(R.id.tvLAFecha);
        mSector = findViewById(R.id.tvLASector);
        mCodigoMedidor = findViewById(R.id.tvLACodigoMedidor);
        mConsecutivoMedidor = findViewById(R.id.tvLACodigoConsecutivo);
        mLectura = findViewById(R.id.txtLectura);
        fn_CargarSharePreferences();
        //mFecha.setText(fn_ObtenerFecha());
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }
    // -------
    public void fn_Regresar(View view)
    {
        this.finish();
    }
    // ---

    private void fn_CargarSharePreferences()
    {
        SharedPreferences mCodigo = getSharedPreferences("CodigoSector", Context.MODE_PRIVATE);
        String mCodigoObtenido = mCodigo.getString("Codigo", "Sector nulo");
        mSector.setText("Sector: "+mCodigoObtenido);

    }

    // ---
    private void fn_JalarDatordeBD()
    {
        mMedidor = mServicioNedidor.fn_BuscarMedidorPorCodigo(Integer.parseInt(mCodigoMedidor.toString()));


    }



    //---


    private String fn_ObtenerFecha()
    {
        Dia = mlFecha.get(Calendar.MONTH);
        Mes = mlFecha.get(Calendar.DAY_OF_MONTH);
        Anio = mlFecha.get(Calendar.YEAR);
        String mDate = Dia + "/" + Mes + "/" + Anio;

        return mDate;
    }






}