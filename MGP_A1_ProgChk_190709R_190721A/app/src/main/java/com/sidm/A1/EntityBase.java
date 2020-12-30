package com.sidm.A1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

// Created by TanSiewLan2020

public interface EntityBase
{
 	 //used for entities such as background
    enum ENTITY_TYPE{
        ENT_PLAYER,
        ENT_SMURF,
        ENT_WALL,
         ENT_LEFT,
         ENT_RIGHT,
         ENT_UP,
         ENT_DOWN,
        ENT_BACKGROUND,
        ENT_SWITCH,
         ENT_COLLECTIBLE,
         ENT_PAUSE,
        ENT_DEFAULT,
    }

    boolean IsDone();
    void SetIsDone(boolean _isDone);

    void Init(SurfaceView _view);
    void Update(float _dt);
    void Render(Canvas _canvas);

    boolean IsInit();

    int GetRenderLayer();
    void SetRenderLayer(int _newLayer);

	 ENTITY_TYPE GetEntityType();
}




