package com.sidm.A1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class EntitySwitch implements EntityBase, Collidable {

   boolean hasInteracted;
    private Bitmap bmp = null; // Define image object name (bmp)
    private Sprite spritesheet = null; //used for the spritesheet.
    private int renderLayer =0;
    private float xPos, yPos, xDir, yDir, lifeTime, imgRadius;
    private boolean hasTouched = false, isInit; // Check for ontouch events
    private boolean isDone = false;
    private Sprite switchon = null;
    private Sprite switchoff = null;

    @Override
    public void Init(SurfaceView _view) {

        spritesheet = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.switchsprite),1,2,60);
        imgRadius = (float) (spritesheet.GetHeight() * 0.5);
        isInit = true;
    }

    @Override
    public void Update(float _dt) {

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

    }

    @Override
    public void OnHit(Collidable _other) {

    }

    public static EntitySwitch Create()
    {
        EntitySwitch result = new EntitySwitch();
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_SWITCH);
        return result;
    }

    public static EntitySwitch Create(int _layer)
    {
        EntitySwitch result = Create();
        result.SetRenderLayer(_layer);
        return result;
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
        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return renderLayer;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        renderLayer = _newLayer;
    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_SWITCH;
    }

    @Override
    public String GetType() {
        return "EntitySwitch";
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
}
