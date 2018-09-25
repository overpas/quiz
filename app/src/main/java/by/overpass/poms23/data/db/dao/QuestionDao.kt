package by.overpass.poms23.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import by.overpass.poms23.data.model.pojo.Question

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(questions: List<Question>)

    @Query("SELECT * FROM Question ORDER BY ordinalNumber ASC")
    fun selectAll(): LiveData<List<Question>>

}