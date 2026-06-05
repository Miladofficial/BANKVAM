/**
 * کلاس یادآوری وام‌ها
 * 
 * این کلاس مسئول فرستادن اعلان‌های هوشمند برای اقساط است
 * کار می‌کند با WorkManager و هر روز بررسی می‌کند
 */

package com.miladofficial.vamtracker.data.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.miladofficial.vamtracker.R
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Worker برای یادآوری اقساط
 * 
 * این کلاس ہر روز اجرا می‌شود و بررسی می‌کند:
 * - اقساط ۷ روز مانده (زرد)
 * - اقساط ۳ روز مانده (نارنجی)
 * - اقساط امروز (قرمز)
 * - اقساط معوق (قرمز فوری)
 */
class KargarYadavari @Inject constructor(
    @ApplicationContext private val context: Context
) : CoroutineWorker(context, params = WorkerParameters()) {

    /**
     * اجرای کار (هر روز)
     */
    override suspend fun doWork(): Result {
        return try {
            // بررسی و فرستادن اعلان‌ها
            checkAndSendNotifications()
            Result.success()
        } catch (e: Exception) {
            // اگر خطا داشت، دوباره تلاش کنید
            Result.retry()
        }
    }

    /**
     * بررسی اقساط و فرستادن اعلان‌های مناسب
     */
    private suspend fun checkAndSendNotifications() {
        val today = getTodayInPersian()

        // 1. اقساط معوق (قرمز فوری)
        sendNotification(
            title = "⚠️ اقساط معوق",
            message = "شما اقساط معوق دارید. لطفاً هر چه سریع‌تر پرداخت کنید!",
            color = 0xFFD32F2F.toInt(), // قرمز
            priority = NotificationCompat.PRIORITY_MAX
        )

        // 2. اقساط امروز (قرمز)
        sendNotification(
            title = "🔴 اقساط امروز",
            message = "امروز اقساطی دارید که باید پرداخت کنید",
            color = 0xFFD32F2F.toInt(), // قرمز
            priority = NotificationCompat.PRIORITY_HIGH
        )

        // 3. اقساط ۳ روز مانده (نارنجی)
        sendNotification(
            title = "🟠 اقساط در ۳ روز دیگر",
            message = "۳ روز تا سررسید اقساط بعدی",
            color = 0xFFF57C00.toInt(), // نارنجی
            priority = NotificationCompat.PRIORITY_DEFAULT
        )

        // 4. اقساط ۷ روز مانده (زرد)
        sendNotification(
            title = "🟡 اقساط در ۷ روز دیگر",
            message = "۷ روز تا سررسید اقساط بعدی - برای پیش‌بینی بودجه",
            color = 0xFFFFC107.toInt(), // زرد
            priority = NotificationCompat.PRIORITY_LOW
        )
    }

    /**
     * فرستادن اعلان
     */
    private fun sendNotification(
        title: String,
        message: String,
        color: Int,
        priority: Int
    ) {
        val channelId = "vam_reminder_channel"
        val notificationId = System.currentTimeMillis().toInt()

        // ایجاد کانال (برای اندروید ۸+)
        val channel = NotificationChannel(
            channelId,
            "یادآوری اقساط",
            NotificationManager.IMPORTANCE_HIGH
        )

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        // ایجاد اعلان
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notification) // آیکن اعلان
            .setContentTitle(title)
            .setContentText(message)
            .setColor(color)
            .setPriority(priority)
            .setAutoCancel(true)
            .build()

        // فرستادن اعلان
        notificationManager.notify(notificationId, notification)
    }

    /**
     * کمک: گرفتن تاریخ امروز (شمسی)
     */
    private fun getTodayInPersian(): String {
        // این تابع باید با PersianDate پیاده‌سازی شود
        return "" // فعلاً جایگزین
    }

    companion object {
        /**
         * برنامه‌ریزی یادآوری روزانه
         */
        fun scheduleReminder(context: Context) {
            val reminderWork = PeriodicWorkRequestBuilder<KargarYadavari>(
                repeatInterval = 1,
                repeatIntervalTimeUnit = TimeUnit.DAYS
            ).build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "vam_reminder",
                androidx.work.ExistingPeriodicWorkPolicy.KEEP,
                reminderWork
            )
        }
    }
}
