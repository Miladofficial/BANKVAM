package com.miladofficial.vamtracker.data.local.dao

import androidx.room.*
import com.miladofficial.vamtracker.data.local.entity.VamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VamDao {

    @Query("SELECT * FROM vam_table ORDER BY shoroTarikh DESC")
    fun getAllVam(): Flow<List<VamEntity>>

    @Query("SELECT * FROM vam_table WHERE id = :vamId")
    suspend fun getVamById(vamId: Long): VamEntity?

    @Insert
    suspend fun insertVam(vam: VamEntity)

    @Update
    suspend fun updateVam(vam: VamEntity)

    @Delete
    suspend fun deleteVam(vam: VamEntity)

    @Query("DELETE FROM vam_table")
    suspend fun deleteAll()

    @Query("SELECT SUM(mablaghKol) FROM vam_table")
    fun getTotalMablagh(): Flow<Long?>

    @Query("SELECT COUNT(*) FROM vam_table")
    fun getCountVam(): Flow<Int>
}