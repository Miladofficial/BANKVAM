/**
 * رابط DAO برای عملیات دیتابیس وام‌ها
 */

package com.miladofficial.vamtracker.data.local.dao

import androidx.room.*
import com.miladofficial.vamtracker.data.local.entity.VamEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object برای VamEntity
 * 
 * تمام عملیات دیتابیسی مربوط به وام‌ها از طریق این رابط انجام می‌شود
 */
@Dao
interface VamDao {

    /**
     * درج وام جدید
     */
    @Insert
    suspend fun insertVam(vam: VamEntity): Long

    /**
     * بروزرسانی وام
     */
    @Update
    suspend fun updateVam(vam: VamEntity)

    /**
     * حذف وام
     */
    @Delete
    suspend fun deleteVam(vam: VamEntity)

    /**
     * گرفتن تمام وام‌های فعال
     */
    @Query("SELECT * FROM vam_table WHERE isActive = 1 ORDER BY createdAt DESC")
    fun getAllActiveVams(): Flow<List<VamEntity>>

    /**
     * گرفتن تمام وام‌ها (فعال و غیرفعال)
     */
    @Query("SELECT * FROM vam_table ORDER BY createdAt DESC")
    fun getAllVams(): Flow<List<VamEntity>>

    /**
     * گرفتن وام بر اساس شناسه
     */
    @Query("SELECT * FROM vam_table WHERE id = :vamId")
    fun getVamById(vamId: Long): Flow<VamEntity?>

    /**
     * جستجو در وام‌ها
     */
    @Query("SELECT * FROM vam_table WHERE bankName LIKE :query OR contractNumber LIKE :query ORDER BY createdAt DESC")
    fun searchVams(query: String): Flow<List<VamEntity>>

    /**
     * فیلتر بر اساس بانک
     */
    @Query("SELECT * FROM vam_table WHERE bankName = :bankName AND isActive = 1 ORDER BY createdAt DESC")
    fun getVamsByBank(bankName: String): Flow<List<VamEntity>>

    /**
     * فیلتر بر اساس نوع وام
     */
    @Query("SELECT * FROM vam_table WHERE loanType = :loanType AND isActive = 1 ORDER BY createdAt DESC")
    fun getVamsByType(loanType: String): Flow<List<VamEntity>>

    /**
     * محاسبه مجموع بدهی فعال
     */
    @Query("""
        SELECT SUM(principalAmount - (monthlyPayment * paidInstallments) + 
        (monthlyPayment * paidInstallments * annualRate / 100 / 12))
        FROM vam_table WHERE isActive = 1
    """)
    fun getTotalActiveDebt(): Flow<Long>

    /**
     * محاسبه مجموع اقساط این ماه
     */
    @Query("SELECT SUM(monthlyPayment) FROM vam_table WHERE isActive = 1")
    fun getTotalMonthlyPayment(): Flow<Long>

    /**
     * محاسبه کل سود پرداختی
     */
    @Query("""
        SELECT SUM((monthlyPayment * durationMonths) - principalAmount)
        FROM vam_table WHERE isActive = 1
    """)
    fun getTotalPaidInterest(): Flow<Long>

    /**
     * گرفتن تعداد وام‌های فعال
     */
    @Query("SELECT COUNT(*) FROM vam_table WHERE isActive = 1")
    fun getActiveVamCount(): Flow<Int>

    /**
     * دریافت گروه‌بندی وام‌ها بر اساس بانک
     */
    @Query("""
        SELECT bankName, SUM(principalAmount) as totalAmount
        FROM vam_table WHERE isActive = 1
        GROUP BY bankName
    """)
    fun getVamsByBankGrouped(): Flow<List<BankVamSummary>>
}

/**
 * مدل داده برای خلاصه وام‌ها بر اساس بانک
 */
data class BankVamSummary(
    val bankName: String,
    val totalAmount: Long
)
