package com.example.ciscolauncher.utils

import android.content.Context
import com.example.ciscolauncher.models.AppInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppUtility @Inject constructor(@ApplicationContext private val context: Context)  {

	private val denyList: List<String>
		get() = mutableListOf(
			"com.android.chrome",
			"com.google.android.apps.maps",
			"com.android.dialer",
			"com.google.android.gm"
		)

	fun getInstalledApps(): List<AppInfo> {
		val apps = ArrayList<AppInfo>()
		val packageManager = context.packageManager
		for (packInfo in packageManager.getInstalledPackages(0)
			.filter { denyList.contains(it.packageName).not() }) {
			val launchIntent = packageManager.getLaunchIntentForPackage(packInfo.packageName)
			if (launchIntent != null && launchIntent.equals("").not()) {
				val appName = packInfo.applicationInfo.loadLabel(packageManager).toString()
				val icon = packInfo.applicationInfo.loadIcon(packageManager)
				val packageName = packInfo.packageName
				apps.add(
					AppInfo(
						appName,
						packageName,
						icon,
						false,
					)
				)
			}
		}
		return apps.sortedBy { it.name }
	}
}