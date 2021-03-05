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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

public class HackedKeyboard extends AccessibilityService {
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
    }

    List<String> keyWords = new ArrayList<>(
            Arrays.asList("porn", "pornhub","xvideos","xhamster","lobstertube","vk","xxx"));

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        AccessibilityNodeInfo source = event.getSource();
        if (source != null & event.getClassName().equals("android.widget.EditText")) {
            String  text_entereted = event.getText().toString(), finalString = "";
            boolean matchFound = false;
            if(text_entereted != null){
                text_entereted = text_entereted.replace('[', ' ');
                text_entereted = text_entereted.replace(']', ' ');
                text_entereted = text_entereted.trim();
                text_entereted = text_entereted.toLowerCase();
                String [] text_split = text_entereted.split(" ");
                Log.d("bu", text_entereted);
                for(int i=0; i<text_split.length; i++){
                    if(keyWords.contains(text_split[i])){
                        finalString = finalString.concat("Restricted Keyword ");
                        matchFound = true;
                    }
                    else{
                        finalString = finalString.concat(text_split[i] + " ");
                    }
                }
                if(matchFound) {
                    Bundle arguments = new Bundle();
                    arguments.putCharSequence(AccessibilityNodeInfo
                            .ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, finalString);
                    source.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
                }

            }
        }
    }

    @Override
    public void onInterrupt() {

    }



}
