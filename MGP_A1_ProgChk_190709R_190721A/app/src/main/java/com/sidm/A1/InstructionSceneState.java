package com.sidm.A1;

// Created by TanSiewLan2020
// Create an interface "StateBase". That is what a state will need.

import android.graphics.Canvas;
import android.view.SurfaceView;

public class InstructionSceneState implements StateBase {
private float timer = 0.f;
    @Override
    public String GetName() {
        return "InstructionP";
    } //Name of this state is called "InstructionP"

    public void OnEnter(SurfaceView _view){
        //Load your 2 or more entities that you created
        RenderBackground.Create(); // Last week's background (Gamescene.png)
        EntitySmurf.Create(); // This week's Smurf

    }
    public void OnExit(){
        EntityManager.Instance.Clean();
        GamePage.Instance.finish();
    }

    public void Render(Canvas _canvas){
        EntityManager.Instance.Render(_canvas);
    }
    public void Update(float _dt){

        // Other example: Set random position for image to appear randomly on the screen

        // example: Set time for image smurf to appear on the screen
        timer += _dt;
        if (timer > 1.0f)
        {
            EntitySmurf.Create();  //Example: Smurf Sprite
            timer = 0.0f;
        }

        EntityManager.Instance.Update(_dt);

        // If you want to transit to another scene from here, Do this below
        if (TouchManager.Instance.IsDown()) {
            //Example of touch on screen in the main game to trigger back to Main menu
            StateManager.Instance.ChangeState("MainMenuState");
        }
    }

}
