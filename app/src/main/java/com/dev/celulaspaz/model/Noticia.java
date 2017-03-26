package com.dev.celulaspaz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Irving on 25/03/2017.
 */

public class Noticia
{
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("titulo")
    @Expose
    private String titulo;
    @SerializedName("texto")
    @Expose
    private String texto;
    @SerializedName("fecha")
    @Expose
    private String fecha;
    @SerializedName("imagen")
    @Expose
    private String imagen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
