package com.miladofficial.vamtracker.data.repository

import com.miladofficial.vamtracker.data.local.dao.VamDao
import com.miladofficial.vamtracker.data.local.entity.VamEntity
import com.miladofficial.vamtracker.domain.model.VamModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VamRepositoryImpl @Inject constructor(
    private val vamDao: VamDao
) : VamRepository {

    override fun getAllVam(): Flow<List<VamEntity>> = vamDao.getAllVam()

    override suspend fun getVamById(id: Long): VamEntity? = vamDao.getVamById(id)

    override suspend fun insertVam(vam: VamModel) {
        val entity = VamEntity(
            bankName = vam.bankName,
            vamType = vam.vamType,
            mablaghKol = vam.mablaghKol,
            tedadAghsat = vam.tedadAghsat,
            nerkhSoodSalane = vam.nerkhSoodSalane,
            shoroTarikh = vam.shoroTarikh,
            shoroJalali = vam.shoroJalali,
            maghazeAghsat = vam.maghazeAghsat,
            yaddasht = vam.yaddasht
        )
        vamDao.insertVam(entity)
    }

    override suspend fun updateVam(vam: VamModel) {
        val entity = VamEntity(
            id = vam.id,
            bankName = vam.bankName,
            vamType = vam.vamType,
            mablaghKol = vam.mablaghKol,
            tedadAghsat = vam.tedadAghsat,
            nerkhSoodSalane = vam.nerkhSoodSalane,
            shoroTarikh = vam.shoroTarikh,
            shoroJalali = vam.shoroJalali,
            maghazeAghsat = vam.maghazeAghsat,
            yaddasht = vam.yaddasht
        )
        vamDao.updateVam(entity)
    }

    override suspend fun deleteVam(vam: VamEntity) = vamDao.deleteVam(vam)

    override suspend fun deleteAll() = vamDao.deleteAll()

    override fun getTotalMablagh(): Flow<Long?> = vamDao.getTotalMablagh()

    override fun getCountVam(): Flow<Int> = vamDao.getCountVam()
}