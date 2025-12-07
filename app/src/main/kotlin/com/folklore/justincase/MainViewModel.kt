package com.folklore.justincase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.folklore.justincase.domain.NoteRepository
import com.folklore.justincase.ui.state.MainScreenUiState
import com.folklore.justincase.ui.state.NoteTileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    noteRepository: NoteRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            noteRepository.getAll().collect { notes ->
                _uiState.value = _uiState.value.copy(
                    notes = notes.map { note ->
                        NoteTileUiState(
                            id = note.id,
                            title = note.title,
                            content = note.content,
                            dateTime = note.dateTime
                        )
                    }
                )
            }
        }
    }
}
