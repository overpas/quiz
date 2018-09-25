package by.overpass.poms23.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import by.overpass.poms23.data.model.pojo.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("SELECT * FROM User ORDER BY bestScore DESC")
    fun selectAll(): LiveData<List<User>>

}