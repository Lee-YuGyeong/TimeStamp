package com.example.timestamp.ui.myStamp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DrawToBitmap {

    public Bitmap drawTimeToBitmap(Context mContext, Bitmap bitmap) {

        long now = System.currentTimeMillis();
        Date mDate = new Date(now);

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일 (E) a hh:mm:ss");
        String getTime = simpleDate.format(mDate);


        try {
            Resources resources = mContext.getResources();
            float scale = resources.getDisplayMetrics().density;

            android.graphics.Bitmap.Config bitmapConfig =   bitmap.getConfig();
            // set default bitmap config if none
            if(bitmapConfig == null) {
                bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
            }
            // resource bitmaps are imutable,
            // so we need to convert it to mutable one
            bitmap = bitmap.copy(bitmapConfig, true);

            Canvas canvas = new Canvas(bitmap);
            // new antialised Paint
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            // text color - 흰색
            paint.setColor(Color.rgb(255,255, 255));
            // text size in pixels
            paint.setTextSize((int) (14 * scale));
            // text shadow
            paint.setShadowLayer(1f, 0f, 1f, Color.DKGRAY);

            paint.setFakeBoldText(true);

            // draw text to the Canvas center
            Rect bounds = new Rect();
            paint.getTextBounds(getTime, 0, getTime.length(), bounds);
            int x = (bitmap.getWidth() - bounds.width())/6;
            int y = (bitmap.getHeight() + bounds.height())/5;

            Rect bounds2 = new Rect();
            paint.getTextBounds(getTime, 0, getTime.length(), bounds2);
            int x2 = (bitmap.getWidth() - bounds2.width())/6;
            int y2 = (bitmap.getHeight() + bounds2.height())/5 ;

            canvas.drawText(getTime, x2 * scale, y2 * scale, paint);

            return bitmap;
        } catch (Exception e) {
            // TODO: handle exception

            return null;
        }

    }


}
