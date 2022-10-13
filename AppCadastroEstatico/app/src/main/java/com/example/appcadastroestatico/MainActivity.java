package com.example.appcadastroestatico;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcadastroestatico.DAO.DaoDados;
import com.example.appcadastroestatico.MODEL.Dados;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {


    private TextView nome,email,senha;
    private CircleImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Home");/

        Bundle extras = getIntent().getExtras();

        initComponents();
        inputFields();

    }

    private void inputFields(){

        CircleImageView d = DaoDados.get().getFoto();

        foto.setImageDrawable(DaoDados.get().getFoto().getDrawable());
        nome.setText(DaoDados.get().getNome());
        email.setText(DaoDados.get().getEmail());
        senha.setText(DaoDados.get().getSenha());

    }

    private void inputFields(String txtNome, String txtEmail, String txtSenha){
        nome.setText(txtNome);
        email.setText(txtEmail);
        senha.setText(txtSenha);
    }

    private void initComponents(){
        nome  = findViewById(R.id.txtNome);
        email = findViewById(R.id.txtEmail);
        senha = findViewById(R.id.txtSenha);
        foto = findViewById(R.id.fotoPerfil);
    }

}