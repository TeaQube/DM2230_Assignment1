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

public class Mainmenu extends Activity implements OnClickListener, StateBase {  //Using StateBase class

    //Define buttons
    private ImageButton btn_play;
    private ImageButton btn_options;
    MediaPlayer BGM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main_menu);

        btn_play = (ImageButton)findViewById(R.id.btn_play);
        btn_play.setOnClickListener(this); //Set Listener to this button --> Start Button

        btn_options = (ImageButton)findViewById(R.id.btn_options);
        btn_options.setOnClickListener(this); //Set Listener to this button --> Options Button

        BGM = MediaPlayer.create(getApplicationContext(), R.raw.backgroundmusic);
        BGM.start();

    }

    @Override
    //Invoke a callback event in the view
    public void onClick(View v)
    {
        // Intent = action to be performed.
        // Intent is an object provides runtime binding.
        // new instance of this object intent

        Intent intent = new Intent();

        if (v == btn_play)
        {
            // intent --> to set to another class which another page or screen that we are launching.
            intent.setClass(this, GamePage.class);
            StateManager.Instance.ChangeState("MainGame");
        }

         if (v == btn_options)
        {
            intent.setClass(this, OptionsMenu.class);
            StateManager.Instance.ChangeState("OptionsMenu"); // Default is like a loading page
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
    }
	
    @Override
    public void Update(float _dt) {
    }
	
    @Override
    public String GetName() {
        return "Mainmenu";
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
