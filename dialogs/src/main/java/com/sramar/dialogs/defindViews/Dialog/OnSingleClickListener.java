package com.sramar.dialogs.defindViews.Dialog;
import android.view.View;

public abstract class OnSingleClickListener implements View.OnClickListener {
    private long mLastClickTime;
    private long timeInterval = 200L;

    public OnSingleClickListener() {

    }

    public OnSingleClickListener(long interval) {
        this.timeInterval = interval;
    }

    @Override
    public void onClick(View v) {
        long nowTime = System.currentTimeMillis();
        if (nowTime - mLastClickTime > timeInterval) {
            // 单次点击事件
            onSingleClick(v);
            mLastClickTime = nowTime;
        } else {
            // 快速点击事件
//            onFastClick();
        }
    }

    protected abstract void onSingleClick(View view);
}