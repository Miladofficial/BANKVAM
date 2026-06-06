package com.miladofficial.vamtracker.domain.model

import java.util.Date

data class VamModel(
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