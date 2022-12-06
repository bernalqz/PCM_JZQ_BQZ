package com.example.pcm_jzq_bqz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class SectoresActivity extends AppCompatActivity {

    ListView mlstListaSectores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sectores);

        mlstListaSectores = findViewById(R.id.lstListaSectores);
    }

    public void fn_Regresar(View view)
    {
        this.finish();
    }

    public void fn_CargarNuevoSectorActivity(View view)
    {
        Intent mPantalla = new Intent(SectoresActivity.this,NuevoSectorActivity.class);
        startActivity(mPantalla);
    }

}