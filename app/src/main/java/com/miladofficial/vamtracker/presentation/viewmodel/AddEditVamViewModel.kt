package com.miladofficial.vamtracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miladofficial.vamtracker.data.repository.VamRepository
import com.miladofficial.vamtracker.domain.model.VamModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddEditVamViewModel @Inject constructor(
    private val repository: VamRepository
) : ViewModel() {

    private val _isSaving = MutableStateFlow(false)
    val isSaving: StateFlow<Boolean> = _isSaving.asStateFlow()

    private val _savedSuccessfully = MutableStateFlow(false)
    val savedSuccessfully: StateFlow<Boolean> = _savedSuccessfully.asStateFlow()

    fun saveVam(
        bankName: String,
        vamType: String,
        mablaghKol: Long,
        tedadAghsat: Int,
        nerkhSood: Double,
        shoroDate: Date,
        shoroJalali: String,
        yaddasht: String,
        vamId: Long = 0
    ) {
        viewModelScope.launch {
            _isSaving.value = true
            val vam = VamModel(
                id = vamId,
                bankName = bankName,
                vamType = vamType,
                mablaghKol = mablaghKol,
                tedadAghsat = tedadAghsat,
                nerkhSoodSalane = nerkhSood,
                shoroTarikh = shoroDate,
                shoroJalali = shoroJalali,
                maghazeAghsat = 0,
                yaddasht = yaddasht
            )
            if (vamId == 0L) {
                repository.insertVam(vam)
            } else {
                repository.updateVam(vam)
            }
            _savedSuccessfully.value = true
            _isSaving.value = false
        }
    }

    fun resetSavedState() {
        _savedSuccessfully.value = false
    }
}