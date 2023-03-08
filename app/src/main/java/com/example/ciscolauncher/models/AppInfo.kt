package com.example.ciscolauncher.models

import android.graphics.drawable.Drawable

data class AppInfo(
	val name: String,
	val packageName: String,
	val icon: Drawable,
	val isHidden: Boolean,
)