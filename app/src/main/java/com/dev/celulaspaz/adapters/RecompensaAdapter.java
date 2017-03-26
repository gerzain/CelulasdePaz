package com.dev.celulaspaz.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.celulaspaz.R;
import com.dev.celulaspaz.model.Recompensa;
import com.dev.celulaspaz.util.OnClickRecompensa;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Irving on 25/03/2017.
 */

public class RecompensaAdapter extends RecyclerView.Adapter<RecompensaAdapter.ViewHolder>
{
    private List<Recompensa> recompensas;
    private OnClickRecompensa onClickItem;

    public RecompensaAdapter(OnClickRecompensa onClickItem) {
        this.recompensas=new ArrayList<>(0);
        this.onClickItem = onClickItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recompensa,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Recompensa recompensa=recompensas.get(position);
        holder.recompensa.setText(recompensa.getPromocion());
        holder.des_recompensa.setText(recompensa.getCodigo());
        Picasso.with(holder.itemView.getContext())
                .load(recompensa.getImagen())
                .into(holder.miniatura_recompensa);
        holder.setOnItemClickListener(recompensa,onClickItem);
    }
    public void setDataset(List<Recompensa> dataset)
    {
        this.recompensas=dataset;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        return recompensas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.miniatura_recompensa)
        CircleImageView miniatura_recompensa;
        @BindView(R.id.tv_nombre_recompensa)
        TextView recompensa;
        @BindView(R.id.tv_des_recompensa)
        TextView des_recompensa;
        public ViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        public void setOnItemClickListener(final Recompensa r, final OnClickRecompensa onItemClickListener)
        {
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    onItemClickListener.onItemClick(r);
                }
            });
        }


    }


}
