package com.example.ap2.Dao;

import com.example.ap2.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuariosDao {

    private static List<Usuario> listaUsuariosCadastrados = new ArrayList<>();

    public UsuariosDao(){
        preencherListaUsuarios();
    }

    public List<Usuario> getListaUsuarios(){
        return new ArrayList<>(listaUsuariosCadastrados);
    }

    public static void salvar(Usuario usuario){
        listaUsuariosCadastrados.add(usuario);
    }

    private void preencherListaUsuarios(){
        listaUsuariosCadastrados.add(new Usuario(new String[]{"Bruce Dickinson","bruce@gmail.com","123456"}));
        listaUsuariosCadastrados.add(new Usuario(new String[]{"Arlison Martins","arlison@gmail.com","123456"}));
        listaUsuariosCadastrados.add(new Usuario(new String[]{"Thcylla Sá","thcylla@gmail.com","123456"}));
        listaUsuariosCadastrados.add(new Usuario(new String[]{"Vitor Martins","vitor@gmail.com","123456"}));
    }

}
