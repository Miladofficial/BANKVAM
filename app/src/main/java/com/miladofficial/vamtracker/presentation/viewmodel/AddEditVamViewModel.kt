/**
 * ViewModel برای افزودن/ویرایش وام
 */

package com.miladofficial.vamtracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miladofficial.vamtracker.data.local.entity.VamEntity
import com.miladofficial.vamtracker.data.repository.VamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddEditVamUiState(
    val bankName: String = "",
    val loanType: String = "",
    val principalAmount: String = "",
    val annualRate: String = "",
    val disbursementDate: String = "",
    val durationMonths: String = "",
    val contractNumber: String = "",
    val branchName: String = "",
    val collateral: String = "",
    val notes: String = "",
    val isLoading: Boolean = false,
    val isSaved: Boolean = false
)

@HiltViewModel
class AddEditVamViewModel @Inject constructor(
    private val repository: VamRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddEditVamUiState())
    val uiState: StateFlow<AddEditVamUiState> = _uiState.asStateFlow()

    fun updateBankName(name: String) {
        _uiState.value = _uiState.value.copy(bankName = name)
    }

    fun updateLoanType(type: String) {
        _uiState.value = _uiState.value.copy(loanType = type)
    }

    fun updatePrincipalAmount(amount: String) {
        _uiState.value = _uiState.value.copy(principalAmount = amount)
    }

    fun updateAnnualRate(rate: String) {
        _uiState.value = _uiState.value.copy(annualRate = rate)
    }

    fun updateDisbursementDate(date: String) {
        _uiState.value = _uiState.value.copy(disbursementDate = date)
    }

    fun updateDurationMonths(months: String) {
        _uiState.value = _uiState.value.copy(durationMonths = months)
    }

    fun updateContractNumber(number: String) {
        _uiState.value = _uiState.value.copy(contractNumber = number)
    }

    fun updateBranchName(name: String) {
        _uiState.value = _uiState.value.copy(branchName = name)
    }

    fun updateCollateral(collateral: String) {
        _uiState.value = _uiState.value.copy(collateral = collateral)
    }

    fun updateNotes(notes: String) {
        _uiState.value = _uiState.value.copy(notes = notes)
    }

    fun saveVam() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val state = _uiState.value
                val vam = VamEntity(
                    bankName = state.bankName,
                    loanType = state.loanType,
                    principalAmount = state.principalAmount.toLongOrNull() ?: 0,
                    annualRate = state.annualRate.toDoubleOrNull() ?: 0.0,
                    disbursementDate = state.disbursementDate,
                    durationMonths = state.durationMonths.toIntOrNull() ?: 0,
                    monthlyPayment = 0,
                    contractNumber = state.contractNumber,
                    branchName = state.branchName,
                    collateral = state.collateral,
                    notes = state.notes
                )
                repository.insertVam(vam)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isSaved = true
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
}
