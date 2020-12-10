package com.sidm.A1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class EntitySmurf implements EntityBase, Collidable {
    private Bitmap bmp = null; // Define image object name (bmp)
    // To load a png image file
    // Define  x and y pos and also direction
    //vector 2 class from ACG, PPHYs, go ahead!!
    private int renderLayer;
    private float xPos, yPos, xDir, yDir, lifeTime, imgRadius;
    private boolean hasTouched = false, isDone,isInit; // Check for ontouch events

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
        //Define which image / png u want to use for this entity
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.mipmap.ic_launcher);
        // Initialize inital positions
        // Any others.
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
        // Our basic rendering with image centered
        _canvas.drawBitmap(bmp, xPos - bmp.getWidth() * 0.5f, yPos - bmp.getHeight() * 0.5f, null);
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

    public static EntitySmurf Create() {
        EntitySmurf result = new EntitySmurf();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_SMURF);
        return result;
    }

    public static EntitySmurf Create(int _layer) {
        EntitySmurf result = Create();
        result.SetRenderLayer(_layer);
        return result;
    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_SMURF;
    }

    @Override
    public String GetType() {
        return "SampleEntity";
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
        if (_other.GetType() == "NextEntity") //Another Entity
        {
            SetIsDone(true);
        }
    }
}
