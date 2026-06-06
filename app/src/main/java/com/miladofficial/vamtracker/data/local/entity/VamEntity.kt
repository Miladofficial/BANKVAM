package com.miladofficial.vamtracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "vam_table")
data class VamEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val bankName: String,
    val vamType: String,
    val mablaghKol: Long,
    val tedadAghsat: Int,
    val nerkhSoodSalane: Double,
    val shoroTarikh: Date,
    val shoroJalali: String,
    val maghazeAghsat: Int = 0,
    val yaddasht: String = ""
)