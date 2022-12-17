package com.example.pcm_jzq_bqz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pcm_jzq_bqz.Clases.cMedidor;
import com.example.pcm_jzq_bqz.Clases.cMedidorServicio;
import com.example.pcm_jzq_bqz.Clases.cMedidoresAdaptador;
import com.example.pcm_jzq_bqz.Clases.cMedidor;

import java.util.List;

import io.realm.Realm;

public class InformeActivity extends AppCompatActivity {

    //---------------------------------- VARIABLES GLOBALES ----------------------------------------
    cMedidorServicio mServicio = new cMedidorServicio(Realm.getDefaultInstance());
    List<cMedidor> mListaMedidores;
    ListView mlstListaMedidores;
    int mposicion;
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe);

        mlstListaMedidores = findViewById(R.id.lstInforme);
        fn_CargarMedidorSeleccionado();
    }
    //----------------------------------------------------------------------------------------------
    public void fn_CargarListaMedidoresSinLecturas(View view)
    {
        mListaMedidores = mServicio.fn_CargarListaMedidoresSinLectura();
        cMedidoresAdaptador mAdaptador = new cMedidoresAdaptador(InformeActivity.this,
            mListaMedidores, R.layout.adaptador_medidores);
        mlstListaMedidores.setAdapter(mAdaptador);
    }
    //----------------------------------------------------------------------------------------------
    public void fn_CargarListaMedidoresConLecturas(View view)
    {
        mListaMedidores = mServicio.fn_CargarListaMedidoresConLectura();
        cMedidoresAdaptador mAdaptador = new cMedidoresAdaptador(InformeActivity.this,
                mListaMedidores, R.layout.adaptador_medidores);
        mlstListaMedidores.setAdapter(mAdaptador);
    }
    //----------------------------------------------------------------------------------------------
    public void fn_Regresar(View view)
    {
        this.finish();
    }
    //----------------------------------------------------------------------------------------------
    private void fn_CargarMedidorSeleccionado()
    {
        mlstListaMedidores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {

                Intent mEditarMedidor = new Intent(InformeActivity.this, EditarMedidorActivity.class);
                cMedidor mMedidor = new cMedidor(); //add
                mMedidor = mServicio.buscarMedidorXNombre(mlstListaMedidores.getItemAtPosition(posicion).toString());
                mEditarMedidor.putExtra("CodigoMedidor", mMedidor.getSecuencia());
                startActivity(mEditarMedidor);
            }
        });
    }
    //----------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------
}