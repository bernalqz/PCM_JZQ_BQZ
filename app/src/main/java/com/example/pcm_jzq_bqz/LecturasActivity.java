package com.example.pcm_jzq_bqz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class LecturasActivity extends AppCompatActivity {

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

    private String fn_ObtenerFecha()
    {
        Dia = mlFecha.get(Calendar.MONTH);
        Mes = mlFecha.get(Calendar.DAY_OF_MONTH);
        Anio = mlFecha.get(Calendar.YEAR);
        String mDate = Dia + "/" + Mes + "/" + Anio;

        return mDate;
    }






}