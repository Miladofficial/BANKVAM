/**
 * مدل‌های داده پیش‌نیاز
 */

package com.miladofficial.vamtracker.domain.model

/**
 * لیست بانک‌ها با نرخ سود پیش‌فرض
 */
object BankData {
    val banks = listOf(
        Bank("بانک ملی ایران", 18.0),
        Bank("بانک ملت", 18.0),
        Bank("بانک صادرات ایران", 18.0),
        Bank("بانک تجارت", 18.0),
        Bank("بانک رفاه کارگران", 18.0),
        Bank("بانک مسکن", 13.5),
        Bank("بانک کشاورزی", 13.0),
        Bank("بانک سپه", 18.0),
        Bank("بانک آینده", 20.0),
        Bank("بانک پاسارگاد", 20.0),
        Bank("بانک اقتصاد نوین", 20.0),
        Bank("بانک سامان", 20.0),
        Bank("بانک سینا", 20.0),
        Bank("بانک شهر", 20.0),
        Bank("بانک دی", 20.0),
        Bank("قرض‌الحسنه مهر ایران", 4.0),
        Bank("قرض‌الحسنه رسالت", 4.0),
        Bank("موسسه اعتباری ملل", 22.0),
        Bank("موسسه اعتباری نور", 22.0),
        Bank("سایر", 18.0)
    )
}

/**
 * مدل بانک
 */
data class Bank(
    val name: String,
    val defaultRate: Double
)

/**
 * انواع وام
 */
object LoanTypeData {
    val types = listOf(
        "قرض‌الحسنه",
        "ازدواج",
        "فرزندآوری",
        "مسکن",
        "خودرو",
        "لوازم خانگی",
        "کسب و کار",
        "وام حقوق",
        "سهام عدالت",
        "دیه",
        "مرابحه",
        "مشارکت مدنی",
        "اجاره به شرط تملیک",
        "جعاله",
        "سایر"
    )
}

/**
 * نمودار و آمار
 */
data class VamStatistics(
    val totalDebt: Long,
    val monthlyPayment: Long,
    val totalInterest: Long,
    val activeVamCount: Int
)
