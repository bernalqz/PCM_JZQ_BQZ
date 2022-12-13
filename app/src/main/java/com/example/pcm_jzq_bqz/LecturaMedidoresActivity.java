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

public class LecturaMedidoresActivity extends AppCompatActivity {
    cSectorServicio mServicio = new cSectorServicio(Realm.getDefaultInstance());
    List<cSector> mListaSectores;
    ListView mlstListaSectores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectura_medidores);

        mlstListaSectores = findViewById(R.id.lstLMListaSectores);

        fn_CargarListadeSectores();
        fn_CargarSectorSeleccionado();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        fn_CargarListadeSectores();
        fn_CargarSectorSeleccionado();
    }

    public void fn_CargarListadeSectores()
    {
        mListaSectores = mServicio.fn_ListaSectores();
        cSectorAdaptador mAdaptador = new cSectorAdaptador(this,mListaSectores,R.layout.adaptador_sectores);
        mlstListaSectores.setAdapter(mAdaptador);
    }

    private void fn_CargarSectorSeleccionado()
    {
        mlstListaSectores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                cSector mSector = new cSector();
                mSector = mServicio.fn_BuscarSectorPorCodigo(posicion+1);
                String CodigoSector = String.valueOf(mSector.getCodigoSector());
                String mMensaje = "Elemento seleccionado: " + mSector.getNombre();
                Toast.makeText(LecturaMedidoresActivity.this, mMensaje, Toast.LENGTH_SHORT).show();

                SharedPreferences mCodigo = getSharedPreferences("CodigoSector", Context.MODE_PRIVATE);
                SharedPreferences.Editor mAsignar = mCodigo.edit();
                mAsignar.putString("Codigo", CodigoSector);
                mAsignar.commit();

                Intent mPantalla = null;
                switch (posicion)
                {
                    case 0:
                        mPantalla = new Intent(LecturaMedidoresActivity.this,LecturasActivity.class);
                        break;
                    default:
                        mPantalla = new Intent(LecturaMedidoresActivity.this,LecturasActivity.class);
                        break;
                }
                startActivity(mPantalla);
            }
        });
    }

    public void fn_Regresar(View view)
    {
        this.finish();
    }
}