package cn.myasapp.main.ui;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cp.mylibrary.custom.TitleBarView;
import com.cp.mylibrary.gesture.GestureContentView;
import com.cp.mylibrary.gesture.GestureDrawline;
import com.cp.mylibrary.utils.ShowToastUtil;

import org.kymjs.kjframe.ui.BindView;

import cn.myasapp.main.R;

/**
 * 手势绘制/校验界面
 */
public class GestureVerifyActivity extends BaseActivity       {

    private GestureContentView mGestureContentView;


    @BindView(id = R.id.gesture_verify_title)
    private TitleBarView gesture_verify_title;


    @BindView(id = R.id.text_tip)
    private TextView mTextTip;

    @BindView(id = R.id.gesture_container)
    private FrameLayout mGestureContainer;


    private int count = 3;//解锁操作三次

    private String gestureCode;


    public static final String GESTURE = "gesture";


    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.draw_gesture_verify_layout);
    }

    @Override
    protected void initView() {
        super.initView();


        gesture_verify_title.setTitleStr("验证密码锁");
        gesture_verify_title.setTitleBackFinshActivity(this);

        ObtainExtraData();
        setUpViews();

    }


    private void ObtainExtraData() {


        Bundle bundle = getIntent().getExtras();
        gestureCode = bundle.getString(GESTURE);


    }

    private void setUpViews() {


        // 初始化一个显示各个点的viewGroup
        mGestureContentView = new GestureContentView(this, true, gestureCode,
                new GestureDrawline.GestureCallBack() {

                    @Override
                    public void onGestureCodeInput(String inputCode) {

                    }

                    @Override
                    public void checkedSuccess() {
                        if (count > 0) {
                            mGestureContentView.clearDrawlineState(0L);
                            ShowToastUtil.showToast(GestureVerifyActivity.this, "密码正确");

                        } else {
                            showExceedTimes();
                        }
                    }

                    @Override
                    public void checkedFail() {
                        count--;
                        if (count > -1) {
                            mGestureContentView.clearDrawlineState(1300L);
                            mTextTip.setVisibility(View.VISIBLE);
                            mTextTip.setText(Html
                                    .fromHtml("<font color='#c70c1e'>密码错误，还有</font>"
                                            + "<font color='#ffffff'>" + count + "</font>"
                                            + "<font color='#c70c1e'>次机会</font>"));
                            // 左右移动动画
                            Animation shakeAnimation = AnimationUtils.loadAnimation(GestureVerifyActivity.this, R.anim.shake);
                            mTextTip.startAnimation(shakeAnimation);
                        } else {
                            showExceedTimes();
                        }
                    }
                });
        // 设置手势解锁显示到哪个布局里面
        mGestureContentView.setParentView(mGestureContainer);
    }




    private void showExceedTimes() {
        Builder dialog = new Builder(GestureVerifyActivity.this);
        dialog.setTitle("提示")
                .setMessage("解锁超过三次!!!是否找回密码？")
                .setNegativeButton("取消", new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("确定", new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

}
