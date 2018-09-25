package by.overpass.poms23.repository.start

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import by.overpass.poms23.DB
import by.overpass.poms23.data.model.pojo.User
import by.overpass.poms23.utils.runInBackground
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class RemoteUsersRepository : UsersRepository {

    override fun getUsers(): LiveData<List<User>> {
        val users = MutableLiveData<List<User>>()
        FirebaseFirestore
                .getInstance()
                .collection(DB.USERS_REMOTE_COLLECTION_NAME)
                .orderBy(DB.FIELD_BEST_SCORE, Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        users.value = task
                                .result
                                .documents
                                .map {
                                    it.toObject(User::class.java)!!
                                }
                    } else {

                    }
                }
        return users
    }

    override fun upsertUser(user: User): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        FirebaseFirestore
                .getInstance()
                .collection(DB.USERS_REMOTE_COLLECTION_NAME)
                .whereEqualTo(DB.FIELD_NAME, user.name)
                .limit(1)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result.documents.apply {
                            if (size > 0) {
                                updateUser(user, get(0).id).observeForever { result.value = it }
                            } else {
                                addNewUser(user).observeForever { result.value = it }
                            }
                        }
                    }
                }
        return result
    }

    private fun addNewUser(user: User): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        FirebaseFirestore
                .getInstance()
                .collection(DB.USERS_REMOTE_COLLECTION_NAME)
                .add(user)
                .addOnCompleteListener {
                    result.value = it.isSuccessful
                }
        return result
    }

    private fun updateUser(user: User, docId: String): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        FirebaseFirestore
                .getInstance()
                .collection(DB.USERS_REMOTE_COLLECTION_NAME)
                .document(docId)
                .set(user)
                .addOnCompleteListener {
                    result.value = it.isSuccessful
                }
        return result
    }

}