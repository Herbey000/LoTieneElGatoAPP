package com.herbeymoreno.lotieneelgatoapp;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.view.Menu;
import android.view.MenuItem;
import android.media.AudioManager;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends ActionBarActivity {

    boolean share = false;
SoundManager sound;
    int Carisaurio,Gato,Huron,PinchiGato,Santino,Matraca,Nachas,CosaSeria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sound = new SoundManager(getApplicationContext());
        loadSounds();

        setContentView(R.layout.activity_main);

        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        Intent intent = getIntent();

        final int[] mSongs = new int[] { R.raw.huron, R.raw.carisaurio, R.raw.gato,R.raw.santino,R.raw.pinchigato,R.raw.matracasi,R.raw.nachas,R.raw.cosaseria };
        for (int i = 0; i < mSongs.length; i++) {
            try {
                String path = Environment.getExternalStorageDirectory() + "/LoTieneElGatoSounds";
                File dir = new File(path);
                if (dir.mkdirs() || dir.isDirectory()) {
                    String str_song_name = i + ".ogg";
                    CopyRAWtoSDCard(mSongs[i], path + File.separator + str_song_name);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void  playSound(View view){

        int id = view.getId();
        switch (id)
        {
            case R.id.btnHuron:
                if(share){
                    ShareSound(0);}
                else{
                sound.play(Huron);}
                break;
            case R.id.btnCarisaurio:
                if(share){
                    ShareSound(1);}
                else{
                sound.play(Carisaurio);}
                break;
            case R.id.btnGato:
                if(share){
                    ShareSound(2);
                }
                else{
                sound.play(Gato);
                }
                break;
            case R.id.btnSantino:
                if(share){
                    ShareSound(3);
                }
            else{
                sound.play(Santino);}
                break;
            case R.id.btnPinchiGato:
                if(share){
                    ShareSound(4);
                }
                else{
                sound.play(PinchiGato);
                }
                break;
            case R.id.btnMatracasi:
                if(share){
                    ShareSound(5);
                }
                else{
                    sound.play(Matraca);
                }
                break;
            case R.id.btnNachas:
                if(share){
                    ShareSound(6);
                }
                else{
                    sound.play(Nachas);
                }
                break;
            case R.id.btnCosaSeria:
                if(share){
                    ShareSound(7);
                }
                else{
                    sound.play(CosaSeria);
                }
                break;
            case R.id.btnShare:
                share = true;
                Context context = getApplicationContext();
                CharSequence text = "Preciona un sonido para enviar.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                break;
        }
    }

    private void loadSounds(){
        Gato = sound.load(R.raw.gato);
        PinchiGato = sound.load(R.raw.pinchigato);
        Santino = sound.load(R.raw.santino);
        Huron = sound.load(R.raw.huron);
        Carisaurio = sound.load(R.raw.carisaurio);
        Matraca = sound.load(R.raw.matracasi);
        Nachas = sound.load(R.raw.nachas);
        CosaSeria = sound.load(R.raw.cosaseria);
    }

    public void ShareSound(int sound){
        String path = Environment.getExternalStorageDirectory() + "/LoTieneElGatoSounds";
        File audio = new File(path+"/"+sound+".ogg");
        Intent intent = new Intent(Intent.ACTION_SEND).setType("audio/*");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(audio));
        startActivity(Intent.createChooser(intent, "LoTieneElGatoAPP"));
        share = false;
    }



    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

      private void CopyRAWtoSDCard(int id, String path) throws IOException {
          InputStream in = getResources().openRawResource(id);
          FileOutputStream out = new FileOutputStream(path);
          byte[] buff = new byte[1024];
          int read = 0;
          try {
              while ((read = in.read(buff)) > 0) {
                  out.write(buff, 0, read);
              }
          } finally {
              in.close();
              out.close();
          }
      }


}
