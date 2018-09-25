package by.overpass.poms23.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import by.overpass.poms23.data.db.dao.QuestionDao
import by.overpass.poms23.data.db.dao.UserDao
import by.overpass.poms23.data.model.pojo.Question
import by.overpass.poms23.data.model.pojo.User

@Database(entities = [Question::class, User::class], version = 2)
@TypeConverters(OptionsConverter::class)
abstract class Poms23AppDB : RoomDatabase() {

    abstract fun getQuestionDao(): QuestionDao

    abstract fun getUserDao(): UserDao

    companion object {
        private var INSTANCE: Poms23AppDB? = null

        fun getInstance(context: Context) =
                INSTANCE ?: synchronized(Poms23AppDB::class) {
                    INSTANCE ?: Room.databaseBuilder(
                            context.applicationContext,
                            Poms23AppDB::class.java,
                            "quiz.db")
                            .fallbackToDestructiveMigration()
                            .build()
                            .also {
                                INSTANCE = it
                            }
                }
    }
}