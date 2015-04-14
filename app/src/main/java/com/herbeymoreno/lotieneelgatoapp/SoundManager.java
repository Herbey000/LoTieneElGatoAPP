package com.herbeymoreno.lotieneelgatoapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.ContextMenu;
/**
 * Created by HERBEY on 22/01/2015.
 */
public class SoundManager {
    private Context pContext;
    private SoundPool sndPool;
    private float rate = 1.0f;
    private float leftVolume = 1.0f;
    private float rightVolume = 1.0f;

    SoundManager sound;
    int Carisaurio,Gato,Huron,PinchiGato,Santino,Matraca;

    public SoundManager(Context appContext){
        sndPool = new SoundPool(1, AudioManager.STREAM_MUSIC,100);
        pContext=appContext;
    }

    public int load(int idSound){
        return sndPool.load(pContext,idSound,2);
    }

    public void play(int idSonido)
    {
        sndPool.play(idSonido, leftVolume, rightVolume, 1, 0, rate);
    }

    public boolean Empty(){
        if(sndPool!=null){
        return true;}
        else {
            return false;
        }
    }
}


