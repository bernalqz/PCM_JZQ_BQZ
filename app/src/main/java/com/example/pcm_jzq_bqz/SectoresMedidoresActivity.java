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

import com.example.pcm_jzq_bqz.Clases.cSector;
import com.example.pcm_jzq_bqz.Clases.cSectorAdaptador;
import com.example.pcm_jzq_bqz.Clases.cSectorServicio;

import java.util.List;

import io.realm.Realm;

public class SectoresMedidoresActivity extends AppCompatActivity {
    //
    cSectorServicio mServicio = new cSectorServicio(Realm.getDefaultInstance());
    List<cSector> mListaSectores;
    ListView mlstListaSectores;
    int mposicion;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sectores_medidores);

        //
        mlstListaSectores = findViewById(R.id.lstaSectores);
        fn_CargarListaSectores();
        fn_CargarSectorSeleccionado();
        //

    }
    //

    // -------------------

    @Override
    public void onResume()
    {
        super.onResume();
        fn_CargarListaSectores();
    }
    // -----------------------

    private void fn_CargarListaSectores()
    {
        mListaSectores = mServicio.fn_ListaSectores();
        cSectorAdaptador mSectorAdaptador = new cSectorAdaptador(this,mListaSectores,R.layout.adaptador_sectores);
        mlstListaSectores.setAdapter(mSectorAdaptador);
        mposicion = -1;

    }
    //----------------------------

    public void fn_RegresarAmain(View view)
    {
        this.finish();
    }
    // -------------------------
    private void fn_CargarSectorSeleccionado()
    {
        mlstListaSectores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                cSector mSector = new cSector();
                mSector = mServicio.fn_BuscarSectorPorCodigo(posicion+1);
                String CodigoSector = String.valueOf(mSector.getCodigoSector());
                String mMensaje = "Elemento seleccionado: " + mSector.getNombre();
                Toast.makeText(SectoresMedidoresActivity.this, mMensaje, Toast.LENGTH_SHORT).show();

                SharedPreferences mCodigo = getSharedPreferences("CodigoSector", Context.MODE_PRIVATE);
                SharedPreferences.Editor mAsignar = mCodigo.edit();
                mAsignar.putString("Codigo", CodigoSector);
                mAsignar.commit();

                Intent mPantalla = null;
                switch (posicion)
                {
                    case 0:
                        mPantalla = new Intent(SectoresMedidoresActivity.this,MedidoresActivity.class);
                        break;
                    default:
                        mPantalla = new Intent(SectoresMedidoresActivity.this,MedidoresActivity.class);
                        break;
                }
                startActivity(mPantalla);
            }
        });
    }
    //--------------------------------------------------

}