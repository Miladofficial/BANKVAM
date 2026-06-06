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

@HiltViewModel
class VamListViewModel @Inject constructor(
    private val repository: VamRepository
) : ViewModel() {

    private val _vamList = MutableStateFlow<List<VamEntity>>(emptyList())
    val vamList: StateFlow<List<VamEntity>> = _vamList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadVamList()
    }

    fun loadVamList() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getAllVam().collect { list ->
                _vamList.value = list
            }
            _isLoading.value = false
        }
    }

    fun deleteVam(vam: VamEntity) {
        viewModelScope.launch {
            repository.deleteVam(vam)
            loadVamList()
        }
    }
}