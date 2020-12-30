package com.sidm.A1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.graphics.Typeface;

public class RenderTextEntity implements EntityBase {

    Paint paint = new Paint();
    private int red = 0;
    private int blue = 0;
    private int green = 0;
    private boolean isDone = false;
    int frameCount;
    long lastTime = 0;
    long lastFPSTime = 0;
    float fps;

    Typeface myfont;

    @Override
    public void Init(SurfaceView _view)
    {
        myfont = Typeface.createFromAsset(_view.getContext().getAssets(),"fonts/gemcut.otf");
    }

    @Override
    public void Update(float _dt) {
        frameCount++;
        long currentTime = System.currentTimeMillis();

        lastTime = currentTime;

        if(currentTime - lastFPSTime > 1000)
        {
            fps = (frameCount * 1000.f) / (currentTime - lastFPSTime);
            lastFPSTime = currentTime;
            frameCount = 0;
        }
    }

    @Override
    public void Render(Canvas _canvas) {
            Paint paint = new Paint(); // Use paint to render text on screen
            paint.setARGB(255, 0, 0, 0); // Alpha, R, G, B Can make it a variable
            paint.setStrokeWidth(200); // Stroke width is just the thickness of the appearance of the text
            paint.setTypeface(myfont); // using the type of font we defined
            paint.setTextSize(70);     // Text size
            _canvas.drawText("FPS: " + fps, 30, 80, paint); // To render text is drawText FPS: 60
            // drawText(String text, float x, float y, Paint paint)
            // Draw the text, with origin at (x,y), using the specified paint
    }

    @Override
    public boolean IsInit() {
        return false;
    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_TEXT;
    }

    @Override
    public int GetRenderLayer() {
        return 0;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {

    }

    @Override
    public boolean IsDone() {
        return false;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }
}
