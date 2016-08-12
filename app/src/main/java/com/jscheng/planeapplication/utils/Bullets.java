package com.jscheng.planeapplication.utils;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.jscheng.planeapplication.view.Bullet;
import com.jscheng.planeapplication.view.MyPlane;
import com.jscheng.planeapplication.view.MyView;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by dell on 2016/8/11.
 */
public class Bullets extends CopyOnWriteArrayList<Bullet> {

    public Bullet createBulet(Resources resources, int res_id, int res_x,int res_y){
        Bullet bullet = new Bullet(resources,res_id,res_x, res_y);
        addBullet(bullet);
       return bullet;
    }

    public void drawBullet(Canvas canvas,Paint paint){
        for(Bullet bullet:this){
            bullet.drawSelf(canvas,paint);
        }
    }

    public void addBullet(Bullet bullet){
        this.add(bullet);
    }

    public void deleteBullet(Bullet bullet){
        bullet.setDispear(true);
        this.remove(bullet);
    }

    public void goBullet(MyPlane myPlane){
        for(Bullet bullet:this){
            bullet.go();
            if(bullet.getRes_y() >= MyView.SCREEN_HIGHT-bullet.getRes_height() || bullet.getRes_y() <= bullet.getRes_height()
                    || bullet.getRes_x() >= MyView.SCREEN_WIDTH || bullet.getRes_x()<=0
                    || myPlane.isInPlane(bullet.getRes_x(),bullet.getRes_y()))
                deleteBullet(bullet);
        }
    }
}
