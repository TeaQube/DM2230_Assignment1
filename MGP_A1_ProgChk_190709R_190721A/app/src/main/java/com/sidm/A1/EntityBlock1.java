package com.sidm.A1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

//TODO: Make other forms of collectibles in the form of powerup, health, buffs and debuffs
//by ernst
public class EntityBlock1 implements EntityBase, Collidable {

    private Bitmap bmp = null; // Define image object name (bmp)
    private Sprite spritesheet = null; //used for the spritesheet.
    private int renderLayer =0;
    private float xPos, yPos, xDir, yDir, lifeTime, imgRadius;
    private boolean hasTouched = false, isInit; // Check for ontouch events
    private boolean isDone = false;
    private float rand_float;
    private float blockspawning = 0.25f;

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
        while (blockspawning <= 2.f && blockspawning > 0.0f)
        {
            yPos = 1080* blockspawning;
            xPos= 1900* blockspawning;
            blockspawning += 0.25f;
        }

        if (blockspawning == 2.f)
        {
            blockspawning = 0.0f;
        }

        spritesheet = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.elementredpolygonglossy),1,1,1);
        spritesheet.Scale(37,37);
        imgRadius = (float) (spritesheet.GetHeight() * 0.5);
        isInit = true;
    }

    @Override
    public void Update(float _dt) {

        if(GameSystem.Instance.GetIsPaused())
            return;

        xPos -= _dt * 50;

        if (TouchManager.Instance.HasTouch()) {
            // 0.0f, xPos, yPos, imgRadius ---> Checking collision of finger w the image

            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius) || hasTouched)
            {
                // Collided!

                hasTouched = true;
                xPos = TouchManager.Instance.GetPosX();
                yPos = TouchManager.Instance.GetPosY();

                //to update the score when collision is true
                int currScore = GameSystem.Instance.GetValueFromSave("Score");
                ++currScore;
                GameSystem.Instance.SaveEditBegin();
                GameSystem.Instance.SetValueInSave("Score", currScore);
                GameSystem.Instance.SaveEditEnd();

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

