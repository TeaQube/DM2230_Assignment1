package com.sidm.A1;

// Created by TanSiewLan2020
// Create a GamePage is an activity class used to hold the GameView which will have a surfaceview

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.view.View.OnClickListener;

public class GamePage extends Activity implements OnClickListener, StateBase {

    public static GamePage Instance = null;

    //defining buttons
    private ImageButton moveLeft;
    private ImageButton moveRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //To make fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Hide titlebar

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);  // Hide topbar

        Instance = this;

        setContentView(new GameView(this)); // Surfaceview = GameView

        moveLeft=(ImageButton)findViewById(R.id.moveLeft);
        moveLeft.setOnClickListener(this);

        moveRight=(ImageButton)findViewById(R.id.moveRight);
        moveRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        // Intent = action to be performed.
        // Intent is an object provides runtime binding.
        // new instance of this object intent

        Intent intent = new Intent();

        if(v==moveLeft)
        {
            intent.setClass(this, GamePage.class);

        }
        if(v==moveRight)
        {
            intent.setClass(this, GamePage.class);

        }
        startActivity(intent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        // WE are hijacking the touch event into our own system
        int x = (int) event.getX();
        int y = (int) event.getY();

        TouchManager.Instance.Update(x, y, event.getAction());

        return true;
    }


    @Override
    public String GetName() {
        return "GamePage";
    }

    @Override
    public void OnEnter(SurfaceView _view) {

    }

    @Override
    public void OnExit() {

    }

    @Override
    public void Render(Canvas _canvas) {

    }

    @Override
    public void Update(float _dt) {

    }
}

