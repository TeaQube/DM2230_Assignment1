package com.sidm.A1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class EntitySmurf implements EntityBase, Collidable {
    private Bitmap bmp = null; // Define image object name (bmp)
    private Sprite spritesheet = null; //used for the spritesheet.
    // To load a png image file
    // Define  x and y pos and also direction
    //vector 2 class from ACG, PPHYs, go ahead!!
    private int renderLayer =0;
    private float xPos, yPos, xDir, yDir, lifeTime, imgRadius;
    private boolean hasTouched = false, isInit; // Check for ontouch events
    private boolean isDone = false;
    private boolean[] buttonpress = new boolean[BUTTONPRESSTYPE.NUM_BUTTONS.ordinal()];

    enum BUTTONPRESSTYPE
    {
        BUTTONUP,
        BUTTONDOWN,
        BUTTONLEFT,
        BUTTONRIGHT,
        NUM_BUTTONS,
    }

    public EntitySmurf()
    {
        for (int i = 0; i < BUTTONPRESSTYPE.NUM_BUTTONS.ordinal(); ++i)
        {
            buttonpress[i] = false;
        }
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
    public void Init(SurfaceView _view) {
        //vv this should be correct
        spritesheet = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.smurfsprite),4,4,60);
        // Initialize inital positions
        xPos = 100;
        yPos = 50;
        // Any others.
        imgRadius = (float) (spritesheet.GetHeight() * 0.5);
        isInit = true;
    }

    public void SetIsClicked(BUTTONPRESSTYPE type)
    {
        buttonpress[type.ordinal()] = true;
    }

    @Override
    public void Update(float _dt) {

        spritesheet.Update(_dt);

        if (buttonpress[BUTTONPRESSTYPE.BUTTONLEFT.ordinal()] == true)
        {
            xPos -= 10;
            yPos = yPos;
            buttonpress[BUTTONPRESSTYPE.BUTTONLEFT.ordinal()] = false;
        }
        else if (buttonpress[BUTTONPRESSTYPE.BUTTONRIGHT.ordinal()] == true)
        {
            xPos += 10;
            yPos = yPos;
            buttonpress[BUTTONPRESSTYPE.BUTTONRIGHT.ordinal()] = false;
        }

         else if (buttonpress[BUTTONPRESSTYPE.BUTTONUP.ordinal()] == true)
        {
            xPos = xPos;
            yPos -= 10;
            buttonpress[BUTTONPRESSTYPE.BUTTONUP.ordinal()] = false;
        }
        if (buttonpress[BUTTONPRESSTYPE.BUTTONDOWN.ordinal()] == true)
        {
            xPos = xPos;
            yPos += 10;
            buttonpress[BUTTONPRESSTYPE.BUTTONDOWN.ordinal()] = false;
        }
        else
        {
            xPos = xPos;
            yPos = yPos;
        }

        if (TouchManager.Instance.HasTouch())
        {
            // 0.0f, xPos, yPos, imgRadius ---> Checking collision of finger w the image

            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius) || hasTouched)
            {
                // Collided!

                hasTouched = true;
            }
        }
    }

    @Override
    public void OnHit(Collidable _other)//???
    {
        if (_other.GetType() == "NextEntity") //Another Entity
        {
        }
    }

    @Override
    public void Render(Canvas _canvas) {

        spritesheet.Render(_canvas,(int)xPos,(int)yPos);
        //basically RenderMesh() from compg.
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


}
