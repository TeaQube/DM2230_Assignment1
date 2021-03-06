package com.sidm.A1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceView;

public class Sprite
{
    //row, col, width and height for the sprite sheet
    private int row =0;
    private int col =0;
    private int width =0;
    private int height =0;

    private Bitmap bmp = null; //bitmap for the image

    //current frame,start and end frame
    private int currentFrame =0;
    private int startFrame =0;
    private int endFrame =0;

    //time per frame, time acc += dt
    private float timePerFrame = 0.0f;
    private float timeAcc = 0.0f;

    //ctor
    public Sprite(Bitmap _bmp,int _row, int _col, int _fps)
    {
        bmp = _bmp;
        row = _row;
        col = _col;

        width = bmp.getWidth() / _col;
        height = bmp.getHeight() / _row;

        timePerFrame = 1.0f / (float)_fps;

        endFrame = _col * _row;
    }

    // Update running vs delta time (Same as your ACG)
    public void Update(float _dt)
    {
        timeAcc += _dt;
        if (timeAcc > timePerFrame)
        {
            ++currentFrame;
            if (currentFrame >= endFrame)
                currentFrame = startFrame;
            timeAcc = 0.0f;
        }
    }

    // Render --> Canvas and x & y (position to be drawn out via the imaginary rectangle
    public void Render(Canvas _canvas, int _x, int _y)
    {
        int frameX = currentFrame % col;
        int frameY = currentFrame / col;
        int srcX = frameX * width;
        int srcY = frameY * height;

        _x -= 0.5f * width;
        _y -= 0.5f * height;
        // RECT
        //Rect(int left, int top, int right, int bottom)
        //Create a new rectangle with the specified coordinates.
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(_x, _y, _x + width, _y + height);
        _canvas.drawBitmap(bmp, src, dst, null);
    }

    public void SetAnimationFrames(int _start, int _end)
    {
        timeAcc = 0.0f;
        currentFrame = _start;
        startFrame = _start;
        endFrame = _end;
    }

    public int GetHeight()
    {
        return height;
    }

    public int GetWidth()
    {
        return width;
    }

    public void Scale(int widthpx, int heightpx)
    {
        bmp = Bitmap.createScaledBitmap(bmp , widthpx,heightpx,false);
        width = bmp.getWidth() / col;
        height = bmp.getHeight() / row;
    }
}
