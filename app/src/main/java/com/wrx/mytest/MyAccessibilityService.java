package com.wrx.mytest;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by wuruixiong on 2018/3/28.
 */

public class MyAccessibilityService extends AccessibilityService {


    public MyAccessibilityService() {
        super();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
    }

    @Override
    public void onInterrupt() {
    }

}
