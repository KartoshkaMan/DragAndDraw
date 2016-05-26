package com.bignerdranch.android.draganddraw;

import android.graphics.PointF;

/**
 * Created by user on 3/14/16.
 */
public class Box {

    private PointF mCurrent;
    private PointF mOrigin;

    public Box(PointF origin) {
        mOrigin = origin;
        mCurrent = origin;
    }

    public PointF getCurrent() {
        return mCurrent;
    }
    public PointF getOrigin() {
        return mOrigin;
    }

    public void setCurrent(PointF current) {
        mCurrent = current;
    }
    public void setOrigin(PointF origin) {
        mOrigin = origin;
    }

}
