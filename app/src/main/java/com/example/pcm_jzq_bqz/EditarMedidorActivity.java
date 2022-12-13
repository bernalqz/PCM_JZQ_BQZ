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
            mNumeroMedidor.setText(mMedidor.getCodigoMedidor());



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
/*
    public void fn_AgregarMedidor(View view)
    {
        if(mSector.getText().equals(""))
        {
            Toast.makeText(this, "Agregue un codigo de sector", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(mCliente.getText().equals(""))
            {
                Toast.makeText(this, "Agregue un codigo de sector y nombre del ciente",
                        Toast.LENGTH_SHORT).show();
            }
            else
            {
                String mEstado="";
                if(mActivo.isChecked()==true)
                {
                    mEstado="Activo";
                }
                else
                {
                    mEstado="Inactivo";
                }
                try
                {
                    int _CodigoSector = Integer.parseInt(mSector.getText().toString());
                    String _Fecha = fn_ObtenerFecha();

                    cMedidorServicio mServicio = new cMedidorServicio(Realm.getDefaultInstance());
                    mServicio.fn_AgregarMedidor(_CodigoSector, _Fecha, mCliente.getText().toString(), mEstado);
                    Toast.makeText(this, "Agregado: "+mServicio.buscarMedidorXNombre(mCliente.getText().toString()), Toast.LENGTH_SHORT).show();
                    fn_Inicializar();

                }catch (Exception e)
                {
                    Toast.makeText(this, "Error al agregar", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
*/


    //-----




}