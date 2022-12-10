package com.example.pcm_jzq_bqz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pcm_jzq_bqz.Clases.cSector;
import com.example.pcm_jzq_bqz.Clases.cSectorAdaptador;
import com.example.pcm_jzq_bqz.Clases.cSectorServicio;

import java.util.List;

import io.realm.Realm;

public class SectoresActivity extends AppCompatActivity {
    cSectorServicio mServicio = new cSectorServicio(Realm.getDefaultInstance());
    //
    List<cSector> mListaSectores;
    ListView mlstListaSectores;
    cSector mSector = new cSector();
    int mposicion;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sectores);

        mlstListaSectores = findViewById(R.id.lstListaSectores);

        fn_CargarListaSectores();
        fn_CargarSectorSeleccionado();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        fn_CargarListaSectores();
    }
// ----------------------------
    private void fn_CargarListaSectores()
    {
        mListaSectores = mServicio.fn_ListaSectores();
        cSectorAdaptador mSectorAdaptador = new cSectorAdaptador(this,mListaSectores,R.layout.adaptador_sectores);
        mlstListaSectores.setAdapter(mSectorAdaptador);
    }
//----------------------------

    public void fn_Regresar(View view)
    {
        this.finish();
    }
// -----------------------------
    public void fn_CargarNuevoSectorActivity(View view)
    {
        Intent mPantalla = new Intent(SectoresActivity.this,NuevoSectorActivity.class);
        startActivity(mPantalla);
    }
//-----------------------------
    private void fn_CargarSectorSeleccionado()
    {
        mlstListaSectores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                mposicion = posicion;
            mSector = mServicio.fn_BuscarSectorPorCodigo(posicion+1);
                Toast.makeText(SectoresActivity.this, "Sector seleccionado: " + mSector.getNombre(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
// ------------------------------

    public void fn_EditarSectorActivity(View view)
    {
        Intent mEditarSector = new Intent(SectoresActivity.this,EditarSectorActivity.class);
        cSector mSector = mListaSectores.get(mposicion);
        mEditarSector.putExtra("CodigoSector",mSector.getCodigoSector());
        startActivity(mEditarSector);
    }




// -----------------------------




/*
    public void fn_ValidarCheckBox(View view)
    {
        CheckBox checkBox = findViewById(R.id.cbCheckBox);

        if(checkBox.isChecked() == true)
        {
            Toast.makeText(this, "CheckBox seleccionado", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "CheckBox sin seleccionar", Toast.LENGTH_SHORT).show();
        }

    }

*/


//
}