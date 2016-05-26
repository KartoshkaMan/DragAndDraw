package com.bignerdranch.android.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BoxDrawingView extends View {

    private static final String TAG = "BoxDrawingView";

    private Box mCurentBox;
    private List<Box> mBoxes = new ArrayList<>();
    private Paint mBoxPaint;
    private Paint mBackgroundPaint;

    public BoxDrawingView(Context context) {
        super(context, null);
    }
    public BoxDrawingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        mBoxPaint = new Paint();
        mBoxPaint.setColor(0x22ff0000);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xff8efe0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        PointF current = new PointF(event.getX(), event.getY());
        String action = "";

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                action = "ACTION_DOWN";

                mCurentBox = new Box(current);
                mBoxes.add(mCurentBox);

                break;
            case MotionEvent.ACTION_MOVE:
                action = "ACTION_MOVE";

                if (mCurentBox != null) {
                    mCurentBox.setCurrent(current);
                    invalidate();
                }

                break;
            case MotionEvent.ACTION_UP:
                action = "ACTION_UP";

                mCurentBox = null;

                break;
            case MotionEvent.ACTION_CANCEL:
                action = "ACTION_CANCEL";

                mCurentBox = null;

                break;
        }

        Log.i(TAG, action + " at x = " + current.x + ", y = " + current.y);

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(mBackgroundPaint);

        for (Box box : mBoxes) {
            float left = Math.min(box.getOrigin().x, box.getCurrent().x);
            float right = Math.max(box.getOrigin().x, box.getCurrent().x);

            float top = Math.min(box.getOrigin().y, box.getCurrent().y);
            float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);

            canvas.drawRect(left, top, right, bottom, mBoxPaint);
        }
    }
}
