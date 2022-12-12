package com.example.pcm_jzq_bqz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.pcm_jzq_bqz.Clases.cMedidor;
import com.example.pcm_jzq_bqz.Clases.cMedidorServicio;
import com.example.pcm_jzq_bqz.Clases.cMedidoresAdaptador;

import java.util.List;

import io.realm.Realm;

public class MedidoresActivity extends AppCompatActivity {

    cMedidorServicio mServicio = new cMedidorServicio(Realm.getDefaultInstance());
    List<cMedidor> mListaMedidores;
    ListView mlstListaMedidores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medidores);

        mlstListaMedidores = findViewById(R.id.lstListaMedidores);

        fn_CargarListaMedidores();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        fn_CargarListaMedidores();
    }

    private void fn_CargarListaMedidores()
    {
        mListaMedidores = mServicio.fn_CargarListaMedidores(fn_CargarSharePreferences());
        cMedidoresAdaptador mAdaptador = new cMedidoresAdaptador(this,mListaMedidores,R.layout.adaptador_medidores);
        mlstListaMedidores.setAdapter(mAdaptador);
    }

    // ----------------
    public void fn_Regresar(View view)
    {
        this.finish();
    }

    // ----------------------------------
    public void frmIrAgregarMedidor(View v)
    {
        Intent mPantalla = new Intent(this, NuevoMedidorActivity.class);
        startActivity(mPantalla);
    }
    // ------------------------------------

    public void frmIrEditarMedidor(View v)
    {
        Intent mPantalla = new Intent(this,EditarMedidorActivity.class);
        startActivity(mPantalla);
    }
    // ------------------------------------
    private int fn_CargarSharePreferences()
    {
        SharedPreferences mCodigo = getSharedPreferences("CodigoSector", Context.MODE_PRIVATE);
        String mCodigoSector = mCodigo.getString("Codigo", "Sector nulo");
        int mCodigo01 = Integer.parseInt(mCodigoSector);

        return mCodigo01;
    }
}