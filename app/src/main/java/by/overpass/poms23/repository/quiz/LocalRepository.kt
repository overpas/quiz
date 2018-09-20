package by.overpass.poms23.repository.quiz

import android.arch.lifecycle.LiveData
import by.overpass.poms23.Poms23App
import by.overpass.poms23.data.db.Poms23AppDB
import by.overpass.poms23.data.model.pojo.Question
import by.overpass.poms23.utils.runInBackground

class LocalRepository : QuizRepository {

    private val questionDao by lazy {
        return@lazy Poms23AppDB.getInstance(Poms23App.getAppContext()).getQuestionDao()
    }

    override fun getQuestions(): LiveData<List<Question>> = questionDao.selectAll()

    fun addQuestions(questions: List<Question>) {
        runInBackground {
            questionDao.insert(questions)
        }
    }
}