import FamilyApp.composeApp.BuildConfig
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.danya.app.App
import com.danya.app.di.initKoin
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseOptions
import dev.gitlive.firebase.initialize
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    Firebase.initialize(
        options = FirebaseOptions(
            apiKey = BuildConfig.FirebaseApiKey,
            authDomain = BuildConfig.firebaseAuthDomain,
            projectId = BuildConfig.firebaseProjectId,
            storageBucket = BuildConfig.firebaseStorageBucket,
            applicationId = BuildConfig.firebaseApplicationId
        )
    )
    initKoin()
    onWasmReady {
        CanvasBasedWindow("FamilyApp") {
            App()
        }
    }
}
