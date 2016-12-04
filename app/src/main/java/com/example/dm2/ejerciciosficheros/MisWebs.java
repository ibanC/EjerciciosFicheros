package com.example.dm2.ejerciciosficheros;

/**
 * Created by iban on 03/12/2016.
 */
public class MisWebs {
    private String texto;
    private String url;
    private String id;

    public MisWebs(String texto, String url, String id) {
        this.texto = texto;
        this.url = url;
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }


}
