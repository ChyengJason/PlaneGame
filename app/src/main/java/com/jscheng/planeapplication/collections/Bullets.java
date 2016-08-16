package com.jscheng.planeapplication.collections;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.jscheng.planeapplication.view.Bullet;
import com.jscheng.planeapplication.view.MyPlane;
import com.jscheng.planeapplication.view.MyView;
import java.util.Vector;

/**
 * Created by dell on 2016/8/11.
 */
public class Bullets extends Vector<Bullet> {

    //创建子弹
    public Bullet createBulet(Resources resources, int res_id, int res_x, int res_y) {
        Bullet bullet = new Bullet(resources, res_id, res_x, res_y);
        addBullet(bullet);
        return bullet;
    }

    public void drawBullet(Canvas canvas, Paint paint) {
        for (Bullet bullet : this) {
            bullet.drawSelf(canvas, paint);
        }
    }

    public void addBullet(Bullet bullet) {
        this.add(bullet);
    }

    public void deleteBullet(Bullet bullet) {
        bullet.setDispear(true);
        this.remove(bullet);
    }

    //子弹前进
    public void BulletGo(MyPlane myPlane) {
        for (int i = this.size() - 1; i >= 0; i--) {
            Bullet bullet = get(i);
            bullet.go();
            if (bullet.isDispear()||bullet.getRes_y() >= MyView.SCREEN_HIGHT - bullet.getRes_height() || bullet.getRes_y() <= bullet.getRes_height()
                    || bullet.getRes_x() >= MyView.SCREEN_WIDTH || bullet.getRes_x() <= 0
                    || myPlane.isInPlane(bullet.getRes_x(), bullet.getRes_y()))
                deleteBullet(bullet);
        }
    }
}
