package com.jimmy.revive.sample.notify

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.JobIntentService
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.jimmy.revive.R
import com.jimmy.revive.lib.framework.util.w
import com.jimmy.revive.sample.MainActivity

/**
 * Created by Jimmy on 2019/10/28.
 */
class NotifyJobIntentService : JobIntentService() {

    companion object {
        private const val JOB_ID = 1000

        @JvmStatic
        fun enqueueWork(ctx: Context, intent: Intent) {
            w("NotifyJobIntentService enqueueWork")
            enqueueWork(ctx, NotifyJobIntentService::class.java, JOB_ID, intent)
        }
    }

    override fun onHandleWork(intent: Intent) {
        w("NotifyJobIntentService onHandleWork")
        sendBroadcast(this)
        stopSelf()
    }

    private fun sendBroadcast(context: Context) {
        w("NotifyJobIntentService sendBroadcast")
        val builder: NotificationCompat.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = NotificationCompat.Builder(context, PracticeCountNotifyReceiver.NOTIFY_ID)
        } else {
            builder = NotificationCompat.Builder(context)
        }
        val startIntent = Intent(context, MainActivity::class.java)
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val pendingIntent = PendingIntent.getActivity(context, 1, startIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(pendingIntent)
        builder.setAutoCancel(true)
        builder.setContentTitle("快回来做题啦！")
        builder.setContentText("哟 我可以显示了")
        builder.setWhen(System.currentTimeMillis())
        builder.setSmallIcon(R.drawable.ic_launcher_background)
        builder.setDefaults(NotificationCompat.DEFAULT_LIGHTS or NotificationCompat.DEFAULT_SOUND)
        NotificationManagerCompat.from(context).notify(1, builder.build())
    }

}