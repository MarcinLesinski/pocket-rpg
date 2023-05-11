package com.example.pocket_rpg._common.services.real_time_storage

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface RealTimeService {
    val tasks: Flow<List<Task>>

    suspend fun getTask(taskId: String): Task?

    suspend fun save(task: Task): String
    suspend fun update(task: Task)
    suspend fun delete(taskId: String)
}

class RealTimeStorageService
@Inject
constructor(
    private val firestore: FirebaseFirestore,
    private val account: Account

) : RealTimeService {
    @OptIn(ExperimentalCoroutinesApi::class)
    override val tasks: Flow<List<Task>>
        get() = account.currentUser.flatMapLatest { user ->
                currentCollection(user.id).snapshots().map { snapshot -> snapshot.toObjects<Task>() }
            }


    override suspend fun getTask(taskId: String): Task? =
        currentCollection(account.currentUserId).document(taskId).get().await().toObject()

    override suspend fun save(task: Task): String = currentCollection(account.currentUserId).add(task).await().id

    override suspend fun update(task: Task) {
//        trace(UPDATE_TASK_TRACE) {
            currentCollection(account.currentUserId).document(task.id).set(task).await()
        }

    override suspend fun delete(taskId: String) {
        currentCollection(account.currentUserId).document(taskId).delete().await()
    }

    private fun currentCollection(uid: String): CollectionReference =
        firestore.collection(USER_COLLECTION).document(uid).collection(TASK_COLLECTION)

    companion object {
        private const val USER_COLLECTION = "users"
        private const val TASK_COLLECTION = "tasks"
        private const val SAVE_TASK_TRACE = "saveTask"
        private const val UPDATE_TASK_TRACE = "updateTask"
    }
}
