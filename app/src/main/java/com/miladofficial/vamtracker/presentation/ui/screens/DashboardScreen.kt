/**
 * صفحه داشبورد
 */

package com.miladofficial.vamtracker.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.miladofficial.vamtracker.presentation.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        TopAppBar(
            title = { Text("داشبورد", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF2196F3)
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DashboardCard(
                title = "کل بدهی",
                amount = formatAmount(uiState.totalDebt),
                icon = Icons.Default.AttachMoney,
                color = Color(0xFFF44336)
            )

            DashboardCard(
                title = "قسط ماهانه",
                amount = formatAmount(uiState.monthlyPayment),
                icon = Icons.Default.Payment,
                color = Color(0xFF2196F3)
            )

            DashboardCard(
                title = "کل سود",
                amount = formatAmount(uiState.totalInterest),
                icon = Icons.Default.TrendingUp,
                color = Color(0xFFFFC107)
            )

            DashboardCard(
                title = "تعداد وام‌ها",
                amount = uiState.activeVamCount.toString(),
                icon = Icons.Default.Folder,
                color = Color(0xFF4CAF50)
            )
        }
    }
}

@Composable
fun DashboardCard(
    title: String,
    amount: String,
    icon: androidx.compose.material.icons.Types.VectorAsset,
    color: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(title, fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    amount,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = color
                )
            }
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = color
            )
        }
    }
}

fun formatAmount(amount: Long): String {
    return when {
        amount >= 1_000_000_000 -> String.format("%.1f میلیارد", amount / 1_000_000_000.0)
        amount >= 1_000_000 -> String.format("%.1f میلیون", amount / 1_000_000.0)
        amount >= 1_000 -> String.format("%.0f هزار", amount / 1_000.0)
        else -> amount.toString()
    }
}
