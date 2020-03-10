package com.uday.hacked;

import android.accessibilityservice.AccessibilityService;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.FrameLayout;


import static android.content.ContentValues.TAG;

public class HackedKeyboard extends AccessibilityService {
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        AccessibilityNodeInfo source = event.getSource();
        if (source != null & event.getClassName().equals("android.widget.EditText")) {
            String  text_entereted = event.getText().toString();
            text_entereted = text_entereted.replace('[', ' ');
            text_entereted = text_entereted.replace(']', ' ');
            text_entereted = text_entereted.trim();
            text_entereted = text_entereted.toLowerCase();
            if(text_entereted.equals("android")){
                Bundle arguments = new Bundle();
                arguments.putCharSequence(AccessibilityNodeInfo
                        .ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, "Hacked");
                source.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
            }
        }
    }

    @Override
    public void onInterrupt() {

    }

}
