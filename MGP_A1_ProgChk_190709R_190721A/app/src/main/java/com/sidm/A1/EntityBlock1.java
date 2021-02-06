package com.sidm.A1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import java.util.Random;

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
    private float blockcountdown;
    private Random random;
    private int randomNumber, min, max;

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
        yPos = 480;
        min = 0;
        max = 2;
        blockcountdown = 0.0f;
        random = new Random();
        randomNumber = random.nextInt(max + 1 - min) + min;
        spritesheet = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.elementredpolygonglossy),1,1,1);
        spritesheet.Scale(52,52);
        imgRadius = (float) (spritesheet.GetHeight() * 0.5);
        isInit = true;
    }

    @Override
    public void Update(float _dt) {

        if(GameSystem.Instance.GetIsPaused())
            return;

        //when countdown hits 5 second interval, do this:
        if ((int) blockcountdown % 2 == 0)
        {
            //check for the type of random number using a switch
            switch(randomNumber)
            {
                case 0:
                    yPos = 600;
                    break;
                case 1:
                    yPos = 480;
                    break;
                case 2:
                    yPos = 360;
                    break;
            }
        }

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

    public static EntityBlock1 Create()
    {
        EntityBlock1 result = new EntityBlock1();
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_COLLECTIBLE);
        return result;
    }

    public static EntityBlock1 Create(int _layer)
    {
        EntityBlock1 result = Create();
        result.SetRenderLayer(_layer);
        return result;
    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public String GetType() {
        return "EntityBlock1";
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
    public float GetWidth() {
        return (float)(spritesheet.GetWidth());
    }

    @Override
    public float GetHeight() {
        return (float)(spritesheet.GetHeight());
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
