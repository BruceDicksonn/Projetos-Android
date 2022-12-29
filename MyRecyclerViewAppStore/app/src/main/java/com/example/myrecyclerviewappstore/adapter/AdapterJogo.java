package com.example.myrecyclerviewappstore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myrecyclerviewappstore.R;
import com.example.myrecyclerviewappstore.model.Jogo;
import com.example.myrecyclerviewappstore.model.Produto;

import java.util.ArrayList;

public class AdapterJogo extends RecyclerView.Adapter<AdapterJogo.JogoViewHolder> {

    Context context;
    ArrayList<Jogo> jogos;

    public AdapterJogo(Context context, ArrayList<Jogo> jogos) {
        this.context = context;
        this.jogos = jogos;
    }

    @NonNull
    @Override
    public JogoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_jogo, viewGroup, false);
        return new JogoViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull JogoViewHolder holder, int position) {

        Produto jogo = jogos.get(position);

        holder.icone.setImageBitmap(jogo.getIcone());
        holder.nome.setText(jogo.getNome());
        holder.nota.setText(String.valueOf(jogo.getAvaliacao()) + " ★");

    }

    @Override
    public int getItemCount() {
        return jogos.size();
    }

    public class JogoViewHolder extends RecyclerView.ViewHolder {

        ImageView icone;
        TextView nome;
        TextView nota;

        public JogoViewHolder(@NonNull View itemView) {
            super(itemView);

            icone = itemView.findViewById(R.id.imagemJogo);
            nome = itemView.findViewById(R.id.nomeJogo);
            nota = itemView.findViewById(R.id.notaJogo);

        }
    }

}
