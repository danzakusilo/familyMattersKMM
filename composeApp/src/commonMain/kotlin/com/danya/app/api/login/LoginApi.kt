package com.danya.app.api.login

import com.danya.app.db.settings.Prefs
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class LoginApi : KoinComponent {
    private val auth = Firebase.auth
    private val prefs: Prefs = get()
    val token = prefs.sessionToken

    suspend fun registration(email: String, password: String): Flow<Result<AuthResult>> {
        return flow {
            val request = try {
                Result.success(auth.createUserWithEmailAndPassword(email, password))
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(request)
        }
    }

    suspend fun login(email: String, password: String): Flow<Result<AuthResult>> {
        return flow {
            val request = try {
                Result.success(auth.signInWithEmailAndPassword(email, password))
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(request)
        }
    }
}
