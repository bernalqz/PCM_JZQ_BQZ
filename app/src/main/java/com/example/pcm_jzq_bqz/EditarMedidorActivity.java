package com.example.pcm_jzq_bqz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pcm_jzq_bqz.Clases.cMedidor;
import com.example.pcm_jzq_bqz.Clases.cMedidorServicio;
import com.example.pcm_jzq_bqz.Clases.cSector;
import com.example.pcm_jzq_bqz.Clases.cSectorServicio;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;

public class EditarMedidorActivity extends AppCompatActivity {

    //------------------------------------- VARIABLES GLOBALES -------------------------------------
    cMedidor mMedidor;
    cMedidorServicio mServicioMedidor = new cMedidorServicio(Realm.getDefaultInstance());

    //cSector moSector;
    //cSectorServicio mServicioSector = new cSectorServicio(Realm.getDefaultInstance());

    EditText mNombre;
    TextView mFecha,mSector,mNumeroMedidor, mLectura,mFechaLectura, mNombreSector;
    String mEstado;
    int Dia, Mes, Year, mCodigoMedidor,sector;
    RadioButton mActivo, mInactivo;
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_medidor);

        //
        mSector = findViewById(R.id.tvSectorMedidor);
        //mNombreSector = findViewById(R.id.tvNombreSectorMedidor);
        mNombre = findViewById(R.id.txteMedidorDuenio);
        mNumeroMedidor = findViewById(R.id.tvNumeroMedidor);
        mLectura = findViewById(R.id.tvLecturaMedidor);
        mFechaLectura = findViewById(R.id.tvFechaLectura);
        mActivo = findViewById(R.id.rbeMedidorActivo);
        mInactivo = findViewById(R.id.rbeMedidorInactivo);
        mFecha = findViewById(R.id.tveFecha);
        mFecha.setText(getFecha());
        LeerPutExtra();
        //
    }

    //----------------------------------------------------------------------------------------------
    @Override
    public void onResume()
    {
        super.onResume();
    }
    //----------------------------------------------------------------------------------------------
    public void fn_Regresar(View view) {
        this.finish();
    }
    //----------------------------------------------------------------------------------------------
    public void BorrarMedidor(View v)
    {
        mServicioMedidor.fn_EliminarMedidor(mCodigoMedidor);
        finish();
    }


    //----------------------------------------------------------------------------------------------
    private void LeerPutExtra() {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mCodigoMedidor = mBundle.getInt("CodigoMedidor", 0);
            mMedidor = mServicioMedidor.fn_BuscarMedidorPorCodigo(mCodigoMedidor);
            //Cargar Datos//

            mSector.setText("Sector: "+mMedidor.getCodigoSector());
            mNombre.setText(mMedidor.getNombreCliente());
            mNumeroMedidor.setText("Numero de Medidor: "+Integer.toString(mMedidor.getCodigoMedidor()));
            mLectura.setText("Lectura Actual: "+Integer.toString(mMedidor.getLectura()));
            mFechaLectura.setText("fecha ultima lectura: "+(mMedidor.getFechaLectura()));
            mEstado = mMedidor.getEstado().toString();

            if (mEstado.trim().equals("Activo")) {
                mActivo.setChecked(true);
                mInactivo.setChecked(false);}
            else{
                mActivo.setChecked(false);
                mInactivo.setChecked(true);}
             }
        }
    //----------------------------------------------------------------------------------------------
    public void fn_EditarMedidor(View v)
    {
        try
        {
            mServicioMedidor.fn_ActualizarMedidor(mCodigoMedidor,mNombre.getText().toString(),mEstado);
            Toast.makeText(this, "Editado: ", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error al editar", Toast.LENGTH_SHORT).show();
        }
    }
    //----------------------------------------------------------------------------------------------
    private String getFecha()
    {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        return formattedDate.toString();
    }
    //----------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------
}
