package com.example.pocket_rpg._common.services.real_time_storage

import com.example.pocket_rpg._common.services.real_time_storage.User
import kotlinx.coroutines.flow.Flow

interface Account {
    val currentUserId: String
    val hasUser: Boolean

    val currentUser: Flow<User>

    suspend fun authenticate(email: String, password: String)
    suspend fun sendRecoveryEmail(email: String)
    suspend fun createAnonymousAccount()
    suspend fun linkAccount(email: String, password: String)
    suspend fun deleteAccount()
    suspend fun signOut()
}