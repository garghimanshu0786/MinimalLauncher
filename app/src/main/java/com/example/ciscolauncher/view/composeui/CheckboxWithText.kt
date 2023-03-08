package com.example.ciscolauncher.view.composeui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CheckboxWithText(
	text: String,
	checked: Boolean,
	modifier: Modifier,
	onCheckedChange: (Boolean) -> Unit
) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = modifier
			.fillMaxWidth()
			.padding(16.dp)
			.clickable { onCheckedChange(checked.not()) }
	) {
		Checkbox(
			checked = checked,
			onCheckedChange = onCheckedChange
		)
		Text(
			text = text,
			style = MaterialTheme.typography.bodyMedium,
			color = if (checked) Color.Gray else Color.Black,
			modifier = Modifier.padding(start = 16.dp)
		)
	}
}
