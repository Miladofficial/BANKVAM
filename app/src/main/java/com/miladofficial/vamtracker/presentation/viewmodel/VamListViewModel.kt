/**
 * ViewModel برای لیست وام‌ها
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

/**
 * حالت لیست وام‌ها
 */
data class VamListUiState(
    val vams: List<VamEntity> = emptyList(),
    val isLoading: Boolean = true,
    val searchQuery: String = "",
    val filterType: String = "all" // all, active, inactive
)

/**
 * ViewModel لیست وام‌ها
 */
@HiltViewModel
class VamListViewModel @Inject constructor(
    private val repository: VamRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(VamListUiState())
    val uiState: StateFlow<VamListUiState> = _uiState.asStateFlow()

    init {
        loadVams()
    }

    /**
     * بارگیری وام‌ها
     */
    private fun loadVams() {
        viewModelScope.launch {
            repository.getAllActiveVams().collect { vams ->
                _uiState.value = _uiState.value.copy(
                    vams = vams,
                    isLoading = false
                )
            }
        }
    }

    /**
     * جستجو در وام‌ها
     */
    fun searchVams(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
        if (query.isEmpty()) {
            loadVams()
        } else {
            viewModelScope.launch {
                repository.searchVams(query).collect { vams ->
                    _uiState.value = _uiState.value.copy(vams = vams)
                }
            }
        }
    }

    /**
     * حذف وام
     */
    fun deleteVam(vam: VamEntity) {
        viewModelScope.launch {
            repository.deleteVam(vam)
            loadVams()
        }
    }
}
