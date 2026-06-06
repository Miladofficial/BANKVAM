package com.miladofficial.vamtracker.data.repository

import com.miladofficial.vamtracker.data.local.entity.VamEntity
import com.miladofficial.vamtracker.domain.model.VamModel
import kotlinx.coroutines.flow.Flow

interface VamRepository {
    fun getAllVam(): Flow<List<VamEntity>>
    suspend fun getVamById(id: Long): VamEntity?
    suspend fun insertVam(vam: VamModel)
    suspend fun updateVam(vam: VamModel)
    suspend fun deleteVam(vam: VamEntity)
    suspend fun deleteAll()
    fun getTotalMablagh(): Flow<Long?>
    fun getCountVam(): Flow<Int>
}