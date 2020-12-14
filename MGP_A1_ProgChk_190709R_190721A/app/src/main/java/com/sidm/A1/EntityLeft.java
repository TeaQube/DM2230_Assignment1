package com.sidm.A1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.text.method.Touch;
import android.view.SurfaceView;

public class EntityLeft implements Collidable, EntityBase {
    private boolean IsClicked = false;
    private int renderLayer = 0;
    private float xPos, yPos, xDir, yDir, imgRadius;
    private Sprite spritesheet = null;

    @Override
    public boolean IsDone() {
        return false;
    }

    @Override
    public void SetIsDone(boolean _isDone) {

    }

    public void Init(SurfaceView _view) {
        //super.Init(_view);
        spritesheet = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.leftbutton),1,1,60);
        xPos = 100;
        yPos = 1000;
        imgRadius = spritesheet.GetHeight() / 2;
    }

    @Override
    public void Update(float _dt)
    {
        if (TouchManager.Instance.HasTouch() && TouchManager.Instance.IsDown())
        {
            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius))
            {
                IsClicked = true;
            }
        }
    }

    public void Render(Canvas _canvas) {
        spritesheet.Render(_canvas, (int) xPos, (int) yPos);
    }

    @Override
    public boolean IsInit() {
        return false;
    }

    @Override
    public int GetRenderLayer() {
        return renderLayer;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        renderLayer = _newLayer;
    }

    public static EntityLeft Create() {
        EntityLeft result = new EntityLeft();
        EntityManager.Instance.AddEntity(result, EntityBase.ENTITY_TYPE.ENT_LEFT);
        return result;
    }

    public static EntityLeft Create(int _layer) {
        EntityLeft result = Create();
        result.SetRenderLayer(_layer);
        return result;
    }

    @Override
    public EntityBase.ENTITY_TYPE GetEntityType() {
        return EntityBase.ENTITY_TYPE.ENT_LEFT;
    }

    public void OnHit()
    {
        IsClicked = true;
    }

    public void Reset()
    {
        this.IsClicked = false;
    }

    public boolean getClicked()
    {
        return this.IsClicked;
    }


    @Override
    public String GetType() {
        return "LeftButton";
    }

    @Override
    public float GetPosX() {
        return xPos;
    }

    @Override
    public float GetPosY() {
        return yPos;
    }

    @Override
    public float GetRadius() {
        return imgRadius;
    }

    @Override
    public void OnHit(Collidable _other) {

    }

}
