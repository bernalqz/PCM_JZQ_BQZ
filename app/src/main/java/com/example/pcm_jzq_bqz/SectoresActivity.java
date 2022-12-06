package com.example.pcm_jzq_bqz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class SectoresActivity extends AppCompatActivity {
    //
    cSectorServicio mServicio = new cSectorServicio(Realm.getDefaultInstance());
    List<cSector> mListaSectores;
    ListView mlstListaSectores;
    //

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
    //

    @Override
    public void onResume()
    {
        super.onResume();
        fn_CargarListaSectores();
    }

    public void fn_Regresar(View view)
    {
        this.finish();
    }

    public void fn_NuevoSector(View view)
    {
        Intent mPantalla = new Intent(this,NuevoSectorActivity.class);
        startActivity(mPantalla);
    }

    private void fn_CargarListaSectores()
    {
        mListaSectores = mServicio.fn_ListaSectores();
        cSectorAdaptador mSectorAdaptador = new cSectorAdaptador(this,mListaSectores,R.layout.adaptador_sectores);
        mlstListaSectores.setAdapter(mSectorAdaptador);
    }
    //-----------------------------------
    private void fn_CargarSectorSeleccionado()
    {
        mlstListaSectores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                cSector mSector = new cSector();
                mSector = mServicio.fn_BuscarSectorPorCodigo(posicion+1);
                String mMensaje = "Elemento seleccionado: " + mSector.getNombre();
                Toast.makeText(SectoresActivity.this, mMensaje, Toast.LENGTH_SHORT).show();

                Intent mPantalla = null;
                switch (posicion)
                {
                    case 0:
                        mPantalla = new Intent(SectoresActivity.this,MedidoresActivity.class);
                        break;
                    default:
                        mPantalla = new Intent(SectoresActivity.this,MedidoresActivity.class);
                        break;
                }
                startActivity(mPantalla);
            }
        });
    }



    //
}