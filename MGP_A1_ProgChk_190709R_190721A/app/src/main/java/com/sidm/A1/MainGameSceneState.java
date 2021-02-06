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
    private float blocksequencetimer = 30.0f;
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
        //todo: deal with this
        String scoreText = String.format("SCORE : %d", GameSystem.Instance.GetValueFromSave("Score"));

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(64);

        _canvas.drawText(scoreText, 10, 300, paint);
        //use entity manager to render things

    }

    @Override
    public void Update(float _dt) {

        timer += _dt;
        blocksequencetimer += _dt;
        rand_double = Math.random();

        //every 2 seconds, do this:
        
        if (blocksequencetimer >= 0.6f)
        {
            EntityBlock1.Create();
            timer = 0.0f;
//             while (blocksequencetimer != 0.f)
//            {
//                  EntityCollectible.Create();
//                  timer = 0.0f;
//            }
//             if (blocksequencetimer == 0.f)
//             {
//                timer = 0.0f;
//             }
        }

//        //note: this just randomly spawns collectables, make it less often i suppose?
//        //TODO: make funcs to spawn like blocs of GOs?
//        if( timer >= 2.0f && GameSystem.Instance.GetIsPaused() == false)
//        {
//            if(rand_double >= 0.5)
//            {
//                EntityCollectible.Create();
//                if(rand_double <= 0.75)
//                {
//                    EntityHealthPickUp.Create();
//
//                }
//                if(rand_double >= 0.9)
//                {
//                    EntityMultiplier.Create();
//                }
//            }
//            else
//            {
//
//                EntityAsteroid.Create();
//                if(rand_double < 0.25)
//                {
//                    EntityVulnerable.Create();
//                }
//
//            }
//            timer = 0.0f;
//        }

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

        if(EntitySmurf.GetHealth() <= 0.f)
        {
            //todo: deal with end of game
        }
    }
}



