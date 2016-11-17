package com.cp.mylibrary.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.cp.mylibrary.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jerry on 2016/11/17.
 */

public class DateTimePickDialogUtil implements DatePicker.OnDateChangedListener,
        TimePicker.OnTimeChangedListener {

    private DatePicker datePicker;
    // private TimePicker timePicker;
    private AlertDialog ad;
    private String dateTime;
    private String initDateTime;
    private String myDialogTitle;
    private Activity activity;

    /**
     * 日期时间弹出选择框构造函数
     *
     * @param activity     ：调用的父activity
     * @param initDateTime 初始日期时间值，作为弹出窗口的标题和日期时间初始值
     *                     对话框显示的标题
     */
    public DateTimePickDialogUtil(Activity activity, String initDateTime, String dialogTitle) {
        this.activity = activity;
        this.initDateTime = initDateTime;
        this.myDialogTitle = dialogTitle;

    }

    /**
     * @param datePicker
     * @param strMinDate"2015-10-22"
     * @param strMaxDate"2016-02-28"
     */
    public void init(DatePicker datePicker, String strMinDate, String strMaxDate) {
        Calendar calendar = Calendar.getInstance();
        if (!(null == initDateTime || "".equals(initDateTime))) {
            calendar = this.getCalendarByInintData(initDateTime);
        } else {
            initDateTime = calendar.get(Calendar.YEAR) + "-"
                    + calendar.get(Calendar.MONTH) + "-"
                    + calendar.get(Calendar.DAY_OF_MONTH) + "-";
//                    + calendar.get(Calendar.HOUR_OF_DAY) + ":"
//                    + calendar.get(Calendar.MINUTE);
        }

        datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), this);
        datePicker.setCalendarViewShown(false);


//        long mindate = System.currentTimeMillis() - 1000L;  //注意：如果不提前一秒的话会报错"java.lang.IllegalArgumentException: fromDate: XXX does not precede toDate: XXX"
//        long maxdate = mindate + 7 * 24 * 3600 * 1000L; //设置DatePicker范围，从今天起之后一周
//        datePicker.setMinDate(mindate);
//        datePicker.setMaxDate(maxdate);
        Date date2 = new DateTimeUtil().strToDate(strMinDate);//最小
        datePicker.setMinDate(date2.getTime());


        Date date = new DateTimeUtil().strToDate(strMaxDate);// 最大
        // long time = date.getTime();
        datePicker.setMaxDate(date.getTime());


//       datePicker.setMinDate(time-10000);
//

//        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
//        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
    }

    /**
     * 弹出日期时间选择框方法
     *
     * @param :为需要设置的日期时间文本编辑框       ]@param strMinDate"2015-10-22"
     * @param strMaxDate"2016-02-28"
     * @return
     */
    public AlertDialog dateTimePicKDialog(String strMinDate, String strMaxDate, String submit, String cancel,
                                          DialogInterface.OnClickListener submitOnClick, DialogInterface.OnClickListener cancelOnClick) {
        LinearLayout dateTimeLayout = (LinearLayout) activity
                .getLayoutInflater().inflate(R.layout.common_datetime, null);
        datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
        //    timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
        init(datePicker, strMinDate, strMaxDate);
//        timePicker.setIs24HourView(true);
//        timePicker.setOnTimeChangedListener(this);

        ad = new AlertDialog.Builder(activity)
                .setTitle(myDialogTitle)
                .setView(dateTimeLayout)
                .setPositiveButton(submit, submitOnClick)
                .setNegativeButton(cancel, cancelOnClick).show();

        onDateChanged(null, 0, 0, 0);
        return ad;
    }

    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        onDateChanged(null, 0, 0, 0);
    }

    public void onDateChanged(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
        // 获得日历实例
        Calendar calendar = Calendar.getInstance();

        calendar.set(datePicker.getYear(), datePicker.getMonth(),
                datePicker.getDayOfMonth());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        dateTime = sdf.format(calendar.getTime());

//        LogCp.i(LogCp.CP, DateTimePickDialogUtil.class + " 这里哪个为nul " + dateTime + ",,"
//                + ad);
        // ad.setTitle(dateTime);
    }

    /**
     * 实现将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒,并赋值给calendar
     *
     * @param initDateTime 初始日期时间值 字符串型
     * @return Calendar
     */
    private Calendar getCalendarByInintData(String initDateTime) {
        Calendar calendar = Calendar.getInstance();

        // 将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒
//        String date = spliteString(initDateTime, "日", "index", "front"); // 日期
//        String time = spliteString(initDateTime, "日", "index", "back"); // 时间
//
//        String yearStr = spliteString(date, "年", "index", "front"); // 年份
//        String monthAndDay = spliteString(date, "年", "index", "back"); // 月日
//
//        String monthStr = spliteString(monthAndDay, "月", "index", "front"); // 月
//        String dayStr = spliteString(monthAndDay, "月", "index", "back"); // 日

//        String hourStr = spliteString(time, ":", "index", "front"); // 时
//        String minuteStr = spliteString(time, ":", "index", "back"); // 分

//        int currentYear = Integer.valueOf(yearStr.trim()).intValue();
//        int currentMonth = Integer.valueOf(monthStr.trim()).intValue() - 1;
//        int currentDay = Integer.valueOf(dayStr.trim()).intValue();
//        int currentHour = Integer.valueOf(hourStr.trim()).intValue();
//        int currentMinute = Integer.valueOf(minuteStr.trim()).intValue();


        Date da = DateTimeUtil.strToDate(initDateTime);

        calendar.set(da.getYear(), da.getMonth(), da.getDay());
        return calendar;
    }

    /**
     * 截取子串
     *
     * @param srcStr      源串
     * @param pattern     匹配模式
     * @param indexOrLast
     * @param frontOrBack
     * @return
     */
    public static String spliteString(String srcStr, String pattern,
                                      String indexOrLast, String frontOrBack) {
        String result = "";
        int loc = -1;
        if (indexOrLast.equalsIgnoreCase("index")) {
            loc = srcStr.indexOf(pattern); // 取得字符串第一次出现的位置
        } else {
            loc = srcStr.lastIndexOf(pattern); // 最后一个匹配串的位置
        }
        if (frontOrBack.equalsIgnoreCase("front")) {
            if (loc != -1)
                result = srcStr.substring(0, loc); // 截取子串
        } else {
            if (loc != -1)
                result = srcStr.substring(loc + 1, srcStr.length()); // 截取子串
        }
        return result;
    }


    public String getSelectDate() {
        return dateTime;

    }


}