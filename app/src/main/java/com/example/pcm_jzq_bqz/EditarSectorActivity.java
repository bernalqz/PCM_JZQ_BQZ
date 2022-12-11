package com.example.pcm_jzq_bqz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pcm_jzq_bqz.Clases.cSector;
import com.example.pcm_jzq_bqz.Clases.cSectorServicio;

import io.realm.Realm;

public class EditarSectorActivity extends AppCompatActivity {
    //

    int mCodigoSector;
    cSector mSector;
    cSectorServicio mServicioSector = new cSectorServicio(Realm.getDefaultInstance());
    EditText mNombre,mCanton;


    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_sector);
        //
        mNombre = findViewById(R.id.txteSectorNombre);
        mCanton = findViewById(R.id.txteCanton);
        Inicializa();
        LeerPutExtra();
      //  LeerPutExtra();

        //


    }
    //----------------------
    public void fn_Regresar(View view)
    {
        this.finish();
    }

 //---------------------

    private void Inicializa()
    {
        mNombre.setText("");
        mCanton.setText("");

    }
// -------------------


    private void LeerPutExtra()
    {
        Bundle mBundle=getIntent().getExtras();
        if (mBundle!=null)
        {
            mCodigoSector = mBundle.getInt("CodigoSector",0);
            mSector = mServicioSector.fn_BuscarSectorPorCodigo(mCodigoSector);
            mNombre.setText(mSector.getNombre());
            mCanton.setText(mSector.getCanton());


        }

    }
   //-----

    public void ActualizarSector(View v)
    {

        mServicioSector.fn_ActualizarSector(mCodigoSector,mNombre.getText().toString(),mCanton.getText().toString());

        Toast.makeText(this,"Usuario actualizado",Toast.LENGTH_SHORT).show();
    }


  //------


    public void BorrarSector(View v)
    {

        mServicioSector.fn_EliminarSector(mCodigoSector);
        finish();


    }



  // ------
}