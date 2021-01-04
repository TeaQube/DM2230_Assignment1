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
    EntityUp upbutton;
    EntityDown downbutton;
    private double rand_double;


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
        PauseButton.Create();
        player = EntitySmurf.Create();
        upbutton = EntityUp.Create();
        downbutton = EntityDown.Create();
        //RenderTextEntity.Create();
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

        String scoreText = String.format("SCORE : %d", GameSystem.Instance.GetValueFromSave("Score"));

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(64);

        _canvas.drawText(scoreText, 10, 220, paint);
        //use entity manager to render things

    }

    @Override
    public void Update(float _dt) {
        timer += _dt;
        rand_double = Math.random();

        if(rand_double >= 0.3 && timer >= 1.0f)
        {
            EntityCollectible.Create();
            timer = 0.0f;
        }

        EntityManager.Instance.Update(_dt);

        //for button click to move the character
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



