package com.example.ciscolauncher.view.composeui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.ciscolauncher.R
import com.example.ciscolauncher.models.AppInfo

@Composable
fun Loader(filteredApps: List<AppInfo>) {
	if (filteredApps.isEmpty()) {
		Column(
			Modifier
				.fillMaxSize()
				.padding(bottom = 300.dp),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			val context = LocalContext.current
			Image(
				painter = rememberAsyncImagePainter(
					ImageRequest.Builder(context).data(data = R.drawable.cisco_loader)
						.apply(block = {
							size(Size.ORIGINAL)
						}).build(),
					imageLoader = ImageLoader.Builder(context)
						.components { add(ImageDecoderDecoder.Factory()) }.build()
				),
				contentDescription = "cisco loader",
				modifier = Modifier.fillMaxWidth(),
			)
			Text(text = stringResource(id = R.string.no_apps_found))
		}
	}
}