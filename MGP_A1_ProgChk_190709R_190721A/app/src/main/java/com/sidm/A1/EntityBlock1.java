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
    private int randomxValue, minimum, maximum;

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
        max = 25;
        blockcountdown = 0.0f;
        random = new Random();
        randomNumber = random.nextInt(max + 1 - min) + min;

        spritesheet = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.meteor),1,1,1);
        spritesheet.Scale(52,52);
        imgRadius = (float) (spritesheet.GetHeight() * 0.5);
        isInit = true;
    }

    @Override
    public void Update(float _dt) {
        if(GameSystem.Instance.GetIsPaused())
            return;

            switch(randomNumber)
            {
                case 0: yPos = 80; break; case 1: yPos = 112;break;
                case 2: yPos = 174; break; case 3: yPos = 220; break;
                case 4: yPos = 276; break; case 5: yPos = 297; break;
                case 6: yPos = 320; break; case 7: yPos = 375; break;
                case 8: yPos = 418; break; case 9: yPos = 487; break;
                case 10: yPos = 537; break; case 11: yPos = 583; break;
                case 12: yPos = 623; break; case 13: yPos = 673; break;
                case 14: yPos = 728; break; case 15: yPos = 776; break;
                case 16: yPos = 812; break; case 17: yPos = 846; break;
                case 18: yPos = 892; break; case 19: yPos = 923; break;
                case 20: yPos = 971; break; case 21: yPos = 1032; break;
                case 22: yPos = 1137; break; case 23: yPos = 1263; break;
                case 24: yPos = 1302; break; case 25: yPos = 1387; break;
            }
//        }


        xPos -= _dt * 50;

        if (TouchManager.Instance.HasTouch()) {
            // 0.0f, xPos, yPos, imgRadius ---> Checking collision of finger w the image

            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius) || hasTouched)
            {
                // Collided!

                hasTouched = true;

                //to update the score when collision is true


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
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_ASTEROID);
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
        return ENTITY_TYPE.ENT_ASTEROID;
    }

    @Override
    public float GetPosX() {
        return xPos;
    }

    public void SetPosX(int positionX) {xPos = positionX;}

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
