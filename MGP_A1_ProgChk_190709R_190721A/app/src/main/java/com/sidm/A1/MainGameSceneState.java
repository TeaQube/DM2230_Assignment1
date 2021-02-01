package com.sidm.A1;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;
import android.widget.Button;

import java.util.concurrent.ThreadLocalRandom;

// Created by TanSiewLan2020

public class MainGameSceneState implements StateBase {
    private float timer = 0.0f;
    private float blocksequencetimer = 0.0f;
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
        //EntityAsteroid.Create();
        //EntityCollectible.Create();
        //EntitySwitch.Create();
        PauseButton.Create();
        player = EntitySmurf.Create();
        upbutton = EntityUp.Create();
        downbutton = EntityDown.Create();
        RenderTextEntity.Create();
        RenderScoreText.Create();
        RenderHealthText.Create();
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
        blocksequencetimer += _dt;
        rand_double = Math.random();

        //note: this just randomly spawns collectables, make it less often i suppose?
        //TODO: make funcs to spawn like blocs of GOs?

       if (blocksequencetimer <= 50.f)  //check if timer for a block sequence is up
       {
           if (timer >= 2.0f && GameSystem.Instance.GetIsPaused() == false)
           {
               if (rand_double >= 0.5) {
                   EntityCollectible.Create();
                   if (rand_double <= 0.75) {
                       EntityHealthPickUp.Create();
                   }
               }
               else
                   {
                    EntityAsteroid.Create();
                   }
               timer = 0.0f;
           }
       }

        if (blocksequencetimer >= 10.f)
        {
            int min = 0;
            int max = 0;
            int choice = ThreadLocalRandom.current().nextInt(min, max + 1);
            switch(choice)
            {
                case 0:
                    EntityBlock1.Create();
                    blocksequencetimer = 0.0f;
                    break;
                case 1:
                    //EntityBlock1.Create();
                    //blocksequencetimer = 0.0f;
                    break;
            }

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
        if (GameSystem.Instance.GetIsPaused())
        {
            if (TouchManager.Instance.IsDown())
            {
                //Example of touch on screen in the main game to trigger back to Main menu
            }
            return;
        }
    }
}








