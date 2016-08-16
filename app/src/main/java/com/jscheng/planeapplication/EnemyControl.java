package com.jscheng.planeapplication;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.jscheng.planeapplication.utils.Constants;
import com.jscheng.planeapplication.collections.Enemies;
import com.jscheng.planeapplication.view.EnemyPlane;
import com.jscheng.planeapplication.view.MyPlane;
import com.jscheng.planeapplication.view.MyView;

import java.util.Random;

/**
 * Created by dell on 2016/8/12.
 * 所有敌军的控制类
 */
public class EnemyControl {
    private final static String TAG = "EnemyControl";
    private long goTime;
    private long dispatchTime;
    Enemies enemies;
    private Resources resources;

    public EnemyControl(Resources resources){
        this.resources = resources;
        this.enemies = new Enemies();
        this.goTime = 0;
        this.dispatchTime = 0;
    }

    public void drawSelf(Canvas canvas, Paint paint){
        enemies.draw(canvas,paint);
    }

    public void dispatchTroops(long currentTime){
        //Log.e(TAG,currentTime+"");
        if(dispatchTime == 0)
            dispatchTime = currentTime;
        if(currentTime - dispatchTime > Constants.ENEMY_DISPATCHTIME){
            int width = new Random().nextInt(MyView.SCREEN_WIDTH);
            enemies.createEnemy(resources,R.mipmap.enemy1,
                    new int[]{R.mipmap.enemy1_down1,R.mipmap.enemy1_down2,R.mipmap.enemy1_down3,R.mipmap.enemy1_down4},
                    width ,20);
//            int num = new Random().nextInt(20);
//            if(num<enemies.size()&& num>=0)
//                if(enemies.get(num).isBoom()==false)
//                enemies.get(num).setBoom(true);
            dispatchTime = currentTime;
        }
    }

    //敌机前进
    public void PlaneGo(long endTime){
        if(endTime - goTime > Constants.ENEMY_GOTIME || goTime == 0) {
            enemies.planeGo();
            goTime = endTime;
        }
    }

    //发射子弹
    public void shoot(long endTime, MyPlane myPlane){
        //检查是不是相撞
        for(EnemyPlane enemyPlane:enemies){
          if(enemyPlane.isDispear()==false && enemyPlane.isBoom()==false
                  &&myPlane.isBoom()==false && myPlane.isDispear()==false
                  &&myPlane.isCrashPlane(enemyPlane)==true) {
                  enemyPlane.setBoom(true);
                  myPlane.setBoom(true);
              }
        }
    }

    public Enemies getEnemies(){
        return enemies;
    }
}
