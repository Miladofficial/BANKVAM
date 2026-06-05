/**
 * موجودیت دیتابیس برای وام‌ها
 * 
 * این کلاس نمایندگی یک وام در دیتابیس است
 * تمام اطلاعات مربوط به هر وام در این کلاس ذخیره می‌شود
 */

package com.miladofficial.vamtracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * @param id شناسه منحصر به فرد وام
 * @param bankName نام بانک یا موسسه اعتباری
 * @param loanType نوع وام (قرض‌الحسنه، ازدواج، مسکن، خودرو و...)
 * @param principalAmount مبلغ اصل وام به ریال
 * @param annualRate نرخ سود سالانه (درصد)
 * @param disbursementDate تاریخ دریافت وام (شمسی)
 * @param durationMonths مدت بازپرداخت به ماه
 * @param monthlyPayment مبلغ قسط ماهانه به ریال
 * @param paidInstallments تعداد اقساط پرداخت‌شده
 * @param contractNumber شماره قرارداد
 * @param branchName نام شعبه بانک
 * @param collateral نوع وثیقه یا ضامن
 * @param notes یادداشت‌های اضافی
 * @param isActive وضعیت فعال بودن وام
 * @param createdAt تاریخ ثبت وام
 */
@Entity(tableName = "vam_table")
data class VamEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val bankName: String,
    val loanType: String,
    val principalAmount: Long,
    val annualRate: Double,
    val disbursementDate: String,
    val durationMonths: Int,
    val monthlyPayment: Long,
    val paidInstallments: Int = 0,
    val contractNumber: String = "",
    val branchName: String = "",
    val collateral: String = "",
    val notes: String = "",
    val isActive: Boolean = true,
    val createdAt: String = ""
) : Serializable
