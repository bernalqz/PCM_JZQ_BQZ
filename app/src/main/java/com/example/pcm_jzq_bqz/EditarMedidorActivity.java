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
    int Dia, Mes, Year,mCodigoMedidor,mLectura,mSector;

    cMedidor mMedidor;
    cMedidorServicio mServicioMedidor = new cMedidorServicio(Realm.getDefaultInstance());
    EditText mNombre, mNumeroMedidor, mEstado;
    TextView mFecha;
    RadioButton mActivo, mInactivo;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_medidor);

        //
        //mSector = findViewById(R.id.txteMedidorSector);
        mNombre = findViewById(R.id.txteMedidorDuenio);
        mNumeroMedidor = findViewById(R.id.txteMedidorNumero);
        //mLectura = findViewById(R.id.txteMedidorLectura);
        mActivo = findViewById(R.id.rbeMedidorActivo);
        mInactivo = findViewById(R.id.rbeMedidorInactivo);
        //Inicializa();
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
            //Cargar Datos//
            mNumeroMedidor.setText(Integer.toString(mMedidor.getCodigoMedidor()));


            //mSector.setText(Integer.toString(mMedidor.getCodigoSector()));
            mNombre.setText((mMedidor.getNombreCliente()));
            //mLectura.setText(Integer.toString(mMedidor.getLectura()));
            //mEstado = mMedidor.getEstado();
            /*if(mActivoInact.trim().equals("Activo"))
            {
                mActivo.setChecked(true);
                mInactivo.setChecked(false);
            }
                }

      */
    }}

    //---------------------

    //private void Inicializa()
   /*
    {
        //mSector.setText("");
        mNombre.setText("");
        mNumeroMedidor.setText("");
       //mLectura.setText(0);


    }

    */
// -------------------
        /*
public String fn_ObtenerFecha()
{
    Calendar mFecha = Calendar.getInstance();
    Dia = mFecha.get(Calendar.DAY_OF_MONTH);
    Mes = mFecha.get(Calendar.MONTH);
    Year = mFecha.get(Calendar.YEAR);
    String mDate = Mes + "/" + Dia + "/" + Year;
    return mDate;
}
*/

//-----------------
/*
    public void fn_EditarMedidor(View v)
    {

                try
                {

                    mServicioMedidor.fn_ActualizarMedidor(mCodigoMedidor,1,mNombre.getText().toString(),0,mActivoInact);

                    Toast.makeText(this, "Editado: ", Toast.LENGTH_SHORT).show();


                }catch (Exception e)
                {
                    Toast.makeText(this, "Error al editar", Toast.LENGTH_SHORT).show();
                }
            }




    //-----Integer.getInteger(mSector.getText().toString())

*/


}