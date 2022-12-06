package com.example.pcm_jzq_bqz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void frmIrAInformeAtivity(View v)
    {
        Intent mPantalla = new Intent(this,InformeActivity.class);
        startActivity(mPantalla);
    }
    // ----------------------------------
    public void frmIrALecturaMedidoresAtivity(View v)
    {
        Intent mPantalla = new Intent(this,LecturaMedidoresActivity.class);
        startActivity(mPantalla);
    }
    // ------------------------------------
    public void frmIrAMedidoresAtivity(View v)
    {
        Intent mPantalla = new Intent(this,MedidoresActivity.class);
        startActivity(mPantalla);
    }
    // --------------------
    public void fn_SectoresActivity(View view)
    {
        Intent mPantalla = new Intent(this,SectoresActivity.class);
        startActivity(mPantalla);

    }
    // --------------------
    public void frmRegresarSalir(View v)
    {
        finish();
    }
    // ----------------------------

    //
}