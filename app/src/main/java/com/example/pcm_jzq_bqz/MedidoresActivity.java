package com.example.pcm_jzq_bqz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MedidoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medidores);
    }

    // ----------------
    public void fn_Regresar(View view)
    {
        this.finish();
    }

    // ----------------------------------
    public void frmIrAgregarMedidor(View v)
    {
        Intent mPantalla = new Intent(this,NuevoMedidorActivity.class);
        startActivity(mPantalla);
    }
    // ------------------------------------

    public void frmIrEditarMedidor(View v)
    {
        Intent mPantalla = new Intent(this,EditarMedidorActivity.class);
        startActivity(mPantalla);
    }
    // ------------------------------------

}