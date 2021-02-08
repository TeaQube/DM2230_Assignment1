package com.sidm.A1;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.Image;
import android.media.MediaPlayer;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;
import android.widget.ImageButton;


// Created by TanSiewLan2020

public class EndGameMenu extends Activity implements OnClickListener, StateBase {  //Using StateBase class

    //Define buttons
    private Button btn_return;

    MediaPlayer BGM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.endgamescreen);

        btn_return = (Button) findViewById(R.id.btn_return);
        btn_return.setOnClickListener(this); //Set Listener to this button --> Options Button

    }

    @Override
    //Invoke a callback event in the view
    public void onClick(View v)
    {
        // Intent = action to be performed.
        // Intent is an object provides runtime binding.
        // new instance of this object intent

        Intent intent = new Intent();

        if (v == btn_return)
        {
            // intent --> to set to another class which another page or screen that we are launching.
            intent.setClass(this, GamePage.class);
            StateManager.Instance.ChangeState("Mainmenu");
        }


        startActivity(intent);

    }

    @Override
    public void Render(Canvas _canvas) {
    }

    @Override
    public void OnEnter(SurfaceView _view) {
    }

    @Override
    public void OnExit()
    {
        BGM.stop();
    }

    @Override
    public void Update(float _dt) {
    }

    @Override
    public String GetName() {
        return "EndGame";
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        BGM.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
