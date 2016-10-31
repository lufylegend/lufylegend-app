package com.lufylegend.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by zhanglubin on 16/11/01.
 */
public class LufylegendView extends WebView {
    private Paint mPaint = new Paint();
    public LufylegendView(Context context) {
        super(context);
    }

    public LufylegendView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LufylegendView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.STROKE); // 塗りつぶし無し

        canvas.drawCircle(150, 150, 25, mPaint); // 半径25
        canvas.drawCircle(150, 150, 50, mPaint); // 半径50
        canvas.drawCircle(150, 150, 100, mPaint); // 半径100
    }
}
