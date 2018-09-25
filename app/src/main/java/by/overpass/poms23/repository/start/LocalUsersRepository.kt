package by.overpass.poms23.repository.start

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.database.sqlite.SQLiteConstraintException
import by.overpass.poms23.Poms23App
import by.overpass.poms23.data.db.Poms23AppDB
import by.overpass.poms23.data.model.pojo.User
import by.overpass.poms23.utils.runInBackground
import by.overpass.poms23.utils.runOnUI

class LocalUsersRepository : UsersRepository {

    private val userDao by lazy {
        return@lazy Poms23AppDB.getInstance(Poms23App.getAppContext()).getUserDao()
    }

    override fun getUsers(): LiveData<List<User>> = userDao.selectAll()

    override fun upsertUser(user: User): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        runInBackground {
            try {
                userDao.insert(user)
            } catch (e: SQLiteConstraintException) {
                userDao.update(user)
            } finally {
                runOnUI { result.value = true }
            }
        }
        return result
    }

}