package by.overpass.poms23.repository.quiz

import android.arch.lifecycle.LiveData
import by.overpass.poms23.data.model.pojo.Question

interface QuizRepository {
    fun getQuestions(): LiveData<List<Question>>
}