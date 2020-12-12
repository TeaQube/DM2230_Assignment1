package com.sidm.A1;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.view.SurfaceView;

import java.util.HashMap;

class AudioManager{

    private SurfaceView view = null;
    private Resources res = null;
    private MediaPlayer newAudio = null;

    private HashMap<Integer, MediaPlayer> audioMap = new HashMap<Integer, MediaPlayer>();

    public final static AudioManager Instance = new AudioManager();

    private AudioManager()
    {

    }

    public void Init(SurfaceView _view)
    {
        view = _view;
        res = _view.getResources();
    }

    public void PlayAudio(int _id, float _vol)
    {
        if (audioMap.containsKey(_id))   //Hashmap using a key -- _id
        {
            audioMap.get(_id).reset();
            audioMap.get(_id).start();
        }

        // Load the audio
        newAudio = MediaPlayer.create(view.getContext(), _id);
        audioMap.put(_id, newAudio);
        newAudio.setVolume(_vol, _vol); //volume value range from 0.0 to 1.0
        newAudio.start();
    }

    public boolean IsPlaying(int _id)
    {
        if (!audioMap.containsKey(_id))
            return false;

        return audioMap.get(_id).isPlaying();
    }

    public void StopAudio(int _id)
    {
        audioMap.get(_id);
        newAudio.stop();
    }
}
