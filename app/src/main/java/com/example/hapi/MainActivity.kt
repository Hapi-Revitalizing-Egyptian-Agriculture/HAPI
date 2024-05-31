package com.example.hapi

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.presentation.navigation.NavGraph
import com.example.hapi.ui.theme.HapiTheme
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.SetStatusBarColor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var datastore: UserDataPreference

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            if (!hasRequiredPermissions()) {
                ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS,
                    0
                )
            }

            lifecycleScope.launch {
                val deviceLanguage = Locale.getDefault().language
                val isEnglish = deviceLanguage == "en"
                datastore.setLanguage(isEnglish)
            }

            HapiTheme {
                SetStatusBarColor(color = YellowAppColor)
                val navController = rememberNavController()
                Box {
                    NavGraph(navController = navController)
                }
            }
        }
    }

    companion object {
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        val PERMISSIONS = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_MEDIA_IMAGES
        )
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun hasRequiredPermissions(): Boolean {
        return PERMISSIONS.all { permission ->
            ContextCompat.checkSelfPermission(
                applicationContext,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }
    }
}
