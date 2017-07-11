package cn.myasapp.main.ui;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.cp.mylibrary.custom.AutoHorizontalScrollTextView;
import com.cp.mylibrary.custom.AutoVerticalScrollTextView;
import com.cp.mylibrary.custom.TitleBarView;

import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;

import cn.myasapp.main.R;
import cn.myasapp.main.bean.ProvinceBean;

/**
 * Created by Jerry on 2017/7/11.
 */

public class ScrollViewTextActivity extends BaseActivity {


    private int number = 0;
    private boolean isRunning = true;

    private String[] strings = {"我的剑，就是你的剑!", "俺也是从石头里蹦出来得!", "我用双手成就你的梦想!", "人在塔在!", "犯我德邦者，虽远必诛!", "我会让你看看什么叫残忍!", "我的大刀早已饥渴难耐了!"};
    private String string = "我的剑，就是你的剑!   俺也是从石头里蹦出来得!    我用双手成就你的梦想!    人在塔在!    犯我德邦者，虽远必诛!    我会让你看看什么叫残忍!    我的大刀早已饥渴难耐了!";


    @BindView(id = R.id.textview, click = true)
    private AutoHorizontalScrollTextView textview;
    @BindView(id = R.id.textview_auto_roll, click = true)
    private AutoVerticalScrollTextView textview_auto_roll;


    @Override
    public void initWidget() {
        super.initWidget();

        textview.setText(string);


        textview_auto_roll.setText(strings[0]);

        new Thread() {
            @Override
            public void run() {
                while (isRunning) {
                    SystemClock.sleep(3000);
                    handler.sendEmptyMessage(199);
                }
            }
        }.start();

    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 199) {
                textview_auto_roll.next();
                number++;
                textview_auto_roll.setText(strings[number % strings.length]);
            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }

    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_scrolview_text);
    }


}
