package com.sidm.A1;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;
import android.widget.Button;

// Created by TanSiewLan2020

public class MainGameSceneState implements StateBase {
    private float timer = 0.0f;
    EntitySmurf player;
    EntityLeft leftbutton;
    EntityRight rightbutton;
    EntityUp upbutton;
    EntityDown downbutton;


    @Override
    public String GetName() {
        return "MainGame";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        RenderBackground.Create();
        EntityCollectible.Create();
        EntitySwitch.Create();
        player = EntitySmurf.Create();
        leftbutton = EntityLeft.Create();
        rightbutton = EntityRight.Create();
        upbutton = EntityUp.Create();
        downbutton = EntityDown.Create();
        //use .Create() to create things you want
        // Example to include another Renderview for Pause Button
    }

    @Override
    public void OnExit() {
        EntityManager.Instance.Clean();
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

        //for button click to move the character
        if (leftbutton.getClicked() == true)
        {
            player.SetIsClicked(EntitySmurf.BUTTONPRESSTYPE.BUTTONLEFT);
        }
        if (rightbutton.getClicked() == true)
        {
            player.SetIsClicked(EntitySmurf.BUTTONPRESSTYPE.BUTTONRIGHT);
        }
        if (upbutton.getClicked() == true)
        {
            player.SetIsClicked(EntitySmurf.BUTTONPRESSTYPE.BUTTONUP);
        }
        if (downbutton.getClicked() == true)
        {
            player.SetIsClicked(EntitySmurf.BUTTONPRESSTYPE.BUTTONDOWN);
        }

        //vv deals with transitions
        if (TouchManager.Instance.IsDown()) {
            //Example of touch on screen in the main game to trigger back to Main menu

        }


    }
}



