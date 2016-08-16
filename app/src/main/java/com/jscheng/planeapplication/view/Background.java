package com.jscheng.planeapplication.view;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.jscheng.planeapplication.base.ObjectView;
import com.jscheng.planeapplication.utils.Constants;

/**
 * Created by dell on 2016/8/11.
 */
public class Background extends ObjectView {
    boolean isExist;
    boolean isVisual;
    int res_width;
    int res_height;
    Bitmap bitmap1;
    Bitmap bitmap2;
    Boolean isBitmap = false;
    int color;
    int res_y1,res_y2;
    long time;
    public Background(Resources res, int r_id){
        this.bitmap1 = BitmapFactory.decodeResource(res,r_id);
        this.bitmap2 = BitmapFactory.decodeResource(res,r_id);
        isBitmap = true;
        this.res_height = MyView.SCREEN_HIGHT;
        this.res_width = MyView.SCREEN_WIDTH;
        res_y1 = -Math.abs(bitmap1.getHeight() - MyView.SCREEN_HIGHT);
        res_y2 = res_y1 - bitmap1.getHeight() + 100;
        isExist = true;
        isVisual = true;
        time =0;
    }

    public Background(int color){
        isBitmap = false;
        this.res_height = MyView.SCREEN_HIGHT;
        this.res_width = MyView.SCREEN_WIDTH;
        isExist = true;
        isVisual = true;
        this.color = color;
        time = 0;
    }

    public void drawSelf(Canvas canvas, Paint paint){
        if(isExist && isVisual) {
            if(isBitmap) {
                paint.setColor(Color.WHITE);
                canvas.save();
                canvas.clipRect(0, 0,  res_width,  res_height);
                canvas.drawBitmap(bitmap1, 0,res_y1,paint);
                canvas.drawBitmap(bitmap2, 0,res_y2,paint);
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

    public void backgroundGo(long endTime){
        if(isBitmap && endTime-time> Constants.ENEMY_GOTIME){
            time = endTime;
            res_y1 += Constants.BACKGROUND_DISTANCE;
            res_y2 += Constants.BACKGROUND_DISTANCE;
            if (res_y1 >MyView.SCREEN_HIGHT)
                res_y1 = res_y2 - bitmap1.getHeight() + 100;
            if (res_y2 > MyView.SCREEN_HIGHT)
                res_y2 = res_y1 - bitmap1.getHeight() + 100;
        }
    }
}
