package com.example.pcm_jzq_bqz.Clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.pcm_jzq_bqz.R;

import java.util.List;

public class cSectorAdaptador extends BaseAdapter {
    private Context context;
    private List<cSector> mLista;
    private int layout;

    public cSectorAdaptador(Context _context, List<cSector> _Lista, int _layout)
    {
        this.context = _context;
        this.mLista = _Lista;
        this.layout = _layout;
    }

    @Override
    public int getCount() {
        return mLista.size();
    }

    @Override
    public Object getItem(int i) {
        return mLista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int posicion, View convertView, ViewGroup parent) {
        ViewHolder vh;
        vh = new cSectorAdaptador.ViewHolder();
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(layout,null);
            vh.Codigo = convertView.findViewById(R.id.tvAdaptadorCodigo);
            vh.Nombre = convertView.findViewById(R.id.tvAdaptadorNombre);
            vh.Canton = convertView.findViewById(R.id.tvAdaptadorCanton);
            convertView.setTag(vh);
        }
        else
        {
            vh = (cSectorAdaptador.ViewHolder) convertView.getTag();
        }

        cSector mSector = mLista.get(posicion);
        int mCodigoSector = mSector.getCodigoSector();
        vh.Codigo.setText(String.valueOf(mCodigoSector));
        vh.Nombre.setText(mSector.getNombre());
        vh.Canton.setText(mSector.getCanton());
        return convertView;
    }

    public class ViewHolder
    {
        TextView Codigo;
        TextView Nombre;
        TextView Canton;
        CheckBox Opcion;
    }

}
