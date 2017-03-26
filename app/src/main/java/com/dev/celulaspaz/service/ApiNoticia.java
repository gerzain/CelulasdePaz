package com.dev.celulaspaz.service;

import com.dev.celulaspaz.model.Noticia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Irving on 25/03/2017.
 */

public interface ApiNoticia
{

    @GET("getAll")
    Call<List<Noticia> > getNoticias();
}
