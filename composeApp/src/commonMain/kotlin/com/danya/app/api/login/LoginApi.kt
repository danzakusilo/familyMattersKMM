package com.danya.app.api.login

import com.danya.app.db.settings.Prefs
import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent

class LoginApi(private val prefs: Prefs, private val auth: FirebaseAuth) : KoinComponent {
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
