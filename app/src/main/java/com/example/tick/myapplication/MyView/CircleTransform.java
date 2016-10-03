package com.example.tick.myapplication.MyView;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

/**
 * Created by Tick on 2016/9/22.
 * 设置圆角
 */
public class CircleTransform implements Transformation {
    @Override
    public Bitmap transform(Bitmap source) {
        int size =Math.min(source.getWidth(),source.getHeight());
        int x = (source.getWidth()-size)/2;
        int y = (source.getHeight()-size)/2;
        Bitmap squareBitmap = Bitmap.createBitmap(source,x,y,size,size);
        if(squareBitmap!=source){
            source.recycle();
        }
        Bitmap bitmap = Bitmap.createBitmap(size,size,source.getConfig());

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader bitmapShader  = new BitmapShader(squareBitmap,BitmapShader.TileMode.CLAMP,BitmapShader.TileMode.CLAMP);
        paint.setShader(bitmapShader);
        float r = size/2f;
        canvas.drawCircle(r,r,r,paint);
        squareBitmap.recycle();

        return bitmap;
    }

    @Override
    public String key() {
        return "circle";
    }
}
