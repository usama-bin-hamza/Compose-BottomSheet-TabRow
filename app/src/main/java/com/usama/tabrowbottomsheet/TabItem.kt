package com.usama.tabrowbottomsheet

import androidx.compose.ui.graphics.vector.ImageVector

data class TabItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
)
