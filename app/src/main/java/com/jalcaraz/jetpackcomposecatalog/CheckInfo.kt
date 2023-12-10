package com.jalcaraz.jetpackcomposecatalog

data class CheckInfo(
    val title: String,
    val selected: Boolean = false,
    var onCheckedChange: (Boolean) -> Unit
)
