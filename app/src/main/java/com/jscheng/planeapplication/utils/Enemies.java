package com.jscheng.planeapplication.utils;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.jscheng.planeapplication.view.EnemyPlane;
import com.jscheng.planeapplication.view.MyView;

import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by dell on 2016/8/12.
 */
public class Enemies extends Vector<EnemyPlane> {

    //创建敌军
    public EnemyPlane createEnemy(Resources resources, int res_id, int res_x, int res_y){
        EnemyPlane enemyPlane = new EnemyPlane(resources,res_id,res_x,res_y);
        addEnemyPlane(enemyPlane);
        return enemyPlane;
    }

    public void addEnemyPlane(EnemyPlane enemyPlane){
        this.add(enemyPlane);
    }

    public void deleteEnemyPlane(EnemyPlane enemyPlane){
        enemyPlane.setDispear(true);
        this.remove(enemyPlane);
    }

    public void draw(Canvas canvas, Paint paint){
        for(EnemyPlane enemyPlane:this){
            enemyPlane.drawSelf(canvas,paint);
        }
    }

    public void planeGo(){
        for(int i= this.size()-1;i>=0;i--){
            EnemyPlane enemyPlane = get(i);
            enemyPlane.go();
//            if(
//                    enemyPlane.getRes_y() >= MyView.SCREEN_HIGHT-enemyPlane.getRes_height()
//                    || enemyPlane.getRes_y() <= enemyPlane.getRes_height()
//                    || enemyPlane.getRes_x() >= MyView.SCREEN_WIDTH || enemyPlane.getRes_x()<=0)
//               deleteEnemyPlane(enemyPlane);
        }
    }

    public void shot(long time){
        for(EnemyPlane enemyPlane:this){
            if(enemyPlane.isShoot()){
                enemyPlane.shoot();
            }
        }
    }

}
