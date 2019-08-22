package com.jendi.intentfuzzer;

import android.app.Activity;
import android.content.pm.ComponentInfo;
import android.widget.TextView;

public class Helper {
    public static void updateLogScreen(ComponentInfo info, String log, Activity activity, final TextView textViewExceptionInfo) {
        final String[] message = new String[1];
        message[0] = "Testing component: " + info.name + "\n\n";
        message[0] = message[0].concat(log);
        message[0] = message[0].concat("\n\n\n");
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textViewExceptionInfo.setText(textViewExceptionInfo.getText() + message[0]);
            }
        });
    }
}
