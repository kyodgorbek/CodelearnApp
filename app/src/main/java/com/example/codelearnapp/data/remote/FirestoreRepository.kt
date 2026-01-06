package com.example.codelearnapp.data.remote

import com.example.codelearnapp.domain.model.LeaderboardEntry
import com.example.codelearnapp.domain.model.UserProfile
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FirestoreRepository(
    private val firestore: FirebaseFirestore
) {
    private val usersCollection = firestore.collection("users")
    private val leaderboardCollection = firestore.collection("leaderboard")
    
    suspend fun saveUserProfile(userId: String, profile: UserProfile): Result<Unit> {
        return try {
            usersCollection.document(userId).set(profile, com.google.firebase.firestore.SetOptions.merge()).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun updateLastLogin(userId: String): Result<Unit> {
        return try {
            usersCollection.document(userId)
                .update("lastLoginAt", System.currentTimeMillis())
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    fun getUserProfile(userId: String): Flow<UserProfile?> = callbackFlow {
        val subscription = usersCollection.document(userId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                
                val profile = snapshot?.toObject(UserProfile::class.java)
                trySend(profile)
            }
        
        awaitClose { subscription.remove() }
    }
    
    suspend fun updateUserXp(userId: String, xp: Int): Result<Unit> {
        return try {
            usersCollection.document(userId)
                .update("totalXp", xp)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun syncLessonProgress(
        userId: String,
        lessonId: String,
        isCompleted: Boolean
    ): Result<Unit> {
        return try {
            usersCollection.document(userId)
                .collection("progress")
                .document(lessonId)
                .set(mapOf("completed" to isCompleted, "timestamp" to System.currentTimeMillis()))
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    fun getLeaderboard(limit: Int = 50): Flow<List<LeaderboardEntry>> = callbackFlow {
        val subscription = leaderboardCollection
            .orderBy("totalXp", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .limit(limit.toLong())
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                
                val entries = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(LeaderboardEntry::class.java)?.copy(id = doc.id)
                } ?: emptyList()
                
                trySend(entries)
            }
        
        awaitClose { subscription.remove() }
    }
    
    suspend fun updateLeaderboard(userId: String, userName: String, xp: Int): Result<Unit> {
        return try {
            leaderboardCollection.document(userId)
                .set(LeaderboardEntry(userId, userName, xp, System.currentTimeMillis()))
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
