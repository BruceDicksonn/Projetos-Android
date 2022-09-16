package com.example.ap2.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ap2.R;
import com.example.ap2.View.ListaAlunos;
import com.example.ap2.View.ListaProdutos;

public class Principal extends AppCompatActivity {

    CardView aluno, produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        initComponents();

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Principal");
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        String nomeUsuario = (String) bundle.get("nome");

        alert("Bem-vindo, " + nomeUsuario);

        aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 abrirAreaAlunos();
            }
        });

        produto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirAreaProdutos();
            }
        });

    }

    private void alert(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_sair:
                logout();
            break;
            case R.id.menu_cadastrarAluno:
                abrirFormularioAluno();
            break;
            case R.id.menu_cadastrarProduto:
                abrirFormularioProduto();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout(){
        startActivity(new Intent(this,FormularioLogin.class));
        finish();
    }

    private void abrirFormularioAluno(){
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    private void abrirFormularioProduto(){
        startActivity(new Intent(this, FormularioProduto.class));
    }

    private void abrirAreaAlunos(){
        startActivity(new Intent(this, ListaAlunos.class));
    }

    private void abrirAreaProdutos(){
        startActivity(new Intent(this, ListaProdutos.class));
    }

    private void initComponents(){
        aluno = findViewById(R.id.cardAreaAluno);
        produto = findViewById(R.id.cardAreaProduto);
    }
}