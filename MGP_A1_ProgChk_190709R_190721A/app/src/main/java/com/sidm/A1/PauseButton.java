package com.sidm.A1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class PauseButton implements EntityBase{

    private boolean isDone = false;
    private boolean isInit = false;
    private boolean isPaused = false;

    private int renderLayer =0;
    private Bitmap bmpP = null;
    private Bitmap scaledbmpP = null;

    private Sprite spritesheet = null; //used for the spritesheet.

    private Bitmap bmpUp = null;
    private Bitmap scaledBMPUP = null;

    private float xPos, yPos, imgRadius;
    private int screenWidth, screenHeight;

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

        // Find screen width and screen height
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

        // Scale image according/ based on the screen size
        // (int)(ScreenWidth)/12 --> 12 is based off my pause image button
        // (int)(ScreenHeight)/7 --> 7 is based off my pause image button
        scaledbmpP = Bitmap.createScaledBitmap(bmpP, (int)(screenWidth)/12, (int)(screenHeight)/7, true);
        //scaledbmpUP = Bitmap.createScaledBitmap(bmpUP, (int)(ScreenWidth)/12, (int)(ScreenHeight)/7, true);

        // My own position adjustment for the Pause button placement
        // Change accordingly
        xPos = screenWidth - 80;
        yPos = screenHeight - imgRadius;

        isInit = true;
    }

    @Override
    public void Update(float _dt) {
        if(TouchManager.Instance.HasTouch())
        {
            if (TouchManager.Instance.IsDown() && !isPaused) {   // Check touch collision here
                float imgRadius = scaledbmpP.getHeight() * 0.5f;

                if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius)) {
                    isPaused = true; // Meant user had pressed the Pause button!!!

                    // When button is pressed, U can play an audio clip
                    // AudioManager.Instance.PlayAudio(R.raw.clicksound);

                    // If just want a pause without the (popup dialog --> No done yet.)
                    // Method already written in your GameSystem class from Week 5
                    GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIsPaused());
                }
            }
        }
        else
            isPaused = false;
    }

    @Override
    public void Render(Canvas _canvas) {
        spritesheet.Render(_canvas,(int)xPos,(int)yPos);
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

    public static PauseButton Create() {
        PauseButton result = new PauseButton();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_PAUSE);
        return result;
    }

    public static PauseButton Create(int _layer){
        PauseButton result = Create();
        result.SetRenderLayer(_layer);
        return result;
    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_PAUSE;
    }
    
}
