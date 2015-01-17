package com.example.izv.reproductordevideo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;


public class Principal extends Activity {

    private Button b;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
        b=(Button)findViewById(R.id.ultimo);
        b.setEnabled(false);
        SharedPreferences pc;
        pc = PreferenceManager.getDefaultSharedPreferences(
                getApplicationContext());
        String ur = pc.getString("uri", "nada");
        Log.v("AAAAAAAAAAAAAAA",ur);
        if (ur.compareTo("nada")!=0){
            uri= Uri.parse(ur);
            b.setEnabled(true);
        }
    }


    public void seleccionar(View view){
        Intent i = new Intent (Intent.ACTION_PICK, MediaStore.Video.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i, 1);
    }

    public void ultimo(View view){
        Intent i=new Intent(this, Reproductor.class);
        i.putExtra("uri",uri);
        startActivity(i);
        this.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==RESULT_OK) {
            Uri selectedVideo = data.getData();
            Intent i=new Intent(this, Reproductor.class);
            i.putExtra("uri",selectedVideo);
            startActivity(i);
            this.finish();
        }
    }

}
