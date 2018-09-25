package by.overpass.poms23.repository.start

import android.arch.lifecycle.LiveData
import by.overpass.poms23.data.model.pojo.User

class CachingUsersRepository(
        private val remoteUsersRepository: UsersRepository = RemoteUsersRepository(),
        private val localUsersRepository: LocalUsersRepository = LocalUsersRepository()
) : UsersRepository {

    override fun getUsers(): LiveData<List<User>> {
        remoteUsersRepository.getUsers().observeForever { list ->
            list?.forEach { localUsersRepository.upsertUser(it) }
        }
        return localUsersRepository.getUsers()
    }

    override fun upsertUser(user: User): LiveData<Boolean> = remoteUsersRepository.upsertUser(user)

}