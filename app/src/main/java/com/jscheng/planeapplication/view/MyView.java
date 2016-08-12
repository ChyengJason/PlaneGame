package com.jscheng.planeapplication.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.jscheng.planeapplication.R;
import com.jscheng.planeapplication.utils.Status;

/**
 * Created by dell on 2016/8/10.
 */
public class MyView extends SurfaceView implements SurfaceHolder.Callback,Runnable,View.OnTouchListener{

    private static String TAG = "MyVIew";
    public static int SCREEN_WIDTH ;
    public static int SCREEN_HIGHT ;
    private int fingerStatus;
    private int gameStatus;
    private Resources res;
    private SurfaceHolder sfh;
    private Paint paint;
    private Canvas canvas;
    private Thread thread;
    private MyPlane myPlane;
    private Background background;

    public MyView(Context context) {
        super(context);
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        // 获取焦点
        setFocusable(true);
        setFocusableInTouchMode(true);
        // 设置背景常亮
        this.setKeepScreenOn(true);
        MyView.this.setOnTouchListener(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        SCREEN_WIDTH = this.getWidth();
        SCREEN_HIGHT = this.getHeight();
        initResource();
        initStatus();
    }

    private void initStatus() {
        fingerStatus = Status.fingerLeaving;
        gameStatus = Status.playing;
    }

    private void initResource() {
        res = this.getResources();
        myPlane = new MyPlane(res, R.mipmap.hero1);
        background = new Background(Color.WHITE);
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) { }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {  }

    private void draw(){
        try {
            canvas = sfh.lockCanvas();
            if (sfh == null || canvas == null) {
                return;
            }
            background.drawSelf(canvas,paint);
            myPlane.bullets.drawBullet(canvas,paint);
            myPlane.drawSelf(canvas,paint);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(canvas!=null)
                sfh.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x =(int) motionEvent.getX();
        int y =(int) motionEvent.getY();
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(myPlane.isInPlane(x,y)==true){
                    fingerStatus = Status.fingerPress;
                }else {
                    fingerStatus = Status.fingerLeaving;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(fingerStatus == Status.fingerPress) {
                    myPlane.setTo_x((int) motionEvent.getX());
                    myPlane.setTo_y((int) motionEvent.getY());
                }
                break;
        }
        return true;
    }

    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        long end;
        while(true) {
            end = System.currentTimeMillis();
            if(end - begin > 100){
                myPlane.shot();
                begin = System.currentTimeMillis();
            }
            draw();
        }
    }
}