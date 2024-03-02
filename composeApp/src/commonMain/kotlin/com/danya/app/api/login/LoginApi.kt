package com.danya.app.api.login

import co.touchlab.kermit.Logger
import com.danya.app.models.User
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.ext.getFullName

class LoginApi(private val auth: FirebaseAuth) : KoinComponent {

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

    suspend fun checkUserLoggedIn(): Flow<Result<Boolean?>> {
        return flow {
            val request = try {
                Result.success(auth.currentUser?.getIdToken(true)?.isNotBlank())
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(request)
        }
    }

    suspend fun writeInMirrorUserDb(newUser: User) {
        try {
            Firebase.firestore.collection(User.CollectionName).document.set(newUser, merge = true)
        } catch (e: Exception) {
            Logger.e(this::class.getFullName(), e)
        }
    }
}
