package com.dev.celulaspaz.service;

import com.dev.celulaspaz.model.Recompensa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Irving on 26/03/2017.
 */

public interface ApiRecompensa
{

    @GET("cupon")
    Call<List<Recompensa> >getRecompensas();
}
