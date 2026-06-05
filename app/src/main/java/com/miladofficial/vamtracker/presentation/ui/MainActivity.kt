/**
 * فایل اصلی اپلیکیشن - MainActivity
 * مدیریت ناویگیشن و تنظیم اولیه
 */

package com.miladofficial.vamtracker.presentation.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.miladofficial.vamtracker.presentation.ui.screens.DashboardScreen
import com.miladofficial.vamtracker.presentation.ui.screens.VamListScreen
import com.miladofficial.vamtracker.presentation.ui.screens.AddEditVamScreen
import com.miladofficial.vamtracker.presentation.ui.screens.VamDetailsScreen
import com.miladofficial.vamtracker.presentation.ui.screens.CalendarScreen
import com.miladofficial.vamtracker.presentation.ui.screens.ReportsScreen
import com.miladofficial.vamtracker.presentation.ui.screens.SettingsScreen
import com.miladofficial.vamtracker.presentation.ui.theme.VamTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * فعالیت اصلی اپلیکیشن
 * 
 * این کلاس مسئول:
 * - تنظیم صفحه splash اولیه
 * - مدیریت ناویگیشن بین صفحات
 * - تنظیم تم و استایل‌های کلی
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // تنظیم splash screen
        installSplashScreen()

        setContent {
            VamTrackerTheme {
                // صفحه اصلی
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    // کنترل ناویگیشن
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "dashboard"
                    ) {
                        // صفحه داشبورد
                        composable("dashboard") {
                            DashboardScreen(navController)
                        }

                        // صفحه لیست وام‌ها
                        composable("vam_list") {
                            VamListScreen(navController)
                        }

                        // صفحه افزودن/ویرایش وام
                        composable("add_edit_vam/{vamId}") { backStackEntry ->
                            val vamId = backStackEntry.arguments?.getString("vamId")?.toLongOrNull()
                            AddEditVamScreen(navController, vamId)
                        }

                        // صفحه جزئیات وام
                        composable("vam_details/{vamId}") { backStackEntry ->
                            val vamId = backStackEntry.arguments?.getString("vamId")?.toLong()
                            if (vamId != null) {
                                VamDetailsScreen(navController, vamId)
                            }
                        }

                        // صفحه تقویم
                        composable("calendar") {
                            CalendarScreen(navController)
                        }

                        // صفحه گزارشات
                        composable("reports") {
                            ReportsScreen(navController)
                        }

                        // صفحه تنظیمات
                        composable("settings") {
                            SettingsScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
