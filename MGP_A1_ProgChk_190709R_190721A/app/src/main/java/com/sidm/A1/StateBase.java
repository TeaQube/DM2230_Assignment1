package com.sidm.A1;

// Created by TanSiewLan2020
// Create an interface "StateBase". That is what a state will need.

import android.graphics.Canvas;
import android.view.SurfaceView;

public interface StateBase {
    String GetName();//defines the name for your state
    void OnEnter(SurfaceView _view);
    void OnExit();
    void Render(Canvas _canvas);
    void Update(float _dt);
}