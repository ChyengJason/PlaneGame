package com.jscheng.planeapplication.view;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.jscheng.planeapplication.R;
import com.jscheng.planeapplication.base.ObjectView;
import com.jscheng.planeapplication.collections.Bullets;
import com.jscheng.planeapplication.collections.Enemies;
import com.jscheng.planeapplication.utils.Constants;

/**
 * Created by dell on 2016/8/11.
 */
public class MyPlane extends ObjectView {
    boolean isDispear;
    boolean isBoom;
    private int res_x;
    private int res_y;
    private int to_x;
    private int to_y;
    private int res_width;
    private int res_height;
    private Bitmap bitmap;
    private Bitmap bitmap_down[];
    public Bullets bullets;
    private Resources resources;
    private long time;
    private int boomTime;

    public  MyPlane(Resources resources,int res_id,int[] res_down,int res_x,int res_y){
        initConfig(resources,res_id,res_down);
        this.res_x = res_x;
        this.res_y = res_y;
        this.to_x = res_x;
        this.to_y = res_y;
    }

    public  MyPlane(Resources resources,int res_id,int[] res_down){
        initConfig(resources,res_id,res_down);
        this.res_x =  MyView.SCREEN_WIDTH/2 - res_width/2;
        this.res_y = MyView.SCREEN_HIGHT - res_height;
        this.to_x = res_x;
        this.to_y = res_y;
    }

    private void initConfig(Resources resources,int res_id,int res_down[]){
        this.isDispear = false;
        this.bitmap = BitmapFactory.decodeResource(resources,res_id);
        this.bitmap_down = new Bitmap[res_down.length];
        for(int i= 0;i<res_down.length;i++) {
            this.bitmap_down[i] = BitmapFactory.decodeResource(resources, res_down[i]);
        }
        this.res_width = bitmap.getWidth();
        this.res_height = bitmap.getHeight();
        this.resources = resources;
        this.time = 0;
        this.isBoom = false;
        this.boomTime = 0;
        bullets =new Bullets();
    }

    public void drawSelf(Canvas canvas,Paint paint){
        bullets.drawBullet(canvas,paint);//绘画子弹

        if(isDispear == false && isBoom == false) {
            res_x = to_x;
            res_y = to_y;
            paint.setColor(Color.YELLOW);
            canvas.save();
            canvas.clipRect(res_x, res_y, res_x + res_width, res_y + res_height);
            canvas.drawBitmap(bitmap, res_x, res_y, paint);
            //Log.d("tag",res_x+"  "+res_y);
            canvas.restore();
        }else if(isDispear==false && isBoom ==true){
            boomTime++;
            if(boomTime < Constants.MYPLANE_BOOMTIME) {
                canvas.save();
                canvas.clipRect(res_x, res_y, res_x + res_width, res_y + res_height);
                canvas.drawBitmap(bitmap_down[boomTime / (Constants.MYPLANE_BOOMTIME / bitmap_down.length) ], res_x, res_y, paint);
                canvas.restore();
            }
            else{
                isDispear = true;
            }
        }
    }

    //发射子弹
    public void shoot(long endTime, Enemies enemies){
        if(endTime - time > Constants.MYPLANE_SHOOTTIME || time == 0) {
            if(isBoom==false && isDispear==false)
                bullets.createBulet(resources, R.mipmap.bullet1, res_x + res_width / 2, res_y);
            bullets.BulletGo(this);
            time = endTime;
        }
        for(int i=0;i<bullets.size();i++)
            for (int j=0;j<enemies.size();j++){
                if (enemies.get(j).isDispear()==false&&
                        enemies.get(j).isInside(bullets.get(i).getRes_x(),bullets.get(i).getRes_y())==true){
                    //击中
                    if(enemies.get(j).isDispear()==false) {
                        enemies.get(j).setBoom(true);
                        if (bullets.get(i).isDispear() == false)
                            bullets.get(i).setDispear(true);
                    }
                }
            }
    }

    public int getRes_x() {
        return res_x;
    }

    public void setRes_x(int res_x) {
        this.res_x = res_x- res_width/2;
    }

    public int getRes_y() {
        return res_y;
    }

    public void setRes_y(int res_y) {
        this.res_y = res_y - res_height*2/3;
    }

    public int getTo_x(){
        return to_x;
    }

    public void setTo_x(int to_x) {
        this.to_x = to_x - res_width/2;
    }

    public int getTo_y(){
        return to_x;
    }

    public void setTo_y(int to_y) {
        this.to_y = to_y - res_height*2/3;
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

    public boolean isInPlane(int x,int y){
        if(x > res_x && x  < res_x + res_width
                && y > res_y && y < res_y + res_height)
            return true;
        return false;
    }

    public boolean isCrashPlane(EnemyPlane enemyPlane){
        int x01 = res_x, x02 = res_x+res_width;
        int dx = (x01+x02)/2;
        x01 = (x01+dx)/2;  x02 = (x02+dx)/2;
        int y01 = res_y, y02 = res_y+res_height;
        int dy = (y01+y02)/2;
        y01 = (y01+dy)/2;  y02 = (y02+dy)/2;

        int x11 = enemyPlane.getRes_x(),x12 = enemyPlane.getRes_x()+enemyPlane.getRes_width();
        int y11 = enemyPlane.getRes_y(),y12 = enemyPlane.getRes_y()+enemyPlane.getRes_height();

        int zx = Math.abs(x01+x02-x11-x12); //两个矩形重心在x轴上的距离的两倍
        int x = Math.abs(x01-x02)+Math.abs(x11-x12); //两矩形在x方向的边长的和
        int zy = Math.abs(y01+y02-y11-y12); //重心在y轴上距离的两倍
        int y = Math.abs(y01-y02)+Math.abs(y11-y12); //y方向边长的和
        if(zx <= x && zy <= y)
           return true;
        else
            return false;
    }

    public boolean isDispear() {
        return isDispear;
    }

    public void setDispear(boolean dispear) {
        isDispear = dispear;
    }

    public boolean isBoom() {
        return isBoom;
    }

    public void setBoom(boolean boom) {
        isBoom = boom;
    }
}
