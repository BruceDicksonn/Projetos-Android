package com.example.myrecyclerviewappstore.view;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myrecyclerviewappstore.R;
import com.example.myrecyclerviewappstore.adapter.AdapterApp;
import com.example.myrecyclerviewappstore.adapter.AdapterJogo;
import com.example.myrecyclerviewappstore.controller.ControllerProduto;
import com.example.myrecyclerviewappstore.model.App;
import com.example.myrecyclerviewappstore.model.Jogo;
import com.example.myrecyclerviewappstore.model.Produto;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ControllerProduto cp;
    ArrayList<Jogo> jogos;
    ArrayList<App> apps;

    RecyclerView recyclerJogos;
    RecyclerView recyclerApps;

    AdapterApp adapterApp;
    AdapterJogo adapterJogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

        recyclerJogos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerApps.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerJogos.setAdapter(adapterJogo);
        recyclerApps.setAdapter(adapterApp);

        recyclerApps.setBackgroundColor(Color.parseColor("#CDCDCD"));
        recyclerJogos.setBackgroundColor(Color.parseColor("#CDCDCD"));

    }

    public void initComponents(){
        cp = new ControllerProduto(this);
        jogos = cp.getJogos();
        apps = cp.getApps();

        recyclerApps = findViewById(R.id.recyclerApp);
        recyclerJogos = findViewById(R.id.recyclerJogo);

        adapterApp = new AdapterApp(this, apps);
        adapterJogo = new AdapterJogo(this, jogos);
    }
}