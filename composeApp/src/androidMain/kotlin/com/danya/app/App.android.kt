package com.danya.app

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.danya.app.di.initKoin
import com.danya.app.models.StockpileItemModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf

class FamilyApplication : Application() {
    companion object {
        lateinit var INSTANCE: FamilyApplication
    }

    override fun onCreate() {
        super.onCreate()
        initKoin()
        INSTANCE = this
    }
}

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Firebase.firestore.setLoggingEnabled(true)
        setContent {
            App()
        }
    }
}

internal actual fun openUrl(url: String?) {
    val uri = url?.let { Uri.parse(it) } ?: return
    val intent = Intent().apply {
        action = Intent.ACTION_VIEW
        data = uri
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    FamilyApplication.INSTANCE.startActivity(intent)
}