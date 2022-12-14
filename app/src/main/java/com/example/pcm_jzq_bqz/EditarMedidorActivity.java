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

import java.util.Calendar;

import io.realm.Realm;

public class EditarMedidorActivity extends AppCompatActivity {

    //
    int Dia, Mes, Year;
    int mCodigoMedidor;
    cMedidor mMedidor;
    cMedidorServicio mServicioMedidor = new cMedidorServicio(Realm.getDefaultInstance());
    EditText mSector,mNombre, mNumeroMedidor, mLectura, mEstado;
    TextView mFecha;
    String mActivoInact;
    RadioButton mActivo, mInactivo;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_medidor);
        //
        mSector = findViewById(R.id.txteMedidorSector);
        mNombre = findViewById(R.id.txteMedidorDuenio);
        mNumeroMedidor = findViewById(R.id.txteMedidorNumero);
        mLectura = findViewById(R.id.txteMedidorLectura);
        mActivo = findViewById(R.id.rbeMedidorActivo);
        mInactivo = findViewById(R.id.rbeMedidorInactivo);
        Inicializa();
        LeerPutExtra();
        //
    }
    //
    public void fn_Regresar(View view)
    {
        this.finish();
    }
    //
    // -------------------
    private void LeerPutExtra()
    {
        Bundle mBundle=getIntent().getExtras();
        if (mBundle!=null)
        {
            mCodigoMedidor = mBundle.getInt("CodigoMedidor",0);
            mMedidor = mServicioMedidor.fn_BuscarMedidorPorCodigo(mCodigoMedidor);
            mNumeroMedidor.setText(Integer.toString(mMedidor.getCodigoMedidor()));

            //Cargar Datos//
            mSector.setText(Integer.toString(mMedidor.getCodigoSector()));
            mNombre.setText((mMedidor.getNombreCliente()));
            mLectura.setText(Integer.toString(mMedidor.getLectura()));
            mActivoInact = mMedidor.getEstado();
            if(mActivoInact.trim().equals("Activo"))
            {
                mActivo.setChecked(true);
                mInactivo.setChecked(false);
            }

        }
    }

    //---------------------

    private void Inicializa()
    {
        mSector.setText("");
        mNombre.setText("");
        mNumeroMedidor.setText("");
        mLectura.setText("");


    }
// -------------------
public String fn_ObtenerFecha()
{
    Calendar mFecha = Calendar.getInstance();
    Dia = mFecha.get(Calendar.DAY_OF_MONTH);
    Mes = mFecha.get(Calendar.MONTH);
    Year = mFecha.get(Calendar.YEAR);
    String mDate = Mes + "/" + Dia + "/" + Year;
    return mDate;
}
//-----------------

    public void fn_EditarMedidor(View v)
    {

                try
                {

                    mServicioMedidor.fn_ActualizarMedidor()

                    Toast.makeText(this, "Agregado: ", Toast.LENGTH_SHORT).show();


                }catch (Exception e)
                {
                    Toast.makeText(this, "Error al agregar", Toast.LENGTH_SHORT).show();
                }
            }




    //-----




}