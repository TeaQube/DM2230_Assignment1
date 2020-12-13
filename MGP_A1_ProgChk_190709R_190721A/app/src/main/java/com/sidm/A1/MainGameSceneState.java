package com.sidm.A1;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

// Created by TanSiewLan2020

public class MainGameSceneState implements StateBase {
    private float timer = 0.0f;

    @Override
    public String GetName() {
        return "MainGame";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        RenderBackground.Create();
        //EntitySmurf.Create();
        //IMPT NOTE: somehow EntitySmurf's init is acting up

        //use .Create() to create things you want
        // Example to include another Renderview for Pause Button
    }

    @Override
    public void OnExit() {
        EntityManager.Instance.Clean();
        GamePage.Instance.finish();
        //clean things up
    }

    @Override
    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);
        //use entity manager to render things
    }

    @Override
    public void Update(float _dt) {

        EntityManager.Instance.Update(_dt);

        //vv deals with transitions
        if (TouchManager.Instance.IsDown()) {

            //Example of touch on screen in the main game to trigger back to Main menu
            StateManager.Instance.ChangeState("Mainmenu");
        }
    }
}



