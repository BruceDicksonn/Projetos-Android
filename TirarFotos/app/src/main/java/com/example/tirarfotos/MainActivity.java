package com.example.tirarfotos;

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 2a392225e2fb4e682e5b061830e07c99da394dfd
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.exifinterface.media.ExifInterface;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    File arquivoFoto;
    Bitmap bitmapImagem;
    ImageView imageView;
    final int IMAGE_REQUEST_CAPTURE = 1;


<<<<<<< HEAD
=======
=======
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

>>>>>>> a33e1fe17682a8b782c907c27d79d4d3b576c6d6
>>>>>>> 2a392225e2fb4e682e5b061830e07c99da394dfd
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 2a392225e2fb4e682e5b061830e07c99da394dfd
        initComponents();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnOpenCamera:
                openCam();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == IMAGE_REQUEST_CAPTURE){

            sendBroadcast(new Intent(
                   Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                   Uri.fromFile(this.arquivoFoto)
            ));

            exibirImagem();

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void openCam() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(intent.resolveActivity(getPackageManager()) != null) {

            try {

                this.arquivoFoto = createImageFile();

            } catch (Exception e) {
                e.printStackTrace();
            }

            if(this.arquivoFoto != null){

                Uri imageUri = FileProvider.getUriForFile(
                        getBaseContext(),
                        getBaseContext().getApplicationContext().getPackageName()+".provider",
                        arquivoFoto
                );

                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, IMAGE_REQUEST_CAPTURE);

            }
        }
    }

    public Bitmap getImageRotated(Bitmap image, int angulo){

        Matrix matrix = new Matrix();
        matrix.postRotate(angulo);

        return Bitmap.createBitmap(image,0,0,
                image.getWidth(),
                image.getHeight(),
                matrix, true);

    }

    private void exibirImagem(){

        int targetW = this.imageView.getWidth();
        int targetH = this.imageView.getHeight();

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(arquivoFoto.getAbsolutePath(),bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.max(1,Math.min(targetW/photoW,targetH/photoH));

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        Bitmap bitmap = BitmapFactory.decodeFile(arquivoFoto.getAbsolutePath(),bmOptions);
        Bitmap rotacionada = tratarOrientacaoDaImagem(bitmap);

        this.bitmapImagem = rotacionada;
        this.imageView.setImageBitmap(rotacionada);

    }

    public Bitmap tratarOrientacaoDaImagem(Bitmap bitmap){

        ExifInterface exifInterface = null;
        Bitmap rotacionada = bitmap;

        try {

            exifInterface = new ExifInterface(this.arquivoFoto.getAbsolutePath());
            String orientation = exifInterface.getAttribute(exifInterface.TAG_ORIENTATION);

            int codeOrientation = Integer.parseInt(orientation);

            switch (codeOrientation) {
                case ExifInterface.ORIENTATION_NORMAL:      // rotaciona 0 graus no sentido horário
                    rotacionada = getImageRotated(bitmap,0);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:  // rotaciona 90 graus no sentido horário
                    rotacionada = getImageRotated(bitmap,90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180: // rotaciona 180 graus no sentido horário
                    rotacionada = getImageRotated(bitmap,180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270: // rotaciona 270 graus no sentido horário
                    rotacionada = getImageRotated(bitmap,270);
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

            return rotacionada;
    }

    private File createImageFile(){
        File imageFile = null;
        String timestamp = new SimpleDateFormat("yyyyMMddd_hhmmss").format(new Date());

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        imageFile = new File(path.getPath()+ File.separator + timestamp + ".jpg");

        return imageFile;
    }

    public void initComponents(){
        this.imageView = findViewById(R.id.foto);
<<<<<<< HEAD
=======
=======
>>>>>>> a33e1fe17682a8b782c907c27d79d4d3b576c6d6
>>>>>>> 2a392225e2fb4e682e5b061830e07c99da394dfd
    }
}