package com.josealfonsomora.ados.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josealfonsomora.ados.domain.Autobus
import com.josealfonsomora.ados.domain.DeleteAutobusUseCase
import com.josealfonsomora.ados.domain.GetAutobusesListUseCase
import com.josealfonsomora.ados.ui.main.MainScreenState.Loaded
import com.josealfonsomora.ados.ui.main.MainScreenState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAutobusesList: GetAutobusesListUseCase,
    private val deleteAutobus: DeleteAutobusUseCase,
) : ViewModel() {

    private var _state = MutableStateFlow<MainScreenState>(Loading)
    val state = _state

    init {
        fetchAutobusList()
    }

    private fun fetchAutobusList() {
        viewModelScope.launch(IO) {
            getAutobusesList().collect {
                _state.value = Loaded(it)
            }
        }
    }

    fun onDeleteAutobusClicked(autobus: Autobus) {
        viewModelScope.launch(IO) {
            deleteAutobus(autobus)
        }
    }
}

sealed interface MainScreenState {
    data object Loading : MainScreenState
    data class Loaded(val list: List<Autobus>) : MainScreenState
    data object Error : MainScreenState
}