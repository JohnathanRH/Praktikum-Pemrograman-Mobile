package com.example.compose.viewmodel

sealed interface NavigationEvent {
    data class NavigateTo(val route: String) : NavigationEvent
    data object NavigateBack : NavigationEvent
}