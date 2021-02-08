package com.sidm.A1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

import java.util.Random;

// Created by TanSiewLan2020

public class MainGameSceneState implements StateBase {
    private float timer = 0.0f;
    private float gameTimer = 0.0f;
    private float blocksequencetimer = 10.0f;
    int xPos;
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
        gameTimer += _dt;
        rand_double = Math.random();
        Random random = new Random();
        Random xRandom = new Random();
        Random yRandom = new Random();
        int min = 0; int max = 1;
        int switchValue = random.nextInt(max + 1 - min) + min;
        int minimum = 1720;
        int maximum = 2800;
        int minimumY = 400;
        int maximumY = 600;
        int randomYNumber = yRandom.nextInt(maximumY + 1 - minimumY) + minimum;
        int firstoffPos = randomYNumber + 260;
        int secondoffPos = firstoffPos - 460;
        int randomxNumber = xRandom.nextInt(maximum + 1 - minimum) + minimum;

        //while timer is still below, do this:
        if (timer <= 18.0f)
        {
            //regular spawning
            if (gameTimer >= 2.0f && GameSystem.Instance.GetIsPaused() == false) {
                if (rand_double >= 0.5)
                {
                    EntityCollectible.Create();
                    if (rand_double <= 0.75) {
                        EntityHealthPickUp.Create();
                    }
                    if (rand_double >= 0.9) {
                        EntityMultiplier.Create();
                    }
                }
                else {
                    EntityAsteroid.Create();
                    if (rand_double < 0.25) {
                        EntityVulnerable.Create();
                    }
                }
                gameTimer = 0.0f;
            }
        }
        else { //when the timer hits to do a RANDOM event
            blocksequencetimer -= _dt;
            //while timer for event is going down, do this:
            if (blocksequencetimer > 0.0f)
            {
                switch(switchValue)
                {
                    case 0:
                        //reduces the spawn count && introduces a break to show clarity between EVENTS
                        if ((int)blocksequencetimer % 5 == 0) {
                            EntityBlock1 newblock = EntityBlock1.Create();
                            newblock.SetPosX(randomxNumber);

                        }
                        break;
                    case 1:
                        EntityBlock2 block = EntityBlock2.Create();
                        for (int i = 0; i < 30; i++)
                        {
                            if (i < 10)
                            {
                                block.SetPosY(randomYNumber);
                                randomYNumber += 280;
                            }
                            else if (i < 20 && i > 10)
                            {
                                block.SetPosY(firstoffPos);
                            }
                            else if (i < 30 && i > 20)
                            {
                                block.SetPosY(secondoffPos);
                            }
                        }
                        break;
                }
            }
            //when the event timer is up, do this:
            else
            {
                //resets the block sequence timer
                blocksequencetimer = 10.f;
                //reset the timer so that another block can appear
                timer = 0.0f;
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

        if(EntitySmurf.GetHealth() <= 0.f)
        {
            //todo: deal with end of game
            int currentScore = EntitySmurf.GetScore();
            GameSystem.Instance.SaveEditBegin();
            GameSystem.Instance.SetValueInSave("Score", currentScore);
            GameSystem.Instance.SaveEditEnd();
        }
    }
}



