package com.choryanquan.testaliveforkeepclean

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.choryanquan.testaliveforkeepclean.base.MainActivity

/**
 * Created by Ryan on 2022/12/2 15:08.
 * Function :
 */
object NotifyUtils {


    fun showNotify(context: Context) {
        try {
            
            val pendingIntent = PendingIntent.getActivity(
                context,
                "NotifyUtils".hashCode(),
                Intent(context, MainActivity::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            val notifyLayout =
                RemoteViews(context.packageName, R.layout.item_notify).apply {
                    setOnClickPendingIntent(
                        R.id.test_tv,
                        pendingIntent
                    )
                }


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                createChannel(context)
            }

            val toolNotify = NotificationCompat.Builder(context, "Test Tools")
                .setSmallIcon(R.drawable.ic_logo)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        context.resources,
                        R.drawable.ic_logo
                    )
                )
                .setCustomBigContentView(notifyLayout)
                .setGroupSummary(false)
                .setContent(notifyLayout)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setAutoCancel(false)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setOngoing(true)
                .build()

            toolNotify.flags = toolNotify.flags or Notification.FLAG_NO_CLEAR

            NotificationManagerCompat.from(context).notify("Test Tools", 111111, toolNotify)

        } catch (e: Exception) {
        }
    }


    private fun createChannel(context: Context) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val testsdad =
                    NotificationChannel(
                        "Test Tools",
                        "Test Tools",
                        NotificationManager.IMPORTANCE_MIN
                    ).apply {
                        description = "Test Tools"
                    }
                testsdad.enableLights(false)
                testsdad.setShowBadge(false)
                testsdad.enableVibration(false)
                testsdad.setSound(null, null)
                val dsadada =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                dsadada.createNotificationChannel(testsdad)
            }
        } catch (e: Exception) {
        }
    }


}