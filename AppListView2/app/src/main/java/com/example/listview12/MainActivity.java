package com.example.listview12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> alunos = new ArrayList<>();
    EditText aluno;
    Button btnAddAluno;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Listar Alunos");

        aluno = findViewById(R.id.txtNomeCadastrado);
        btnAddAluno = findViewById(R.id.btnAddAluno);
        listview = findViewById(R.id.listaDeAlunos);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,alunos);

        btnAddAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!aluno.getText().toString().isEmpty()){
                    addAlunoNaLista(aluno.getText().toString());
                    listview.setAdapter(getAdapterListaAlunos());
                    aluno.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Preencha o campo com um nome!", Toast.LENGTH_SHORT).show();
                }
                    listview.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh:
                limparListaDeAlunos();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addAlunoNaLista(String nomeDoAluno){
        alunos.add(nomeDoAluno);
    }

    public ArrayAdapter getAdapterListaAlunos(){
        return new ArrayAdapter(this, android.R.layout.simple_list_item_1,alunos);
    }

    public void limparListaDeAlunos(){
         alunos.clear();
         listview.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,alunos));
         listview.setVisibility(View.GONE);
    }

}