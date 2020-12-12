package com.sidm.A1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;


public class RenderBackground implements EntityBase {
    private Bitmap bmp = null;
    private boolean isDone = false;
    private float xPos, yPos, xPos2, yPos2 = 0.0f;
    int screenWidth, screenHeight;

    public static void Create() {
    }

    @Override
    public boolean IsDone() {
        return false;
    }

    @Override
    public void SetIsDone(boolean _isDone) {

    }

    @Override
    public void Init(SurfaceView _view) {


        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
    }

    @Override
    public void Update(float _dt) {
        xPos -= _dt * 500;  // How fast it scrolls moving from right to left.

        if (xPos < - screenWidth){  // xpos
            xPos = 0;
        }
    }

    @Override
    public void Render(Canvas _canvas) {

    }

    @Override
    public boolean IsInit() {
        return false;
    }

    @Override
    public int GetRenderLayer() {
        return 0;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {

    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return null;
    }
}
