package com.sidm.A1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class EntityCollectible implements EntityBase, Collidable {

    private Bitmap bmp = null; // Define image object name (bmp)
    private Sprite spritesheet = null; //used for the spritesheet.
    private int renderLayer =0;
    private float xPos, yPos, xDir, yDir, lifeTime, imgRadius;
    private boolean hasTouched = false, isInit; // Check for ontouch events
    private boolean isDone = false;
    private float rand_float;

    @Override
    public void OnHit(Collidable _other) {
        if(_other.GetType()=="SmurfEntity")
        {
            SetIsDone(true);
        }
    }

    @Override
    public void Init(SurfaceView _view) {
        //randomise the x and y pos
        rand_float = (float) Math.random();
        xPos= 1900;
        yPos = 1080* rand_float;
        spritesheet = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.elementredpolygonglossy),1,1,1);
        spritesheet.Scale(37,37);
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
    }

    @Override
    public void Render(Canvas _canvas) {
        spritesheet.Render(_canvas,(int)xPos,(int)yPos);
    }

    public static EntityCollectible Create()
    {
        EntityCollectible result = new EntityCollectible();
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_COLLECTIBLE);
        return result;
    }

    public static EntityCollectible Create(int _layer)
    {
        EntityCollectible result = Create();
        result.SetRenderLayer(_layer);
        return result;
    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public String GetType() {
        return "EntityCollectible";
    }

    @Override
    public int GetRenderLayer() {
        return renderLayer;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        renderLayer=_newLayer;
    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_COLLECTIBLE;
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
        return 0;
    }

    @Override
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone= _isDone;
    }
}
