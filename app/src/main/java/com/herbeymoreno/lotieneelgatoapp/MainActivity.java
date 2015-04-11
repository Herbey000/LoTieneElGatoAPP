package com.herbeymoreno.lotieneelgatoapp;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.Menu;
import android.view.MenuItem;
import android.media.AudioManager;
import android.content.Intent;
import android.appwidget.AppWidgetManager;
import android.widget.RelativeLayout;



public class MainActivity extends ActionBarActivity {

SoundManager sound;
    int Carisaurio,Gato,Huron,PinchiGato,Santino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sound = new SoundManager(getApplicationContext());
        loadSounds();
        if(sound.Empty()){
        loadSounds();
        }

        setContentView(R.layout.activity_main);



        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);


        Intent intent = getIntent();
      // Bundle extras = intent.getExtras();
        if(intent!=null)
        {
            playSoundS();
        }

      /* if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
           }*/

    }
    public void  playSoundS(){
        sound.play(Huron);
        }

    public void  playSound(View view){

        int id = view.getId();
        switch (id)
        {
            case R.id.btnGato:
                sound.play(Gato);
                break;
            case R.id.btnCarisaurio:
                sound.play(Carisaurio);
                break;
            case R.id.btnSantino:
                sound.play(Santino);
                break;
            case R.id.btnPinchiGato:
                sound.play(PinchiGato);
                break;
            case R.id.btnHuron:
                sound.play(Huron);
                break;
        }
    }

    private void loadSounds(){
        Gato = sound.load(R.raw.gato);
        PinchiGato = sound.load(R.raw.pinchigato);
        Santino = sound.load(R.raw.santino);
        Huron = sound.load(R.raw.huron);
        Carisaurio = sound.load(R.raw.carisaurio);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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
}
