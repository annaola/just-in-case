package com.folklore.justincase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.folklore.justincase.ui.component.NoteTileComponent
import com.folklore.justincase.ui.state.MainScreenUiState

@Composable
fun MainScreen(
    onNavigateToNew: () -> Unit,
    viewModel: MainViewModel = hiltViewModel<MainViewModel>(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    MainScreenContent(onNavigateToNew = onNavigateToNew, state = state)
}

@Composable
private fun MainScreenContent(onNavigateToNew: () -> Unit, state: MainScreenUiState) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToNew) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Dodaj"
                )
            }
        }
    ) { innerPadding ->
        LazyVerticalStaggeredGrid(
            modifier = Modifier.padding(horizontal = 16.dp),
            columns = StaggeredGridCells.Adaptive(minSize = 160.dp),
            contentPadding = innerPadding,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalItemSpacing = 8.dp
        ) {
            item(span = StaggeredGridItemSpan.FullLine) {
                Text(
                    text = "Witaj w Just In Case!",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
            items(state.notes.size) { index ->
                NoteTileComponent(note = state.notes[index])
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MainScreenContent(onNavigateToNew = {}, state = MainScreenUiState())
}
