package com.folklore.justincase.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.folklore.justincase.ui.state.NoteTileUiState
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

@Composable
fun NoteTileComponent(
    modifier: Modifier = Modifier,
    note: NoteTileUiState
) {
    val dateTimeFormatter = remember {
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.getDefault())
    }
    Card(
        modifier = modifier
            .wrapContentHeight()
            .heightIn(max = 240.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            note.title?.let {
                Text(text = it, style = MaterialTheme.typography.headlineMedium)
            }
            note.content?.let {
                Text(text = it)
            }
            Text(
                modifier = Modifier.align(Alignment.End),
                text = dateTimeFormatter.format(note.dateTime),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
