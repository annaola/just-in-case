package com.folklore.justincase.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
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
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
            .withLocale(Locale.getDefault())
    }
    ElevatedCard(
        modifier = modifier
            .heightIn(max = 240.dp)
            .wrapContentHeight(),
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            note.title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
            note.content?.let {
                Text(
                    modifier = Modifier.weight(1f, fill = false),
                    text = it,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                modifier = Modifier.align(Alignment.End),
                text = dateTimeFormatter.format(note.dateTime),
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}
