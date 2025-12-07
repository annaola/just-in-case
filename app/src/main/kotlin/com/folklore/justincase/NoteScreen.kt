package com.folklore.justincase

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.folklore.justincase.ui.component.TransparentTextField
import com.folklore.justincase.ui.theme.JustInCaseTheme

@Composable
fun NoteScreen(onBack: () -> Unit, viewModel: NoteViewModel = hiltViewModel<NoteViewModel>()) {
    NoteScreenContent(onBack = onBack, viewModel.contentState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NoteScreenContent(onBack: () -> Unit, contentState: TextFieldState) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Powrót"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        TransparentTextField(
            state = contentState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .focusRequester(focusRequester),
            placeholder = { Text("Wpisz tekst") }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NoteScreenPreview() {
    JustInCaseTheme {
        NoteScreenContent(onBack = {}, contentState = TextFieldState("Przykładowa treść notatki"))
    }
}
