package com.miladofficial.vamtracker.presentation.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.miladofficial.vamtracker.data.local.entity.VamEntity
import com.miladofficial.vamtracker.presentation.viewmodel.VamListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VamListScreen(
    viewModel: VamListViewModel = hiltViewModel(),
    onAddClick: () -> Unit,
    onItemClick: (Long) -> Unit
) {
    val vamList by viewModel.vamList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("لیست وام‌ها") },
                actions = {
                    IconButton(onClick = onAddClick) {
                        Icon(Icons.Default.Add, contentDescription = "افزودن وام")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
            } else if (vamList.isEmpty()) {
                Text(
                    text = "هیچ وامی ثبت نشده است\nروی دکمه + کلیک کنید",
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(vamList) { vam ->
                        VamListItem(vam = vam, onClick = { onItemClick(vam.id) })
                    }
                }
            }
        }
    }
}

@Composable
fun VamListItem(vam: VamEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = vam.bankName, style = MaterialTheme.typography.titleMedium)
            Text(text = "نوع: ${vam.vamType}", style = MaterialTheme.typography.bodySmall)
            Text(text = "مبلغ: ${vam.mablaghKol} ریال", style = MaterialTheme.typography.bodySmall)
            Text(text = "تعداد اقساط: ${vam.tedadAghsat}", style = MaterialTheme.typography.bodySmall)
        }
    }
}