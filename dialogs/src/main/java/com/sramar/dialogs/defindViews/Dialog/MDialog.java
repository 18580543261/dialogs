package com.sramar.dialogs.defindViews.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sramar.dialogs.R;


public class MDialog extends Dialog {

    final int RIGHT = 0;
    final int WRONG = -1;
    final int UNFOUND = 1;
    final int ALREADY = 2;
    final int SUCCESS = 3;
    Handler handler;
    //上下文
    Activity context;
    String msg;
    String opText = "确定",neText = "取消",title;
    boolean isTitleVisible = false, isOpVisible = false, isNeVisible = false;
    float Alpha=1.0f;
    int id=-1;

    int color_ok = Color.parseColor("#27BEA7");
    int color_cancle = Color.parseColor("#27BEA7");

    View.OnClickListener opListener,neListener;

    //弹窗按钮组件
    TextView dialog_btn_cancel,dialog_text_msg,dialog_btn_ok,dialog_txt_title;
    LinearLayout background;

    public MDialog(@NonNull Context context) {
        super(context);
        this.context = (Activity) context;
    }
    public void setTitle(String title){
        this.title = title;
        isTitleVisible = true;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public void setOpListener(View.OnClickListener opListener){
        isOpVisible = true;
        this.opListener = opListener;
    }
    public void setNeListener(View.OnClickListener neListener){
        isNeVisible = true;
        this.neListener = neListener;
    }
    public void setOpListener(String opText,View.OnClickListener opListener){
        this.opText = opText;
        isOpVisible = true;
        this.opListener = opListener;
    }
    public void setNeListener(String neText,View.OnClickListener neListener){
        this.neText = neText;
        isNeVisible = true;
        this.neListener = neListener;
    }
    public void setAlpha(float Alpha) {
        this.Alpha = Alpha;
    }
    public void setBackground(int id) {
        this.id = id;
    }
    public void setColor_ok(int color_ok) {
        this.color_ok = color_ok;
    }
    public void setColor_cancle(int color_cancle) {
        this.color_cancle = color_cancle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //指定布局
        setContentView(R.layout.m_dialog);
        dialog_text_msg = findViewById(R.id.dialog_text_msg);
        dialog_btn_ok = findViewById(R.id.dialog_btn_ok);
        dialog_btn_cancel= findViewById(R.id.dialog_btn_cancel);
        dialog_btn_ok.setVisibility(View.GONE);
        dialog_btn_cancel.setVisibility(View.GONE);
        dialog_txt_title = findViewById(R.id.dialog_txt_title);
        dialog_text_msg.setText(msg);
        dialog_btn_ok.setTextColor(color_ok);
        dialog_btn_cancel.setTextColor(color_cancle);
        background = findViewById(R.id.dialog_bk);
        background.setAlpha(Alpha);
        if(id!=-1){
            background.setBackgroundResource(id);
        }
        if (isTitleVisible){
            dialog_txt_title.setVisibility(View.VISIBLE);
            dialog_txt_title.setText(title);
        }
        if (isOpVisible){
            dialog_btn_ok.setVisibility(View.VISIBLE);
            dialog_btn_ok.setText(opText);
        }
        if (isNeVisible){
            dialog_btn_cancel.setVisibility(View.VISIBLE);
            dialog_btn_cancel.setText(neText);
        }

        //获取窗口
        final Window window = this.getWindow();
        WindowManager w = context.getWindowManager();
        //获取屏幕宽、高
        Display d = w.getDefaultDisplay();
        //获取当前对话框参数值
        WindowManager.LayoutParams p = window.getAttributes();
        //设置对话框宽度为屏幕0.8
        //p.height = (int)(d.getHeight()*0.8);
        p.width = (int)(d.getWidth()*0.9);
        window.setAttributes(p);

        //设置是否可以点击空白处取消
        this.setCancelable(true);
        dialog_btn_ok.setOnClickListener(opListener);
        dialog_btn_cancel.setOnClickListener(neListener);
    }

}
