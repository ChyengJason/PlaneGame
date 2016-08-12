package com.jscheng.planeapplication;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.jscheng.planeapplication.utils.Enemies;
import com.jscheng.planeapplication.view.MyView;

/**
 * Created by dell on 2016/8/12.
 * 所有敌军的控制类
 */
public class EnemyControl {
    private final static String TAG = "EnemyControl";
    private long time;
    Enemies enemies;
    private Resources resources;

    public EnemyControl(Resources resources){
        this.resources = resources;
        this.enemies = new Enemies();
        this.time = 0;
    }

    public void drawSelf(Canvas canvas, Paint paint){
        enemies.draw(canvas,paint);
    }
    int width = 30;
    public void dispatchTroops(long currentTime){
        Log.e(TAG,currentTime+"");
        if(time == 0)
            time = currentTime;
        if(currentTime - time > 2000){
            enemies.planeGo();
            width += 30;
            width %= MyView.SCREEN_WIDTH;
            enemies.createEnemy(resources,R.mipmap.enemy1,width ,20);
            time = currentTime;
        }
    }
}
