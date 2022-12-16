package com.example.myrecyclerviewappstore.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;

import com.example.myrecyclerviewappstore.R;
import com.example.myrecyclerviewappstore.model.App;
import com.example.myrecyclerviewappstore.model.Jogo;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ControllerProduto extends AppCompatActivity {

    private ArrayList<Jogo> jogos = new ArrayList<Jogo>();
    private ArrayList<App> apps = new ArrayList<App>();

    public ControllerProduto(Context context){
        fillLists(context);
    }

    public void fillLists(Context context){
        fillApps(context);
        fillJogos(context);
    }

    private void clearLists(){
        jogos.clear();
        apps.clear();
    }

    public ArrayList<Jogo> getJogos(){
        return jogos;
    }

    public ArrayList<App> getApps(){
        return apps;
    }

    public void fillApps(Context context){
        Field[] f = R.raw.class.getFields();

        for(Field field : f){
            try {
                if(field.getName().contains("app")) {

                    int start = field.getName().indexOf('_') + 1;
                    int end = field.getName().length();

                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), field.getInt(null));
                    String nomeApp = StringUtils.capitalize(field.getName().substring(start, end));
                    Double nota = Math.floor(0.0 + Math.random() * (10.0 - 1));

                    apps.add(new App(bitmap,nomeApp,nota));
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void fillJogos(Context context){
        Field[] f = R.raw.class.getFields();

        for(Field field : f){
            try {
                if(field.getName().contains("jogo")) {

                    int start = field.getName().indexOf('_') + 1;
                    int end = field.getName().length();

                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), field.getInt(null));
                    String nomeJogo = StringUtils.capitalize(field.getName().substring(start, end));
                    Double nota = Math.floor(0.0 + Math.random() * (10.0 - 1));

                    jogos.add(new Jogo(bitmap,nomeJogo,nota));
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
