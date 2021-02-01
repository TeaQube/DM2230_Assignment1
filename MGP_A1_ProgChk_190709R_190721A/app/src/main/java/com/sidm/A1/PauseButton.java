package com.sidm.A1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;

//by qing bing
public class PauseButton implements EntityBase {

    private boolean isDone = false;
    private boolean isInit = false;
    private boolean isPaused = false;

    private int renderLayer =1;

    private Bitmap bmpP = null;
    private Bitmap scaledbmp = null;

    //pos to indicate where to draw button
    private float xPos, yPos;
    //scale the image based on the screen size
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
    public void Init(SurfaceView _view)
    {
        //call drawable of pause button
        bmpP = ResourceManager.Instance.GetBitmap(R.drawable.pausebutton);

        // Find screen width and screen height
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

        //scale image accordingly / based on the screen size
        scaledbmp = Bitmap.createScaledBitmap(bmpP, screenWidth /8, screenHeight /6, true);

        //adjust position of pause button
        xPos = screenWidth - 130;
        yPos = 90;
        isInit = true;

    }

    @Override
    public void Update(float _dt) {
        if(TouchManager.Instance.HasTouch()) {
            if (TouchManager.Instance.IsDown() && !isPaused) {   // Check touch collision here
                float imgRadius = scaledbmp.getHeight() * 0.5f;

                if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius)) {
                    isPaused = true;

                    // When button is pressed, U can play an audio clip
                    // AudioManager.Instance.PlayAudio(R.raw.clicksound);
                    PauseConfirmDialogFragment newPauseConfirm = new PauseConfirmDialogFragment();
                    newPauseConfirm.show(GamePage.Instance.getSupportFragmentManager(), "PauseConfirm");
                    GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIsPaused());

                    // If just want a pause without the (popup dialog --> No done yet.)
                    // Method already written in your GameSystem class from Week 5
                    //GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIsPaused());
                }
            }
        }
        else
        {
            isPaused = false;
        }
    }

    @Override
    public void Render(Canvas _canvas) {
        if (isPaused == false)
        {
            _canvas.drawBitmap(scaledbmp, xPos - scaledbmp.getWidth() * 0.5f, yPos - scaledbmp.getHeight() * 0.5f, null);
        }
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

    public static PauseButton Create()
    {
        PauseButton result = new PauseButton();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_PAUSE);
        return result;
    }

    public static PauseButton Create(int _layer)
    {
        PauseButton result = Create();
        result.SetRenderLayer(_layer);
        return result;
    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_PAUSE;
    }

}
