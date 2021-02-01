package com.sidm.A1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class EntitySmurf implements EntityBase, Collidable {
    private static int score;
    private static int health =70;
    private Bitmap bmp = null; // Define image object name (bmp)
    private Bitmap scaledbmp = null;
    private Sprite spritesheet = null; //used for the spritesheet.
    // To load a png image file
    // Define  x and y pos and also direction
    //vector 2 class from ACG, PPHYs, go ahead!!
    private int renderLayer =0;
    private float xPos, yPos, xDir, yDir, lifeTime, imgRadius;
    private boolean hasTouched = false, isInit; // Check for ontouch events
    private boolean isDone = false;

    private int Multiplier = 1;//what the score is multiplied by
    private float MuliplierTimer = 0;//how long a multiplier is supposed to last
    private boolean isMultiplied = false;//is a multiplier active?

    private float VulnerableTimer = 0;
    private boolean isVulnerable = false;

    private boolean[] buttonpress = new boolean[BUTTONPRESSTYPE.NUM_BUTTONS.ordinal()];
    int screenWidth, screenHeight;

    enum BUTTONPRESSTYPE
    {
        BUTTONUP,
        BUTTONDOWN,
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
        spritesheet = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.spaceships),1,1,60);
        spritesheet.Scale(60,80);
        imgRadius = (float) (spritesheet.GetHeight() * 0.5);
        //health = 70;
        //render screenWidth and screenHeight
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
        // Initialize inital positions
        xPos = 360 + imgRadius;
        yPos = screenHeight / 2;
        // Any others.
        isInit = true;
    }

    public void SetIsClicked(BUTTONPRESSTYPE type)
    {
        buttonpress[type.ordinal()] = true;
    }

    @Override
    public void Update(float _dt) {
        if(GameSystem.Instance.GetIsPaused())
            return;

        if(MuliplierTimer >0.f)
        {
            MuliplierTimer -=  _dt;
        }
        if(MuliplierTimer <= 0.f)
        {
            isMultiplied = false;
            Multiplier = 1;
            MuliplierTimer = 0.f;
        }

        if(VulnerableTimer > 0.f)
        {
            MuliplierTimer -=  _dt;
        }
        if(VulnerableTimer <=0.f)
        {
            isVulnerable = false;
            VulnerableTimer = 0.f;
        }

        spritesheet.Update(_dt);

        if (buttonpress[BUTTONPRESSTYPE.BUTTONUP.ordinal()] == true)
        {
            xPos = xPos;
            yPos -= 30;
            buttonpress[BUTTONPRESSTYPE.BUTTONUP.ordinal()] = false;

            if (yPos <= 0 + imgRadius)
            {
                yPos = 0 + imgRadius;
            }

        }
        if (buttonpress[BUTTONPRESSTYPE.BUTTONDOWN.ordinal()] == true)
        {
            xPos = xPos;
            yPos += 30;
            buttonpress[BUTTONPRESSTYPE.BUTTONDOWN.ordinal()] = false;

            if (yPos >= 1080 - imgRadius)
            {
                yPos = 1080 - imgRadius;
            }
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
            SetIsDone(true);
        }
        switch (_other.GetType())
        {
            case "EntityAsteroid":
            {
                if(!isVulnerable)
                {
                    health -= 5;
                }
                else
                {
                    health -= 15;
                }
                break;
            }
            case "EntityCollectible":
            {
                if(isMultiplied==false)
                {
                    score += 1 ;
                    break;
                }
                else
                {
                    score += 1 * Multiplier;
                    break;
                }
            }
            case "EntityHealthPickUp":
            {
                if(health <= 99)
                {
                    health += 5;
                }
                break;
            }
            case "EntityMultiplier":
            {
                Multiplier = 2;
                MuliplierTimer += 10.f;
                isMultiplied = true;
                break;
            }
            case "EntityVulnerable":
            {
                VulnerableTimer += 30.f;
                isVulnerable = true;
            }
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
        return "SmurfEntity";
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
    public float GetWidth() {
        return (float)(spritesheet.GetWidth());
    }

    @Override
    public float GetHeight() {
        return (float)(spritesheet.GetHeight());
    }

    //just not too bright way to do things, but hey.
    public void SetScore(int _score)
    {
        score += _score;
    }

    public static int GetScore()
    {
        return score;
    }

    public static int GetHealth(){return health;}
}
