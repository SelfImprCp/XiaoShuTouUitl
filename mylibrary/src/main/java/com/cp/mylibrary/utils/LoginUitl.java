package com.cp.mylibrary.utils;

import android.content.Context;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;

import com.cp.mylibrary.R;

/**
 * Created by Jerry on 2016/10/14.
 */
public class LoginUitl {


    private boolean showOrHidePassWord(boolean passWordIsShow, EditText register_input_password) {


        if (passWordIsShow) {
            //设置EditText文本为可见的
            register_input_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            //设置EditText文本为隐藏的
            register_input_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        passWordIsShow = !passWordIsShow;
        register_input_password.postInvalidate();
        //切换后将EditText光标置于末尾
        CharSequence charSequence = register_input_password.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }


        return passWordIsShow;


    }


    /**
     * 是否可以去登录,
     *
     * @param
     */
    public static boolean checkLogin(Context context, EditText uMobile, EditText uPassWord) {

        if (!NetWorkUtil.hasInternetConnected(context)) {
            ShowToastUtil.showToastShort(context, R.string.tip_network_error);
            return false;
        }

        if (!checkLoginName(context, uMobile)) {

            return false;
        }

        if (!checkLoginPass(context, uPassWord)) {

            return false;
        }
        return true;
    }


    /**
     * 传入EditTExt 不能为null
     * 如果合法,返回true
     * 如果不合法,会有提示,并获取焦点
     * 验证手机号
     *
     * @param mEtName
     * @return
     */


    public static boolean checkLoginName(Context context, EditText mEtName) {

        String str = mEtName.getText().toString();
        if (StringUtils.isEmpty(str)) {
            ShowToastUtil.showToast(context, R.string.please_name);

            mEtName.requestFocus();
            return false;
        } else {

            if (StringUtils.isPhoneValid(str)) {
                return true;
            } else {
                ShowToastUtil.showToast(context, R.string.please_correct_phone);
                mEtName.requestFocus();
                return false;
            }


        }

    }


    /**
     * 验证密码
     * <p>
     * 根据不同业务需要写判断
     *
     * @param
     * @return
     */

    public static boolean checkLoginPass(Context context, EditText mEtName) {
        String str = mEtName.getText().toString();

        if (StringUtils.isEmpty(str)) {
            ShowToastUtil.showToast(context, R.string.please_password);
            mEtName.requestFocus();
            return false;
        } else if (str.length() < 6 || str.length() > 20) {
            ShowToastUtil.showToast(context, "密码为6-20位的数字或字母");
            mEtName.requestFocus();
            return false;
        } else {
            return true;

        }


    }


}
