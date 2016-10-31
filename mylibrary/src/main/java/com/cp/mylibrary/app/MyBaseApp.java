package com.cp.mylibrary.app;

import android.app.Application;

import com.cp.mylibrary.utils.ActivityManagerUtil;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by Jerry on 2016/7/6.
 */
public class MyBaseApp  extends Application {
    private static MyBaseApp context;
    private static ActivityManagerUtil activityManager = null;




    {
        //在application文件中配置三方平台的appkey：
        PlatformConfig.setWeixin(Config.WEICHAT_APPID,Config.WEICHAT_SECRET);
          //豆瓣RENREN平台目前只能在服务器端配置
        //新浪微博
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
//        //易信
//        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
//        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
//        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
//        PlatformConfig.setAlipay("2015111700822536");
//        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
//        PlatformConfig.setPinterest("1439206");

    }


    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        activityManager = ActivityManagerUtil.getInstance();


         //application中初始化sdk，这个初始化最好放在application的程序入口中，防止意外发生：

        UMShareAPI.get(this);

        //如果您使用了新浪微博，需要在这里设置回调地址：
        com.umeng.socialize.Config.REDIRECT_URL = "";

    }

    /**
     * 获得当前app运行的AppContext
     *
     * @return
     */
    public static MyBaseApp getInstance() {
        return context;
    }


    /**
     *
     * @return
     */
    public ActivityManagerUtil getActivityManager() {
        return activityManager;
    }

    public static void exit() {
        activityManager.finishAllActivity();
    }



}
