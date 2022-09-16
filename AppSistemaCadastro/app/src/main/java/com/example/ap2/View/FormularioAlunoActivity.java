package com.example.ap2.View;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ap2.Model.Aluno;
import com.example.ap2.Dao.AlunoDao;
import com.example.ap2.R;

public class FormularioAlunoActivity extends AppCompatActivity {

    private EditText editNome, editTelefone, editEmail;
    private Button btnSalvar;
    private final AlunoDao alunoDao = new AlunoDao();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_aluno);
        initComponents();

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Novo Aluno");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(verificarInputs()){

                    String nome = editNome.getText().toString();
                    String email = editEmail.getText().toString();
                    String fone = editTelefone.getText().toString();

                    Aluno alunoCriado = new Aluno(nome,fone,email);
                    alunoDao.salvar(alunoCriado);

                    alert("Aluno salvo com sucesso!");
                    finish();

                }

            }
        });

    }

    private boolean verificarInputs(){

        String n = editNome.getText().toString();
        String e = editEmail.getText().toString();
        String t = editTelefone.getText().toString();

        if(n.isEmpty() && e.isEmpty() && t.isEmpty()){
            alert("Preencha os campos vazios!");
            return false;
        } else if(n.isEmpty()){
            alert("Preencha o campo de nome");
            return false;
        } else if(e.isEmpty()){
            alert("Preencha o campo de email");
            return false;
        } else if(t.isEmpty()){
            alert("Preencha o campo de telefone");
            return false;
        }


        return true;
    }

    private void alert(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initComponents(){
        editNome = findViewById(R.id.edit_nome);
        editTelefone = findViewById(R.id.edit_telefone);
        editEmail = findViewById(R.id.edit_email);
        btnSalvar = findViewById(R.id.btn_salvar);
    }
}