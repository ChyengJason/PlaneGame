package com.jscheng.planeapplication.view;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.jscheng.planeapplication.R;
import com.jscheng.planeapplication.utils.Bullets;

/**
 * Created by dell on 2016/8/11.
 */
public class MyPlane extends ObjectView{
    boolean isExist;
    boolean isVisual;
    private int res_x;
    private int res_y;
    private int to_x;
    private int to_y;
    private int screen_width;
    private int screen_height;
    private int res_width;
    private int res_height;
    Bitmap bitmap;
    public Bullets bullets;
    Resources resources;

    public  MyPlane(Resources resources,int res_id, int res_x,int res_y){
        this.isExist = true;
        this.isVisual = true;
        this.res_x = res_x;
        this.res_y = res_y;
        this.to_x = res_x;
        this.to_y = res_y;
        this.bitmap =BitmapFactory.decodeResource(resources,res_id);
        this.screen_width =  MyView.SCREEN_WIDTH;
        this.screen_height =  MyView.SCREEN_HIGHT;
        this.res_width = bitmap.getWidth();
        this.res_height = bitmap.getHeight();
        this.resources = resources;
        bullets =new Bullets();
    }

    public  MyPlane(Resources resources,int res_id){
        this.isExist = true;
        this.isVisual = true;
        this.bitmap = BitmapFactory.decodeResource(resources,res_id);
        this.screen_width =  MyView.SCREEN_WIDTH;
        this.screen_height = MyView.SCREEN_HIGHT;
        this.res_width = bitmap.getWidth();
        this.res_height = bitmap.getHeight();
        this.res_x = screen_width/2 - res_width/2;
        this.res_y = screen_height - res_height;
        this.to_x = res_x;
        this.to_y = res_y;
        this.resources = resources;
        bullets =new Bullets();
    }

    public void drawSelf(Canvas canvas,Paint paint){
        res_x = to_x;
        res_y = to_y;
        if(isExist && isVisual) {
            paint.setColor(Color.WHITE);
            canvas.save();
            //canvas.clipRect(res_x, res_y, res_x + res_width, res_y + res_height);
            canvas.drawBitmap(bitmap, res_x, res_y, paint);
            Log.d("tag",res_x+"  "+res_y);
            canvas.restore();
        }
    }

    public void shot(){
        bullets.createBulet(resources,R.mipmap.bullet,res_x+ res_width/2,res_y);
        bullets.goBullet(this);
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
//        Log.e("TAG", "x: "+x+" y:"+ y);
//        Log.e("TAG", "res_x :"+res_x +"--" +  res_x + res_width);
//        Log.e("TAG", "res_y :"+res_y +"--" +  res_y + res_height);
        if(x > res_x && x  < res_x + res_width
                && y > res_y && y < res_y + res_height)
            return true;
        return false;
    }
}
