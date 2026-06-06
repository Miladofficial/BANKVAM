package com.miladofficial.vamtracker.domain.usecase

import com.miladofficial.vamtracker.domain.model.VamModel
import kotlin.math.pow

class MohasebeVam {

    // محاسبه قسط ماهانه (فرمول وام چسبانده)
    fun calculateMonthlyPayment(vam: VamModel): Double {
        val P = vam.mablaghKol.toDouble()
        val monthlyRate = (vam.nerkhSoodSalane / 12) / 100
        val n = vam.tedadAghsat

        if (monthlyRate == 0.0) return P / n

        val numerator = P * monthlyRate * (1 + monthlyRate).pow(n)
        val denominator = (1 + monthlyRate).pow(n) - 1

        return numerator / denominator
    }

    // محاسبه سود هر قسط
    fun calculateInterestPerInstallment(vam: VamModel): Double {
        val monthlyPayment = calculateMonthlyPayment(vam)
        val monthlyRate = (vam.nerkhSoodSalane / 12) / 100
        val remainingBalance = vam.mablaghKol.toDouble()

        return remainingBalance * monthlyRate
    }

    // محاسبه مانده باقی‌مانده
    fun calculateRemainingBalance(vam: VamModel): Double {
        val monthlyPayment = calculateMonthlyPayment(vam)
        val paidCount = vam.maghazeAghsat
        val totalPaid = monthlyPayment * paidCount
        return vam.mablaghKol - totalPaid
    }

    // گرفتن وضعیت وام (جاری/معوق/تکمیل شده)
    fun getVamStatus(vam: VamModel, currentInstallmentNumber: Int): String {
        return when {
            currentInstallmentNumber > vam.tedadAghsat -> "تکمیل شده"
            currentInstallmentNumber > vam.maghazeAghsat + 3 -> "معوق"
            else -> "جاری"
        }
    }
}