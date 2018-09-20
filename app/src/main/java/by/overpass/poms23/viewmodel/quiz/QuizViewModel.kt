package by.overpass.poms23.viewmodel.quiz

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import by.overpass.poms23.data.model.pojo.Question
import by.overpass.poms23.repository.quiz.CachingRepository
import by.overpass.poms23.repository.quiz.QuizRepository

class QuizViewModel : ViewModel() {

    private val quizRepository: QuizRepository = CachingRepository()

    val questions: LiveData<List<Question>>
        get() {
            return quizRepository.getQuestions()
        }

}