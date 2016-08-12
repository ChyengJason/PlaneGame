package com.jscheng.planeapplication.view;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.jscheng.planeapplication.base.ObjectView;

/**
 * Created by dell on 2016/8/12.
 */
public class EnemyPlane extends ObjectView {
    private boolean isDispear;
    private int res_x;
    private int res_y;
    private int to_x;
    private int to_y;
    private int screen_width;
    private int screen_height;
    private int res_width;
    private int res_height;
    private boolean isShoot;
    Bitmap bitmap;

    public EnemyPlane(Resources resources, int res_id, int res_x, int res_y){
        this.isDispear = false;
        this.isShoot = false;
        this.res_x = res_x;
        this.res_y = res_y;
        this.to_x = res_x;
        this.to_y = res_y;
        this.bitmap = BitmapFactory.decodeResource(resources,res_id);
        this.screen_width =  MyView.SCREEN_WIDTH;
        this.screen_height =  MyView.SCREEN_HIGHT;
        this.res_width = bitmap.getWidth();
        this.res_height = bitmap.getHeight();
    }

    @Override
    public void drawSelf(Canvas canvas, Paint paint) {
        if(isDispear == false) {
            canvas.save();
            canvas.clipRect(res_x, res_y, res_x + res_width, res_y + res_height);
            canvas.drawBitmap(bitmap, res_x, res_y, paint);
            canvas.restore();
        }
    }

    //子弹前进
    public void go(){
        res_y += res_height;
    }

    public void shoot(){

    }

    public boolean isDispear() {
        return isDispear;
    }

    public void setDispear(boolean dispear) {
        isDispear = dispear;
    }


    public int getRes_x() {
        return res_x;
    }

    public void setRes_x(int res_x) {
        this.res_x = res_x;
    }

    public int getRes_y() {
        return res_y;
    }

    public void setRes_y(int res_y) {
        this.res_y = res_y;
    }


    public int getRes_width() {
        return res_width;
    }

    public void setRes_width(int res_width) {
        this.res_width = res_width;
    }

    public int getRes_height() {
        return res_height;
    }

    public void setRes_height(int res_height) {
        this.res_height = res_height;
    }


    public boolean isShoot() {
        return isShoot;
    }

    public void setShoot(boolean shoot) {
        isShoot = shoot;
    }

}
