package com.example.lixin.yuekaotestdemo3;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by hua on 2017/8/23.
 */

public class PhotoViewpager extends ViewPager {
    public PhotoViewpager(Context context) {
        super(context);
    }

    public PhotoViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //重写该方法来解决冲突问题  test.bawei.com.tupianosuofang.PhotoViewPager（自定义布局的全名自己//类的名字）
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            //写自己要处理的error包括报错日志
            e.printStackTrace();
            Log.e("TAG", "onInterceptTouchEvent: " );

            return false;
        }
    }
}
