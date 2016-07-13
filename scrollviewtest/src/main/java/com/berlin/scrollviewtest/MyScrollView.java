package com.berlin.scrollviewtest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by berlin on 2016/7/13 0013.
 */
public class MyScrollView extends ScrollView{

    private MyScrollListener myScrollListener;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setMyScrollListener(MyScrollListener myScrollListener){
        this.myScrollListener = myScrollListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (myScrollListener!=null){
            myScrollListener.onScrollChanged(l,t,oldl,oldt);
        }
    }
}
