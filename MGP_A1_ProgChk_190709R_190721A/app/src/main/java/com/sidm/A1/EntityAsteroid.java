package com.sidm.A1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class EntityAsteroid implements EntityBase, Collidable{

    private Bitmap bmp = null; // Define image object name (bmp)
    private Sprite spritesheet = null; //used for the spritesheet.
    private int renderLayer =0;
    private float xPos, yPos, xDir, yDir, lifeTime, imgRadius;
    private boolean hasTouched = false, isInit; // Check for ontouch events
    private boolean isDone = false;
    private float rand_float;

    @Override
    public void Init(SurfaceView _view) {

        rand_float = (float) Math.random();
        xPos= 1900;
        yPos = 1080* rand_float;
        spritesheet = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.meteor),1,1,1);
        spritesheet.Scale(40,40);
        imgRadius = (float) (spritesheet.GetHeight() * 0.5);
        isInit = true;
    }

    @Override
    public void Update(float _dt) {

        xPos -= _dt * 50;
        if (TouchManager.Instance.HasTouch()) {
            // 0.0f, xPos, yPos, imgRadius ---> Checking collision of finger w the image

            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius) || hasTouched) {
                // Collided!

                hasTouched = true;
                xPos = TouchManager.Instance.GetPosX();
                yPos = TouchManager.Instance.GetPosY();
            }

        }
        if(xPos <= 0.0f) {
            isDone = true;
        }

    }

    @Override
    public void Render(Canvas _canvas) {
        spritesheet.Render(_canvas,(int)xPos,(int)yPos);
    }

    @Override
    public void OnHit(Collidable _other) {
        if(_other.GetType()=="SmurfEntity")
        {
            SetIsDone(true);
        }
    }

    public static EntityAsteroid Create()
    {
        EntityAsteroid result = new EntityAsteroid();
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_ASTEROID);
        return result;
    }

    public static EntityAsteroid Create(int _layer)
    {
        EntityAsteroid result = Create();
        result.SetRenderLayer(_layer);
        return result;
    }

    @Override
    public String GetType() {
        return "EntityAsteroid";
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
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
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
        return ENTITY_TYPE.ENT_ASTEROID;
    }
}
