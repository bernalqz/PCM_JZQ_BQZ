package com.example.pcm_jzq_bqz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pcm_jzq_bqz.Clases.cSectorServicio;

import io.realm.Realm;

public class NuevoSectorActivity extends AppCompatActivity {

    //----------------------------------- VARIABLES GLOBALES ---------------------------------------
    TextView mNombre, mCanton;
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_sector);

        mNombre = findViewById(R.id.txtNuevoSectorNombre);
        mCanton = findViewById(R.id.txtNuevoSectorCanton);

        fn_Inicializar();
    }
    //----------------------------------------------------------------------------------------------
    public void fn_Regresar(View view)
    {
        this.finish();
        fn_Inicializar();
    }
    //----------------------------------------------------------------------------------------------
    private void fn_Inicializar()
    {
        mNombre.setText("");
        mCanton.setText("");
    }
    //----------------------------------------------------------------------------------------------
    public void fn_AgregarSector(View view)
    {
        if(mNombre.getText().toString().trim().equals(""))
        {
            if(mCanton.getText().toString().trim().equals(""))
            {
                Toast.makeText(this, "Digite un nombre y un canton", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Digite un nombre", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            if(mCanton.getText().toString().trim().equals(""))
            {
                Toast.makeText(this, "Digite una canton", Toast.LENGTH_SHORT).show();
            }
            else
            {
                try
                {
                    cSectorServicio mServicio = new cSectorServicio(Realm.getDefaultInstance());
                    mServicio.fn_AgregarSector(mNombre.getText().toString(), mCanton.getText().toString());
                    fn_Inicializar();
                    Toast.makeText(this, "Sector agregado", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e)
                {
                    Toast.makeText(this, "Error al agregar", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
    //----------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------
}