package com.example.pcm_jzq_bqz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
    int i = 0;

    TextView mFecha, mSector, mCodigoMedidor, mConsecutivoMedidor;
    EditText mLectura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturas);

        mFecha = findViewById(R.id.tvLAFecha);
        mSector = findViewById(R.id.tvLASector);
        mCodigoMedidor = findViewById(R.id.tvLACodigoMedidor);
        mConsecutivoMedidor = findViewById(R.id.tvLACodigoConsecutivo);
        mLectura = findViewById(R.id.txtLectura);

        mFecha.setText(getFecha());
        fn_TraerMedidores();
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }
    // -------
    private void fn_TraerMedidores()
    {
        SharedPreferences mCodigo = getSharedPreferences("CodigoSector", Context.MODE_PRIVATE);
        String mCodigoObtenido = mCodigo.getString("Codigo", "Sector nulo");
        int _CodigoSector = Integer.parseInt(mCodigoObtenido);

        List<cMedidor> mListaMedidores = mServicioMedidor.fn_CargarListaMedidores(_CodigoSector);
        mMedidorObjeto = mListaMedidores.get(i);
        mSectorObjeto = mServicioSector.fn_BuscarSectorPorCodigo(_CodigoSector);

        mSector.setText("Sector: " + mSectorObjeto.getNombre());
        mCodigoMedidor.setText("Código de medidor: " + String.valueOf(mMedidorObjeto.getCodigoMedidor()));
        mConsecutivoMedidor.setText("Consecutivo de medidor: " + String.valueOf(mMedidorObjeto.getSecuencia()));

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
    }

    // ---
    /*private void fn_JalarDatordeBD()
    {
        mMedidor = mServicioMedidor.fn_BuscarMedidorPorCodigo(Integer.parseInt(mCodigoMedidor.toString()));


    }*/

    //---


    private String getFecha()
    {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        return formattedDate;
    }

}