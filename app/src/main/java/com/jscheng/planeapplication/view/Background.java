package com.jscheng.planeapplication.view;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by dell on 2016/8/11.
 */
public class Background extends ObjectView{
    boolean isExist;
    boolean isVisual;
    int screen_width;
    int screen_height;
    int res_width;
    int res_height;
    Bitmap bitmap;
    Boolean isBitmap = false;
    int color;

    public Background(Resources res, int r_id,int res_width, int res_height){
        this.bitmap = BitmapFactory.decodeResource(res,r_id);
        isBitmap = true;
        this.res_height = res_height;
        this.res_width = res_width;
        this.screen_height = MyView.SCREEN_HIGHT;
        this.screen_width = MyView.SCREEN_WIDTH;
        isExist = true;
        isVisual = true;
    }

    public Background(Resources res, int r_id){
        this.bitmap = BitmapFactory.decodeResource(res,r_id);
        isBitmap = true;
        this.screen_height = MyView.SCREEN_HIGHT;
        this.screen_width = MyView.SCREEN_WIDTH;
        this.res_height = MyView.SCREEN_HIGHT;
        this.res_width = MyView.SCREEN_WIDTH;
        isExist = true;
        isVisual = true;
    }

    public Background(int color, int res_width, int res_height){
        isBitmap = false;
        this.res_height = res_height;
        this.res_width = res_width;
        this.screen_height = MyView.SCREEN_HIGHT;
        this.screen_width = MyView.SCREEN_WIDTH;
        isExist = true;
        isVisual = true;
        this.color = color;
    }

    public Background(int color){
        isBitmap = false;
        this.res_height = MyView.SCREEN_HIGHT;
        this.res_width = MyView.SCREEN_WIDTH;
        this.screen_height = MyView.SCREEN_HIGHT;
        this.screen_width = MyView.SCREEN_WIDTH;
        isExist = true;
        isVisual = true;
        this.color = color;
    }

    public void drawSelf(Canvas canvas, Paint paint){
        if(isExist && isVisual) {
            if(isBitmap) {
                paint.setColor(color);
                canvas.save();
                canvas.drawBitmap(bitmap, res_width,res_height,paint);
                canvas.restore();
            }else{
                paint.setColor(color);
                canvas.save();
                Rect rect = new Rect(0,0,res_width,res_height);
                canvas.drawRect(rect, paint);
                canvas.restore();
            }
        }
    }
}
