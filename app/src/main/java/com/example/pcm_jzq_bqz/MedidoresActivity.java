package com.example.pcm_jzq_bqz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pcm_jzq_bqz.Clases.cMedidor;
import com.example.pcm_jzq_bqz.Clases.cMedidorServicio;
import com.example.pcm_jzq_bqz.Clases.cMedidoresAdaptador;
import com.example.pcm_jzq_bqz.Clases.cSector;
import com.example.pcm_jzq_bqz.Clases.cSectorAdaptador;

import java.util.List;

import io.realm.Realm;

public class MedidoresActivity extends AppCompatActivity {

    //---------------------------------- VARIABLES GLOBALES ----------------------------------------
    cMedidorServicio mServicio = new cMedidorServicio(Realm.getDefaultInstance());
    cMedidor mMedidor = new cMedidor();
    List<cMedidor> mListaMedidores;
    ListView mlstListaMedidores;
    int mposicion = -1;
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medidores);
    //
        mlstListaMedidores = findViewById(R.id.lstListaMedidores);

        fn_CargarListaMedidores();
        fn_CargarMedidorSeleccionado();
    //
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void onResume()
    {
        super.onResume();
        //

        fn_CargarListaMedidores();
        fn_CargarMedidorSeleccionado();
        //
    }
    //----------------------------------------------------------------------------------------------
    private void fn_CargarListaMedidores()
    {
        int _CodigoSector = fn_CargarSharePreferences();
        mListaMedidores = mServicio.fn_CargarListaMedidores(_CodigoSector);
        cMedidoresAdaptador mAdaptador = new cMedidoresAdaptador(MedidoresActivity.this,
                mListaMedidores, R.layout.adaptador_medidores);
        mlstListaMedidores.setAdapter(mAdaptador);
    }
    //----------------------------------------------------------------------------------------------
    public void fn_Regresar(View view)
    {
        this.finish();
    }
    //----------------------------------------------------------------------------------------------
    public void frmIrAgregarMedidor(View v)
    {
        Intent mPantalla = new Intent(this, NuevoMedidorActivity.class);
        startActivity(mPantalla);
    }
    //----------------------------------------------------------------------------------------------
    public void frmIrEditarMedidor(View v) {
        if (mposicion != -1) {
            Intent mEditarMedidor = new Intent(MedidoresActivity.this, EditarMedidorActivity.class);
            cMedidor mMedidor = mListaMedidores.get(mposicion);
            mEditarMedidor.putExtra("CodigoMedidor", mMedidor.getSecuencia());

            startActivity(mEditarMedidor);
        } else {
            Toast.makeText(MedidoresActivity.this, "Debe de seleccionar un Medidor",
                    Toast.LENGTH_SHORT).show();
        }
    }
    //----------------------------------------------------------------------------------------------
    private int fn_CargarSharePreferences()
    {
        SharedPreferences mCodigo = getSharedPreferences("CodigoSector", Context.MODE_PRIVATE);
        String mCodigoSector = mCodigo.getString("Codigo", "Sector nulo");
        int mCodigo01 = Integer.parseInt(mCodigoSector);

        return mCodigo01;
    }
    //----------------------------------------------------------------------------------------------
    public void fn_BorrarMedidor(View v) {
        if (mposicion != -1) {
            cMedidor mMedidor = mListaMedidores.get(mposicion);
            mServicio.fn_EliminarMedidor(mMedidor.getCodigoMedidor());

            Toast.makeText(this, "Medidor eliminado", Toast.LENGTH_SHORT).show();
            fn_CargarListaMedidores();

        } else {
            Toast.makeText(MedidoresActivity.this, "Debe de seleccionar un Medidor",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //----------------------------------------------------------------------------------------------
    private void fn_CargarMedidorSeleccionado()
    {

        mlstListaMedidores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                mposicion = posicion;
                cMedidor mMedidor = new cMedidor();
                mMedidor = mServicio.fn_BuscarMedidorPorCodigo(posicion+1);
                Toast.makeText(MedidoresActivity.this, "Sector " + mMedidor.getNombreCliente() + " seleccionado" ,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    //----------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------
}