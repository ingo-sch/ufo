package com.example.ufo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class UFOView extends View {
    private float xpos = -1;
    private float ypos = -1;
    private Bitmap ufoBitmap;

    public UFOView(Context context) {
        super(context);

        //lade Hintergrund
        this.setBackgroundResource(R.drawable.hintergrund);

        // lade Bitmap für UFO
        Resources resources = getResources();
        ufoBitmap = BitmapFactory.decodeResource(resources, R.drawable.ufo4);

        // Zeichenfläche soll Ereignisse empfangen können
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float dx = event.getX() - xpos;
        float dy = event.getY() - ypos;
        // wischen behandeln
        if(action == MotionEvent.ACTION_MOVE) {
            xpos += dx;
            ypos += dy;
        }
        // einzelnen Klick/Touch behandeln
        else if(action == MotionEvent.ACTION_DOWN ) {
            xpos += Math.signum(dx) * 25;
            ypos += Math.signum(dy) * 25;
        }
        //Grenzencheck, damit UFO nicht Bild verlässt
        if(xpos < 0) {
            xpos = 0;
        }
        if(xpos > getWidth()) {
            xpos = getWidth();
        }
        if(ypos < 0) {
            ypos = 0;
        }
        if(ypos > getHeight()) {
            ypos = getHeight();
        }
        //neu zeichnen
        invalidate();
        return true;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Startposition beim ersten Aufruf in die Mitte
        if (xpos==-1 && ypos==-1) {
            xpos = getWidth()/2;
            ypos = getHeight()/2;
        }
        // UFO zeichnen
        Paint iconPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.FILTER_BITMAP_FLAG);
        if (ufoBitmap != null) {
            canvas.drawBitmap(ufoBitmap,xpos-ufoBitmap.getWidth()/2,ypos-ufoBitmap.getHeight()/2,iconPaint);
        }
    }
}
