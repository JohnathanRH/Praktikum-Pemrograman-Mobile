package com.example.compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.CardUiState
import com.example.compose.Routes
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class GamesViewModel(private val username: String) : ViewModel() {
    private val _uistate = MutableStateFlow(CardUiState(username = username))
    val uiState: StateFlow<CardUiState> = _uistate.asStateFlow()

    private val _navigationEvents = Channel<NavigationEvent>()
    val navigationEvents = _navigationEvents.receiveAsFlow()

    fun onClicked(route: String) {
        viewModelScope.launch {
            _navigationEvents.send(NavigationEvent.NavigateTo(route))
        }
    }

    fun onDetailClicked(gameId: Int){
        viewModelScope.launch {
            _navigationEvents.send(
                NavigationEvent.NavigateTo(
                    Routes.ItemDetail.createPath(gameId)
                )
            )
        }
    }
}