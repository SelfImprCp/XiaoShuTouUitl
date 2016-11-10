package cn.myasapp.main.ui;



import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cp.mylibrary.custom.TitleBarView;
import com.cp.mylibrary.gesture.GestureContentView;
import com.cp.mylibrary.gesture.GestureDrawline;
import com.cp.mylibrary.gesture.LockIndicator;
import com.cp.mylibrary.utils.SharePreferencesUitl;
import com.cp.mylibrary.utils.ShowToastUtil;

import org.kymjs.kjframe.ui.BindView;

import cn.myasapp.main.R;
import cn.myasapp.main.TestUIhelper;

/**
 * 手势密码设置界面
 */
public class GestureLockActivity extends BaseActivity implements OnClickListener {


    private GestureContentView mGestureContentView;


    private boolean mIsFirstInput = true;
    private String mFirstPassword = null;


    @BindView(id = R.id.text_show)
    private TextView mTextReset;

    @BindView(id = R.id.lock_indicator)
    private LockIndicator mLockIndicator;

    @BindView(id = R.id.text_tip)
    private TextView mTextTip;

    @BindView(id = R.id.gesture_container)
    private FrameLayout mGestureContainer;

    @BindView(id = R.id.gesture_title)
    private TitleBarView gesture_title;


    private String gesturePws = "gesturePsw";


    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.draw_psw_canvas_layout);
    }

    @Override
    protected void initView() {
        super.initView();

        gesture_title.setTitleStr("测试手势密码");
        gesture_title.setTitleBackFinshActivity(this);

        String gesturePswStr = (String) SharePreferencesUitl.get(GestureLockActivity.this, gesturePws, "");


        // 已经设置过了，直接进入验证界面
        if (!"".equals(gesturePswStr) || !TextUtils.isEmpty(gesturePswStr)) {


            TestUIhelper.showGestureVerifyActivity(GestureLockActivity.this, gesturePswStr);


        } else {

            setUpViews();
            setUpListeners();

        }

    }


    private void setUpViews() {


        mTextReset.setClickable(false);

        // 初始化一个显示各个点的viewGroup
        mGestureContentView = new GestureContentView(this, false, "", new GestureDrawline.GestureCallBack() {
            @Override
            public void onGestureCodeInput(String inputCode) {
                if (!isInputPassValidate(inputCode)) {
                    mTextTip.setText(Html.fromHtml("<font color='#c70c1e'>最少链接4个点, 请重新输入</font>"));
                    mGestureContentView.clearDrawlineState(0L);
                    return;
                }
                if (mIsFirstInput) {

                    // 第一次设置密码
                    mFirstPassword = inputCode;
                    updateCodeList(inputCode);
                    mGestureContentView.clearDrawlineState(0L);
                    mTextReset.setClickable(true);
                    mTextReset.setText("重新设置手势密码");


                } else {
                    if (inputCode.equals(mFirstPassword)) {
                        // 密码设置成功
                        SharePreferencesUitl.put(GestureLockActivity.this, gesturePws, inputCode);

                        mGestureContentView.clearDrawlineState(0L);


                        ShowToastUtil.showToast(GestureLockActivity.this, "设置成功，密码为：" + inputCode);


                    } else {
                        // 与上次一不同
                        mTextTip.setText(Html.fromHtml("<font color='#c70c1e'>与上一次绘制不一致，请重新绘制</font>"));
                        // 左右移动动画
                        Animation shakeAnimation = AnimationUtils.loadAnimation(GestureLockActivity.this, R.anim.shake);
                        mTextTip.startAnimation(shakeAnimation);
                        // 保持绘制的线，1.5秒后清除
                        mGestureContentView.clearDrawlineState(1500L);
                    }
                }
                mIsFirstInput = false;
            }

            @Override
            public void checkedSuccess() {

            }

            @Override
            public void checkedFail() {

            }
        });
        // 设置手势解锁显示到哪个布局里面
        mGestureContentView.setParentView(mGestureContainer);
        updateCodeList("");
    }

    private void setUpListeners() {

        mTextReset.setOnClickListener(this);
    }

    private void updateCodeList(String inputCode) {
        // 更新选择的图案
        mLockIndicator.setPath(inputCode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.text_show:
                mIsFirstInput = true;
                updateCodeList("");
                mTextTip.setText("绘制解锁图案");
                break;
            default:
                break;
        }
    }

    private boolean isInputPassValidate(String inputPassword) {
        if (TextUtils.isEmpty(inputPassword) || inputPassword.length() < 4) {
            return false;
        }
        return true;
    }

}

