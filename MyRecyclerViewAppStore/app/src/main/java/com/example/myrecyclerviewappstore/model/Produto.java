package com.example.myrecyclerviewappstore.model;

import android.graphics.Bitmap;

public class Produto {

    private Bitmap icone;
    private String nome;
    private Double avaliacao;

    public Produto(Bitmap icone, String nome, Double avaliacao) {
        this.icone = icone;
        this.nome = nome;
        this.avaliacao = avaliacao;
    }

    public Bitmap getIcone() {
        return icone;
    }

    public void setIcone(Bitmap icone) {
        this.icone = icone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }
}
