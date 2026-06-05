/**
 * کلاس محاسبات وام
 * 
 * این کلاس تمام محاسبات مربوط به وام‌ها را انجام می‌دهد:
 * - محاسبه قسط ماهانه
 * - تولید جدول اقساط
 * - محاسبه مانده بدهی
 * - محاسبه تسویه زودهنگام
 */

package com.miladofficial.vamtracker.domain.usecase

import kotlin.math.pow

/**
 * مدل داده برای یک قسط
 */
data class Qest(
    val qestNo: Int,              // شماره قسط
    val dueDate: String,          // تاریخ سررسید (شمسی)
    val totalAmount: Long,        // مبلغ کل قسط (اصل + سود)
    val principalAmount: Long,    // سهم اصل
    val interestAmount: Long,     // سهم سود
    val remainingDebt: Long,      // مانده بدهی
    val isPaid: Boolean = false   // وضعیت پرداخت
)

/**
 * کلاس محاسبه وام
 * 
 * فرمول استفاده شده برای محاسبه قسط مساوی:
 * M = P × r(1+r)^n / ((1+r)^n - 1)
 * 
 * @param principalAmount مبلغ اصل وام
 * @param annualRate نرخ سود سالانه (درصد)
 * @param durationMonths مدت بازپرداخت (ماه)
 */
class MohasebeVam(
    private val principalAmount: Long,
    private val annualRate: Double,
    private val durationMonths: Int
) {

    /**
     * محاسبه قسط ماهانه
     * 
     * @return قسط ماهانه به ریال
     */
    fun calculateMonthlyPayment(): Long {
        // تبدیل نرخ سالانه به ماهانه
        val monthlyRate = annualRate / 100 / 12

        // اگر نرخ صفر باشد (وام بدون سود)
        if (monthlyRate == 0.0) {
            return principalAmount / durationMonths
        }

        // فرمول: M = P × r(1+r)^n / ((1+r)^n - 1)
        val numerator = principalAmount * monthlyRate * (1 + monthlyRate).pow(durationMonths.toDouble())
        val denominator = (1 + monthlyRate).pow(durationMonths.toDouble()) - 1

        return (numerator / denominator).toLong()
    }

    /**
     * تولید جدول کامل اقساط
     * 
     * @param disbursementDate تاریخ دریافت وام (شمسی)
     * @param paidInstallments تعداد اقساط پرداخت‌شده
     * @return لیست اقساط
     */
    fun generateAmortizationTable(
        disbursementDate: String,
        paidInstallments: Int = 0
    ): List<Qest> {
        val qestList = mutableListOf<Qest>()
        val monthlyRate = annualRate / 100 / 12
        val monthlyPayment = calculateMonthlyPayment()

        var remainingDebt = principalAmount

        // محاسبه هر قسط
        for (i in 1..durationMonths) {
            // محاسبه سهم سود
            val interestAmount = (remainingDebt * monthlyRate).toLong()

            // محاسبه سهم اصل
            val principalPaid = monthlyPayment - interestAmount

            // محاسبه مانده بدهی برای قسط بعدی
            remainingDebt -= principalPaid

            // اگر آخرین قسط است، مانده را به صفر تنظیم کنید
            if (i == durationMonths) {
                remainingDebt = 0
            }

            // تاریخ سررسید (ماه i پس از دریافت)
            val dueDate = addMonthsToDate(disbursementDate, i)

            // وضعیت پرداخت
            val isPaid = i <= paidInstallments

            qestList.add(
                Qest(
                    qestNo = i,
                    dueDate = dueDate,
                    totalAmount = monthlyPayment,
                    principalAmount = principalPaid,
                    interestAmount = interestAmount,
                    remainingDebt = maxOf(remainingDebt, 0),
                    isPaid = isPaid
                )
            )
        }

        return qestList
    }

    /**
     * محاسبه مانده بدهی فعلی
     * 
     * @param paidInstallments تعداد اقساط پرداخت‌شده
     * @return مانده بدهی
     */
    fun calculateRemainingDebt(paidInstallments: Int): Long {
        val monthlyRate = annualRate / 100 / 12
        val monthlyPayment = calculateMonthlyPayment()

        var remainingDebt = principalAmount

        for (i in 1..paidInstallments) {
            val interestAmount = (remainingDebt * monthlyRate).toLong()
            val principalPaid = monthlyPayment - interestAmount
            remainingDebt -= principalPaid
        }

        return maxOf(remainingDebt, 0)
    }

    /**
     * محاسبه تسویه زودهنگام
     * 
     * @param paidInstallments تعداد اقساط پرداخت‌شده
     * @return مبلغ تسویه
     */
    fun calculateEarlySettlement(paidInstallments: Int): Long {
        val monthlyRate = annualRate / 100 / 12
        val monthlyPayment = calculateMonthlyPayment()
        val remainingInstallments = durationMonths - paidInstallments

        var remainingDebt = calculateRemainingDebt(paidInstallments)
        var settlement = remainingDebt

        // محاسبه بهره‌های آینده
        for (i in 1..remainingInstallments) {
            val futureInterest = (remainingDebt * monthlyRate * 0.5).toLong() // ۵۰ درصد تخفیف
            settlement += futureInterest
            remainingDebt -= (monthlyPayment - futureInterest)
        }

        return settlement
    }

    /**
     * محاسبه کل سود پرداختی
     * 
     * @return کل سود
     */
    fun calculateTotalInterest(): Long {
        val monthlyPayment = calculateMonthlyPayment()
        return (monthlyPayment * durationMonths) - principalAmount
    }

    /**
     * محاسبه درصد پیشرفت بازپرداخت
     * 
     * @param paidInstallments تعداد اقساط پرداخت‌شده
     * @return درصد پیشرفت (۰-۱۰۰)
     */
    fun calculateProgressPercentage(paidInstallments: Int): Int {
        return ((paidInstallments.toFloat() / durationMonths) * 100).toInt()
    }

    /**
     * اضافه کردن ماه‌ها به تاریخ (کمکی)
     */
    private fun addMonthsToDate(baseDate: String, months: Int): String {
        // این تابع باید با کتابخانه PersianDate پیاده‌سازی شود
        return baseDate // فعلاً جایگزین
    }
}
