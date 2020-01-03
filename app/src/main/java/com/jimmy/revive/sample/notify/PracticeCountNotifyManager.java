package com.jimmy.revive.sample.notify;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;


import com.jimmy.revive.lib.base.ReviveContext;
import com.jimmy.revive.lib.framework.util.LogUtilKt;
import com.jimmy.revive.lib.framework.util.PreUtils;

import java.util.Calendar;

/**
 * PracticeCountNotifyManager
 * Created by gaoyang on 16/8/25.
 */

public class PracticeCountNotifyManager {

    private static final String FILE_SHARED_PRE = "jiakao_practice_notify_manager";
    private static final String KEY_PRACTICE_NOTIFY_TIME = "key_practice_notify_time";

    private PracticeCountNotifyManager() {
    }

    /**
     * 设定提醒闹钟,需要在MainActivity启动的时候需要调用
     */
    public static void triggerNextNotify() {
        triggerNextNotify0();
    }

    private static void triggerNextNotify0() {
        LogUtilKt.w("PracticeCountNotifyManager triggerNextNotify0");
        new Thread(new Runnable() {
            @Override
            public void run() {
                long lastSettingTime = PreUtils.getLong(KEY_PRACTICE_NOTIFY_TIME, 0);
                long currentTime = System.currentTimeMillis();

//                if (currentTime < lastSettingTime) {
//                    return;
//                }

                /**
                 * 避免同一时刻请求峰值，小时、分钟、秒随机
                 * 小时区间[08~20]
                 * 分钟区间[00~59]
                 * 秒区间[00~59]
                 */
                int hour = 12;
                int minute = 37;
                int second = 0;

                long wednesdayHalfDayTime;
                long sundayHalfDayTime;
                Calendar tempCalendar = Calendar.getInstance();

                //先计算本周三该时间点毫秒数
                tempCalendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                tempCalendar.set(Calendar.HOUR_OF_DAY, hour);
                tempCalendar.set(Calendar.MINUTE, minute);
                tempCalendar.set(Calendar.SECOND, second);
                tempCalendar.set(Calendar.MILLISECOND, 0);
                wednesdayHalfDayTime = tempCalendar.getTimeInMillis();

                //计算本周日该时间点毫秒数
                tempCalendar.add(Calendar.WEEK_OF_YEAR, 1);
                tempCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                tempCalendar.set(Calendar.HOUR_OF_DAY, hour);
                tempCalendar.set(Calendar.MINUTE, minute);
                tempCalendar.set(Calendar.SECOND, second);
                tempCalendar.set(Calendar.MILLISECOND, 0);
                sundayHalfDayTime = tempCalendar.getTimeInMillis();

                int nextDay;
                Calendar calendar = Calendar.getInstance();
                //如果是周三之前,则设置本周三的闹钟
                if (currentTime < wednesdayHalfDayTime) {
                    calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                    nextDay = Calendar.WEDNESDAY;
                } else if (currentTime < sundayHalfDayTime) {
                    //如果当前是周三之后,并且在本周日12点之前,则设置周日的闹钟,因为Calendar的星期第一天是周日,所以得获取下周的周日
                    calendar.add(Calendar.WEEK_OF_YEAR, 1);
                    calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                    nextDay = Calendar.SUNDAY;
                } else {
                    // 如果当前是周日之后,则在设置下周三的闹钟
                    calendar.add(Calendar.WEEK_OF_YEAR, 1);
                    calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                    nextDay = Calendar.WEDNESDAY;
                }
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, second);
                calendar.set(Calendar.MILLISECOND, 0);

                // 下一次启动广播的时间
                long finalTimeInMillis = calendar.getTimeInMillis();

//                // 如果下一次启动与上一次启动小于1天，直接返回
//                if (finalTimeInMillis - lastSettingTime < 24 * 60 * 60 * 1000) {
//                    return;
//                }

                PreUtils.setLong(KEY_PRACTICE_NOTIFY_TIME, finalTimeInMillis);
                Intent intent = new Intent();
                intent.setAction(String.valueOf(nextDay));
                intent.setClass(ReviveContext.context, PracticeCountNotifyReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(ReviveContext.context, nextDay, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager manager = (AlarmManager) ReviveContext.context.getSystemService(Context.ALARM_SERVICE);
                manager.set(AlarmManager.RTC, finalTimeInMillis, pendingIntent);
            }
        }).start();
    }
}
