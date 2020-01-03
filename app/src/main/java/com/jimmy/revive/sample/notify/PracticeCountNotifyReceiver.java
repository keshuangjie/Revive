package com.jimmy.revive.sample.notify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jimmy.revive.lib.framework.util.LogUtilKt;

/**
 * PracticeCountNotifyReceiver
 * Created by gaoyang on 16/8/25.
 */

public class PracticeCountNotifyReceiver extends BroadcastReceiver {

    public static final String NOTIFY_ID = "core__push_channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtilKt.w("PracticeCountNotifyReceiver onReceive action: " + intent.getAction());
        NotifyJobIntentService.enqueueWork(context, intent);
    }

    private String getNotifyContent() {
        return "哟 我可以显示了";
    }

}
