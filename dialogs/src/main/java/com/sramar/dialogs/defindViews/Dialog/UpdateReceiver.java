package com.sramar.dialogs.defindViews.Dialog;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class UpdateReceiver extends BroadcastReceiver {
    private static final String TAG = "UpdateReceiver";
 
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
 
        if (Intent.ACTION_PACKAGE_REPLACED.equals(action) || Intent.ACTION_MY_PACKAGE_REPLACED.equals(action)) {
            try {
                String scheme = intent.getScheme();
                String packageName = intent.getData().getSchemeSpecificPart();
                if (context.getPackageName().equals(packageName)) {
                    final Intent intent1 = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent1);
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}