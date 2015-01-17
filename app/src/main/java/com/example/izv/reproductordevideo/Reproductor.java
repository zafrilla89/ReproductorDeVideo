package com.example.izv.reproductordevideo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;


public class Reproductor extends Activity {

    private VideoView reproductor;
    private Uri u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_reproductor);
        reproductor=(VideoView)findViewById(R.id.video);
        Bundle b = getIntent().getExtras();
        if(b !=null ) {
            u=b.getParcelable("uri");
            reproductor.setVideoURI(u);
        }
        SharedPreferences pc;
        pc = PreferenceManager.getDefaultSharedPreferences(
                getApplicationContext());
        SharedPreferences.Editor ed = pc.edit();
        ed.putString("uri", u.toString());
        Log.v("aaaaaaaaaaaaaaaaa",u.toString());
        ed.commit();

    }

    public void reproducir(View view){
        reproductor.start();
    }

    public void pause(View view){
        reproductor.pause();
    }

    public void principio (View view){
        reproductor.resume();
    }

    public void volver (View view){
        Intent i=new Intent(this, Principal.class);
        startActivity(i);
        this.finish();
    }
}
