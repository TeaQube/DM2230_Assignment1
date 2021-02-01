package com.sidm.A1;

import android.content.SharedPreferences;
import android.view.SurfaceView;

// Created by TanSiewLan2020

public class GameSystem {
    public final static GameSystem Instance = new GameSystem();

    //for week12 - added items
    public static final String SHARED_PREF_ID = "GameSaveFile";

    // Game stuff
    private boolean isPaused = false;
    SharedPreferences sharedPref = null;
    SharedPreferences.Editor editor = null;


    // Singleton Pattern : Blocks others from creating
    private GameSystem()
    {
    }

    public void Update(float _deltaTime)
    {
    }

    public void Init(SurfaceView _view)
    {
        // Get shared preferences (Save File)
        sharedPref = GamePage.Instance.getSharedPreferences(SHARED_PREF_ID, 0);

        // We will add all of our states into the state manager here!
        StateManager.Instance.AddState(new Mainmenu());
        StateManager.Instance.AddState(new GamePage());
        StateManager.Instance.AddState(new OptionsMenu());
        StateManager.Instance.AddState(new MainGameSceneState());
    }

    //stuff for pausing the game vv
    public void SetIsPaused(boolean _newIsPaused)
    {
        isPaused = _newIsPaused;
    }

    public boolean GetIsPaused()
    {
        return isPaused;
    }

    public void SaveEditBegin()
    {
        //Safety check, only allows if not editing
        if (editor != null)
        {
            return;
        }

        //start the editing
        editor = sharedPref.edit();
    }

    public void SaveEditEnd()
    {
        //check if editor exists
         if (editor == null)
         {
            return;
         }

        editor.commit();
        //safety to ensure other functions will FAIL once commit is complete
        editor = null;
    }

    public void SetValueInSave(String _key, int _value)
    {
         if (editor == null)
         {
            return;
         }
        editor.putInt(_key, _value);
    }

    public int GetValueFromSave(String _key)
    {
        return sharedPref.getInt(_key, 10);
    }

}
