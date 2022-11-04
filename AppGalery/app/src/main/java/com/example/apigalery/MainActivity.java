package com.example.apigalery;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.PermissionRequest;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Bitmap imagem = null;
    private ImageView foto;
    private File arquivoFoto = null;
    private final int SELECAO_GALERIA = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foto = findViewById(R.id.foto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.openGalery:
                openGalery();
            break;
            case R.id.saveInGalery:

            break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openGalery(){
        Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,SELECAO_GALERIA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == SELECAO_GALERIA){

            Bitmap imagem = null;

            try {

                switch (requestCode){
                    case SELECAO_GALERIA:

                        Uri local = data.getData();
                        imagem = MediaStore.Images.Media.getBitmap(getContentResolver(),local);

                        break;
                }

                if(imagem != null) {

                    foto.setImageBitmap(imagem);
                    this.imagem = imagem;

                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }


        super.onActivityResult(requestCode, resultCode, data);
    }

}