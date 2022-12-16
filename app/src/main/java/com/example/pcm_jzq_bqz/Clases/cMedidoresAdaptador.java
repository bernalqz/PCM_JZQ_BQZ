package com.example.pcm_jzq_bqz.Clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pcm_jzq_bqz.R;

import java.util.List;

public class cMedidoresAdaptador extends BaseAdapter {

    //------------------------------------ VARIABLES GLOBALES --------------------------------------
    private Context context;
    private List<cMedidor> mLista;
    private int layout;
    //----------------------------------------------------------------------------------------------
    public cMedidoresAdaptador(Context _context, List<cMedidor> _Lista, int _layout)
    {
        this.context = _context;
        this.mLista = _Lista;
        this.layout = _layout;
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public int getCount() {
        return mLista.size();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public Object getItem(int i) {
        return mLista.get(i);
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public long getItemId(int i) {
        return i;
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public View getView(int posicion, View convertView, ViewGroup parent)
    {
        cMedidoresAdaptador.ViewHolder vh;
        vh = new cMedidoresAdaptador.ViewHolder();
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(layout,null);
            vh.Secuencia = convertView.findViewById(R.id.tvAMSecuencia);
            vh.Cliente = convertView.findViewById(R.id.tvAMCliente);
            vh.Estado = convertView.findViewById(R.id.tvAMEstado);
            convertView.setTag(vh);
        }
        else
        {
            vh = (cMedidoresAdaptador.ViewHolder) convertView.getTag();
        }

        cMedidor mMedidor = mLista.get(posicion);
        int mCodigoMedidor = mMedidor.getSecuencia();
        vh.Secuencia.setText("Medidor: " + String.valueOf(mCodigoMedidor));
        vh.Cliente.setText("Cliente: " + mMedidor.getNombreCliente());
        vh.Estado.setText("Estado: " + mMedidor.getEstado());
        return convertView;
    }
    //----------------------------------------------------------------------------------------------
    public class ViewHolder
    {
        TextView Secuencia;
        TextView Cliente;
        TextView Estado;
    }
    //----------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------
}
