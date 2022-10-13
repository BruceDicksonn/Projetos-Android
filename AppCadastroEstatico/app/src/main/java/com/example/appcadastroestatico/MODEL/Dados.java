package com.example.appcadastroestatico.MODEL;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dados {

    private CircleImageView foto;
    private String nome;
    private String email;
    private String senha;

    public Dados(CircleImageView f,String n,String e, String s){

        this.foto  = f;
        this.nome  = n;
        this.email = e;
        this.senha = s;

    }

    public CircleImageView getFoto() {
        return foto;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
