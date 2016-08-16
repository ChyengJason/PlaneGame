package com.jscheng.planeapplication.view;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.jscheng.planeapplication.base.ObjectView;
import com.jscheng.planeapplication.utils.Constants;

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
    private boolean isShoot;//是否射击
    private boolean isBoom;//是否爆炸
    private Bitmap bitmap;
    private Bitmap bitmap_down[];
    private int boomTime;

    public EnemyPlane(Resources resources, int res_id, int res_donw[],int res_x, int res_y){
        this.isDispear = false;
        this.isShoot = false;
        this.isBoom = false;
        this.bitmap = BitmapFactory.decodeResource(resources,res_id);
        this.res_width = bitmap.getWidth();
        this.res_height = bitmap.getHeight();
        if(res_x>MyView.SCREEN_WIDTH-res_width)
            res_x = MyView.SCREEN_WIDTH-res_width;
        if(res_x<res_width)
            res_x = res_width;
        this.res_x = res_x;
        this.res_y = res_y;
        this.to_x = res_x;
        this.to_y = res_y;
        this.bitmap_down = new Bitmap[res_donw.length];
        for(int i=0;i<bitmap_down.length;i++)
            this.bitmap_down[i] = BitmapFactory.decodeResource(resources,res_donw[i]);
        this.boomTime = 0;
    }

    @Override
    public void drawSelf(Canvas canvas, Paint paint) {
        if (isDispear == false && isBoom == false) {
            canvas.save();
            canvas.clipRect(res_x, res_y, res_x + res_width, res_y + res_height);
            canvas.drawBitmap(bitmap, res_x, res_y, paint);
            canvas.restore();
        } else if (isDispear == false && isBoom == true) {//被击中
            boomTime++;
            if(boomTime < Constants.ENEMY_SMALL_BOOMTIME) {
                canvas.save();
                canvas.clipRect(res_x, res_y, res_x + res_width, res_y + res_height);
                canvas.drawBitmap(bitmap_down[boomTime / (Constants.ENEMY_SMALL_BOOMTIME / bitmap_down.length) ], res_x, res_y, paint);
                canvas.restore();
            }
            else{
                isDispear = true;
            }
        }
    }

    //子弹前进
    public void go(){
        if(isBoom == false && isDispear==false)
            res_y += (res_height/2);
    }

    public void shoot(){

    }

    public boolean isInside(int x,int y){//int left, int top, int right, int bottom
        if(x > res_x && x  < res_x + res_width
                && y > res_y && y < res_y + res_height)
            return true;
        return false;
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

    public boolean isBoom() {
        return isBoom;
    }

    public void setBoom(boolean boom) {
        isBoom = boom;
    }
}
