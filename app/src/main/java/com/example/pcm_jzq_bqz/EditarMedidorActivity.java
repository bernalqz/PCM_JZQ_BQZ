package com.example.pcm_jzq_bqz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class EditarMedidorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_medidor);
    }
    //
    public void fn_Regresar(View view)
    {
        this.finish();
    }
    //
}