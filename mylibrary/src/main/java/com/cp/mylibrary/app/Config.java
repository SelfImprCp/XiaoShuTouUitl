package com.cp.mylibrary.app;

import android.os.Environment;

import java.io.File;

/**
 * Created by Jerry on 2016/8/17.
 */
public class Config {
     public static int PAGE_SIXE = 20;

     public static final String WEICHAT_APPID = "wx6a54a8cecdce38e9";
     public static final String WEICHAT_SECRET = "9f67580ae92be8fe3260e0c9b06eff09";

     // 默认存放文件下载的路径
     public final static String DEFAULT_SAVE_FILE_PATH = Environment
             .getExternalStorageDirectory()
             + File.separator
             + "xiaoshutou"
             + File.separator + "download" + File.separator;
}
