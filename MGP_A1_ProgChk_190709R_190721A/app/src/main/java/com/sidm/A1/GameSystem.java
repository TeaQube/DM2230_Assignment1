package com.sidm.A1;

import android.view.SurfaceView;

// Created by TanSiewLan2020

public class GameSystem {
    public final static GameSystem Instance = new GameSystem();

    // Game stuff
    private boolean isPaused = false;

    // Singleton Pattern : Blocks others from creating
    private GameSystem()
    {
    }

    public void Update(float _deltaTime)
    {
    }

    public void Init(SurfaceView _view)
    {
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

}
