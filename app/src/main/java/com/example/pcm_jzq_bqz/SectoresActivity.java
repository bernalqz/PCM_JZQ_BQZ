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
import com.example.pcm_jzq_bqz.Clases.cSector;
import com.example.pcm_jzq_bqz.Clases.cSectorAdaptador;
import com.example.pcm_jzq_bqz.Clases.cSectorServicio;

import java.util.List;

import io.realm.Realm;

public class SectoresActivity extends AppCompatActivity {
    //------------------------------------ VARIABLES GLOBALES --------------------------------------
    cSectorServicio mServicio = new cSectorServicio(Realm.getDefaultInstance());
    List<cSector> mListaSectores;
    ListView mlstListaSectores;
    int mposicion=-1;
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sectores);
    //
        mlstListaSectores = findViewById(R.id.lstListaSectores);
        fn_CargarListaSectores();
        fn_CargarSectorSeleccionado();
    //
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void onResume()
    {
        super.onResume();
        fn_CargarListaSectores();
    }
    //----------------------------------------------------------------------------------------------
    private void fn_CargarSectorSeleccionado()
    {
        mlstListaSectores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int indice, long l) {

                cSector mSector = new cSector();
                mSector = mServicio.fn_BuscarSectorPorNombre(mlstListaSectores.getItemAtPosition(indice).toString());
                mposicion = mSector.getCodigoSector();
                        Toast.makeText(SectoresActivity.this, "Sector:" +
                                mSector.getNombre()+ "seleccionado", Toast.LENGTH_SHORT).show();
       }});
    }
    //----------------------------------------------------------------------------------------------
    private void fn_CargarListaSectores()
    {
        mListaSectores = mServicio.fn_ListaSectores();
        cSectorAdaptador mSectorAdaptador = new cSectorAdaptador(this,mListaSectores,R.layout.adaptador_sectores);
        mlstListaSectores.setAdapter(mSectorAdaptador);
        mposicion=-1;
    }
    //----------------------------------------------------------------------------------------------
    public void fn_Regresar(View view)
    {
        this.finish();
    }
    //----------------------------------------------------------------------------------------------
    public void fn_CargarNuevoSectorActivity(View view)
    {
        Intent mPantalla = new Intent(SectoresActivity.this,NuevoSectorActivity.class);
        startActivity(mPantalla);
    }
    //----------------------------------------------------------------------------------------------
    public void fn_EditarSectorActivity(View view) {
        if (mposicion != -1) {
            Intent mEditarSector = new Intent(SectoresActivity.this, EditarSectorActivity.class);

            mEditarSector.putExtra("CodigoSector", mposicion);
            startActivity(mEditarSector);
        } else {
            Toast.makeText(SectoresActivity.this, "Debe de seleccionar un Sector",
                    Toast.LENGTH_SHORT).show();
        }
    }
    //----------------------------------------------------------------------------------------------
    public void BorrarSector(View v)
    {
        if (mposicion != -1) {
                mServicio.fn_EliminarSector(mposicion);
                fn_CargarListaSectores();
                Toast.makeText(this, "Sector eliminado", Toast.LENGTH_SHORT).show();
                fn_CargarListaSectores();
        }
        else
        {
            Toast.makeText(SectoresActivity.this, "Debe de seleccionar un Sector",
                    Toast.LENGTH_SHORT).show();
        }
    }
    //----------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------
}