package com.dev.celulaspaz.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.celulaspaz.R;
import com.dev.celulaspaz.model.Noticia;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Irving on 25/03/2017.
 */

public class NoticiaAdapter  extends RecyclerView.Adapter<NoticiaAdapter.ViewHolder>
{

    private List<Noticia> noticias;
    private Context context;

    public NoticiaAdapter( Context context)
    {
        this.noticias = new ArrayList<>();
        this.context = context;
    }
    private Context getContext()
    {
        return context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_noticia,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final Noticia noticia=noticias.get(position);
        holder.titulo_noticia.setText(noticia.getTitulo());
        holder.des_noticia.setText(noticia.getTexto());
        holder.fecha_noticia.setText(noticia.getFecha());
        Picasso.with(holder.itemView.getContext())
                .load(noticia.getImagen())
                .into(holder.img_noticia);
    }
    public void setDataset(List<Noticia> dataset)
    {
        this.noticias=dataset;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        return noticias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        @BindView(R.id.tvTituloNoticia)
        TextView titulo_noticia;
        @BindView(R.id.tvnotica_des)
        TextView des_noticia;
        @BindView(R.id.ivNoticia)
        ImageView img_noticia;
        @BindView(R.id.fecha_noticia)
        TextView fecha_noticia;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }

}
