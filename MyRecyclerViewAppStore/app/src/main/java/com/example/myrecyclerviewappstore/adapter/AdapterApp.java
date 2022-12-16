package com.example.myrecyclerviewappstore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myrecyclerviewappstore.R;
import com.example.myrecyclerviewappstore.model.App;
import com.example.myrecyclerviewappstore.model.Produto;

import java.util.ArrayList;

public class AdapterApp extends RecyclerView.Adapter<AdapterApp.AppViewHolder>{

    Context context;
    ArrayList<App> apps;

    public AdapterApp(Context context, ArrayList<App> apps) {
        this.context = context;
        this.apps = apps;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_app, viewGroup, false);
        return new AppViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {

        Produto app = apps.get(position);

        holder.icone.setImageBitmap(app.getIcone());
        holder.nome.setText(app.getNome());
        holder.nota.setText(String.valueOf(app.getAvaliacao()));

    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {

        ImageView icone;
        TextView nome;
        TextView nota;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);

            icone = itemView.findViewById(R.id.imagemApp);
            nome = itemView.findViewById(R.id.nomeApp);
            nota = itemView.findViewById(R.id.notaApp);

        }

    }

}
