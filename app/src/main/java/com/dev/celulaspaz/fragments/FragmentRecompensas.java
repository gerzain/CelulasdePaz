package com.dev.celulaspaz.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dev.celulaspaz.R;
import com.dev.celulaspaz.adapters.RecompensaAdapter;
import com.dev.celulaspaz.model.Recompensa;
import com.dev.celulaspaz.service.ApiRecompensa;
import com.dev.celulaspaz.util.OnClickRecompensa;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Irving on 25/03/2017.
 */

public class FragmentRecompensas extends Fragment implements OnClickRecompensa
{

    private RecyclerView recyclerView;
    private List<Recompensa> recompensas;
    private RecompensaAdapter recompensaAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
       View view=inflater.inflate(R.layout.fragment_recompensa,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.rvRecompensas);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this.getContext(),2);
        recyclerView.setHasFixedSize(true);
        recompensas=new ArrayList<>();
        recyclerView.setLayoutManager(gridLayoutManager);
        recompensaAdapter=new RecompensaAdapter(this);
        recyclerView.setAdapter(recompensaAdapter);
        new Api().execute();

        return view;
    }
    private class Api extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            recompensaAdapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm")
                    .create();
            final String ulr = "http://192.168.1.72:5000/api/";
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ulr)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            ApiRecompensa apiRecompensa=retrofit.create(ApiRecompensa.class);
            final Call< List<Recompensa> > respuesta=apiRecompensa.getRecompensas();
            respuesta.enqueue(new Callback<List<Recompensa>>() {
                @Override
                public void onResponse(Call<List<Recompensa>> call, Response<List<Recompensa>> response) {
                    for(Recompensa recompensa: response.body())
                    {
                        recompensas.add(recompensa);

                    }
                    getActivity().runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            recompensaAdapter.setDataset(recompensas);
                            recyclerView.setAdapter(recompensaAdapter);
                        }
                    });
                }

                @Override
                public void onFailure(Call<List<Recompensa>> call, Throwable t)
                {
                   // Snackbar.make(getActivity().findViewById(R.id.vista_recompensa), t.getMessage(), Snackbar.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });

            return null;
        }
    }

    @Override
    public void onItemClick(Recompensa recompensa)
    {
       // Snackbar.make(getActivity().findViewById(R.id.vista_recompensa), "ClickRecompensa", Snackbar.LENGTH_SHORT).show();
    }
}
