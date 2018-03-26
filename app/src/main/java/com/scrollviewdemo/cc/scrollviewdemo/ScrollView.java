package com.scrollviewdemo.cc.scrollviewdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by CC on 2018/3/26.
 */

public class ScrollView extends View {

    private Scroller mScroller;

    public ScrollView(Context context) {
        this(context,null);
    }

    public ScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        float lastX=0;
        float lastY=0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                 lastX=x;
                 lastY=y;
                break;
            case MotionEvent.ACTION_MOVE:
                int dx= (int) (x-lastX);
                int dy= (int) (y-lastY);
                ((View)getParent()).scrollBy(-dx,-dy);

//                layout(getLeft()+dx,getTop()+dy,getRight()+dx,getBottom()+dy);
//                                lastX=x;
//                                lastY=y;
                break;
            case MotionEvent.ACTION_UP:
               View view= ((View)getParent());
                mScroller.startScroll(view.getScrollX(),view.getScrollY(),-view.getScrollX(),-view.getScrollY(),3000);
                invalidate();
                break;
            default:
                break;
        }
//        invalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            ((View)getParent()).scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

}
