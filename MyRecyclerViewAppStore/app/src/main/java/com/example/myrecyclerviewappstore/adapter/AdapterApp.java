package com.example.myrecyclerviewappstore.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        return new AppViewHolder(view, viewGroup);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {

        Produto app = apps.get(position);

        holder.icone.setImageBitmap(app.getIcone());
        holder.nome.setText(app.getNome());
        holder.nota.setText(String.valueOf(app.getAvaliacao())+ " ★");

    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView icone;
        TextView nome;
        TextView nota;

        ViewGroup viewGroup;

        public AppViewHolder(@NonNull View itemView, ViewGroup viewGroup) {
            super(itemView);

            icone = itemView.findViewById(R.id.imagemApp);
            nome = itemView.findViewById(R.id.nomeApp);
            nota = itemView.findViewById(R.id.notaApp);
            viewGroup = viewGroup;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            View editTextDialog = LayoutInflater.from(context).inflate(R.layout.edit_text_dialog, this.viewGroup);

            AlertDialog.Builder builder = new AlertDialog.Builder(context)
                    .setView(editTextDialog)
                    .setPositiveButton("Abrir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            EditText edit = (EditText) ((ConstraintLayout)editTextDialog).getChildAt(1);
                            String msg  = edit.getText().toString();

                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();

                        }
                    })
                    .setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    builder.create().show();

        }
    }

}
