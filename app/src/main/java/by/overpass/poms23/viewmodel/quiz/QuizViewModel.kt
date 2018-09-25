package by.overpass.poms23.viewmodel.quiz

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Intent
import by.overpass.poms23.BEST_SCORE_KEY
import by.overpass.poms23.CURRENT_SCORE_KEY
import by.overpass.poms23.USERNAME_KEY
import by.overpass.poms23.data.model.pojo.Question
import by.overpass.poms23.repository.quiz.CachingQuizRepository
import by.overpass.poms23.repository.quiz.QuizRepository
import by.overpass.poms23.ui.quiz.activity.QuizActivity
import by.overpass.poms23.ui.result.activity.ResultActivity
import by.overpass.poms23.utils.PrefUtil

class QuizViewModel : ViewModel() {

    private val quizRepository: QuizRepository = CachingQuizRepository()
    private var loadedQuestions: List<Question>? = null
    private var userAnswers: MutableMap<Question, Int?> = mutableMapOf()
    internal lateinit var username: String

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

    val correctAnswers: MutableLiveData<Int> = MutableLiveData()
    val incorrectAnswers: MutableLiveData<Int> = MutableLiveData()

    init {
        correctAnswers.value = 0
        incorrectAnswers.value = 0
    }

    fun setUserAnswer(ordinalNumber: Int, userAnswer: Int) {
        loadedQuestions?.let { list ->
            userAnswers[list.first { it.ordinalNumber == ordinalNumber }] = userAnswer
        }
    }

    fun finishQuiz(quizActivity: QuizActivity) {
        val intent = Intent(quizActivity, ResultActivity::class.java)
        val result = userAnswers.processCorrect()
        PrefUtil.putInt(CURRENT_SCORE_KEY, result)
        PrefUtil
                .getInt(BEST_SCORE_KEY)
                .takeIf { result > it }
                ?.apply { PrefUtil.putInt(BEST_SCORE_KEY, result) }
        intent.putExtra(USERNAME_KEY, username)
        intent.putExtra(CURRENT_SCORE_KEY, result)
        quizActivity.finish()
        quizActivity.startActivity(intent)
    }

    fun checkAnswers(currentQuestionNumber: Int) {
        val correct = userAnswers.processCorrect(currentQuestionNumber)
        correctAnswers.value = correct
        incorrectAnswers.value = currentQuestionNumber - correct
    }
}


private fun MutableMap<Question, Int?>.processCorrect(until: Int = this.size): Int {
    return filter {
        it.key.ordinalNumber < until
                && it.key.answer != null
                && it.value != null
                && it.key.answer == it.value
    }.size
}
