package by.overpass.poms23.viewmodel.quiz

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Intent
import by.overpass.poms23.BEST_SCORE_KEY
import by.overpass.poms23.CURRENT_SCORE_KEY
import by.overpass.poms23.data.model.pojo.Question
import by.overpass.poms23.repository.quiz.QuizRepository
import by.overpass.poms23.repository.quiz.RemoteRepository
import by.overpass.poms23.ui.quiz.activity.QuizActivity
import by.overpass.poms23.ui.result.activity.ResultActivity
import by.overpass.poms23.utils.PrefUtil

class QuizViewModel : ViewModel() {

    private val quizRepository: QuizRepository = RemoteRepository()
    private var loadedQuestions: List<Question>? = null
    private var userAnswers: MutableMap<Question, Int?> = mutableMapOf()

    val questions: LiveData<List<Question>>
        get() {
            return quizRepository.getQuestions().also { livedata ->
                livedata.observeForever { list ->
                    loadedQuestions = list
                    list?.forEach {
                        userAnswers[it] = null
                    }

                }
            }
        }

    fun setUserAnswer(ordinalNumber: Int, userAnswer: Int) {
        loadedQuestions?.let { list ->
            userAnswers[list.first { it.ordinalNumber == ordinalNumber }] = userAnswer
        }
    }

    fun finishQuiz(quizActivity: QuizActivity) {
        val intent = Intent(quizActivity, ResultActivity::class.java)
        val result = userAnswers.processResult()
        PrefUtil.putInt(CURRENT_SCORE_KEY, result)
        PrefUtil
                .getInt(BEST_SCORE_KEY)
                .takeIf { result > it }
                ?.apply { PrefUtil.putInt(BEST_SCORE_KEY, result) }
        quizActivity.finish()
        quizActivity.startActivity(intent)
    }
}


private fun MutableMap<Question, Int?>.processResult(): Int {
    return filter { it.key.answer != null && it.value != null && it.key.answer == it.value }.size
}
