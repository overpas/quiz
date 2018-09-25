package by.overpass.poms23.repository.start

import android.arch.lifecycle.LiveData
import by.overpass.poms23.data.model.pojo.User

interface UsersRepository {
    fun getUsers(): LiveData<List<User>>
    fun upsertUser(user: User): LiveData<Boolean>
}