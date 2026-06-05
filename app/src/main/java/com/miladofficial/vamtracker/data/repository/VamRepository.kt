/**
 * رابط Repository برای وام‌ها
 */

package com.miladofficial.vamtracker.data.repository

import com.miladofficial.vamtracker.data.local.dao.BankVamSummary
import com.miladofficial.vamtracker.data.local.entity.VamEntity
import kotlinx.coroutines.flow.Flow

/**
 * رابط Repository
 */
interface VamRepository {
    fun getAllActiveVams(): Flow<List<VamEntity>>
    fun getAllVams(): Flow<List<VamEntity>>
    fun getVamById(vamId: Long): Flow<VamEntity?>
    fun searchVams(query: String): Flow<List<VamEntity>>
    fun getVamsByBank(bankName: String): Flow<List<VamEntity>>
    fun getVamsByType(loanType: String): Flow<List<VamEntity>>
    fun getTotalActiveDebt(): Flow<Long>
    fun getTotalMonthlyPayment(): Flow<Long>
    fun getTotalPaidInterest(): Flow<Long>
    fun getActiveVamCount(): Flow<Int>
    fun getVamsByBankGrouped(): Flow<List<BankVamSummary>>
    suspend fun insertVam(vam: VamEntity): Long
    suspend fun updateVam(vam: VamEntity)
    suspend fun deleteVam(vam: VamEntity)
}
