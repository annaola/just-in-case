package com.folklore.justincase

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.folklore.justincase.domain.Note
import com.folklore.justincase.domain.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID.randomUUID
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    val noteRepository: NoteRepository
) : ViewModel() {
    val id = generateNoteId()
    val titleState = TextFieldState()
    val contentState = TextFieldState()

    init {
        viewModelScope.launch {
            snapshotFlow { contentState.text }.collect { saveNote() }
        }
        viewModelScope.launch {
            snapshotFlow { titleState.text }.collect { saveNote() }
        }
    }

    private fun saveNote() {
        viewModelScope.launch {
            noteRepository.insert(
                Note(
                    id = id,
                    title = titleState.toNonEmptyString(),
                    content = contentState.toNonEmptyString(),
                    dateTime = LocalDateTime.now()
                )
            )
        }
    }

    private fun generateNoteId() = randomUUID().toString()

    private fun TextFieldState.toNonEmptyString() = text.takeUnless { it.isBlank() }?.toString()
}
