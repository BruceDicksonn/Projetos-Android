package com.example.appcadastroestatico.DAO;

import com.example.appcadastroestatico.MODEL.Dados;

import java.util.ArrayList;
import java.util.List;

public class DaoDados {

    public static List<Dados> data = new ArrayList<>();

    public static void addData(Dados dados){
        data.add(dados);
    }

    public static Dados get(){
        if(data.isEmpty()) return null;
        else return data.get(0);
    }

}
