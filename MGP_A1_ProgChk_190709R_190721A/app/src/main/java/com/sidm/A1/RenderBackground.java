package com.sidm.A1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;


public class RenderBackground implements EntityBase {
    private Bitmap bmp = null;
    private boolean isDone = false;
    private float xPos = 0.0f;
    private float yPos = 0.0f;
    private float xPos2 = 0.0f;
    private float yPos2 = 0.0f;
    int screenWidth, screenHeight;

    public static void Create() {
        RenderBackground result = new RenderBackground();

        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_BACKGROUND);

    }

    @Override
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view) {
        bmp = BitmapFactory.decodeResource(_view.getResources(),R.drawable.gamescene);

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

        //can create a scaled bitmap if it's too big
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
        _canvas.drawBitmap(bmp,xPos, yPos, null);  // 1st image loaded which starts at 0,0

        _canvas.drawBitmap(bmp, xPos + screenWidth, yPos, null);  //xpos + screenwidth = 1028 ,.. 1028 end
        //yPos = 0

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
        return ENTITY_TYPE.ENT_BACKGROUND;
    }
}
