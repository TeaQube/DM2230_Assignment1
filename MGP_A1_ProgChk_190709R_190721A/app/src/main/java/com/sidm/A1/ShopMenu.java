package com.sidm.A1;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;

import android.widget.Switch;

public class ShopMenu extends Activity implements OnClickListener, StateBase {

    private Button Exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.shop_menu);

        //define all the switches HERE
        Exit = (Button) findViewById(R.id.exitButton);
        Exit.setOnClickListener(this);

    }

    @Override
    //Invoke a callback event in the view
    public void onClick(View v)
    {
        Intent intent = new Intent();

        if (v == Exit)
        {
            // intent --> to set to another class which another page or screen that we are launching.
            intent.setClass(this, Mainmenu.class);
            StateManager.Instance.ChangeState("Default"); // Default is like a loading page
        }
        startActivity(intent);

    }

    @Override
    public String GetName() {
        return "ShopMenu";
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
