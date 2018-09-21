package by.overpass.poms23.ui.quiz.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RadioGroup
import by.overpass.poms23.R
import by.overpass.poms23.R.id.vpQuizFragmentContainer
import by.overpass.poms23.data.model.pojo.Question
import by.overpass.poms23.ui.quiz.adapter.QuizFragmentViewPagerAdapter
import by.overpass.poms23.ui.result.activity.ResultActivity
import by.overpass.poms23.viewmodel.quiz.QuizViewModel
import com.google.android.gms.tasks.OnSuccessListener
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private lateinit var quizFragmentsViewPagerAdapter: QuizFragmentViewPagerAdapter
    private lateinit var quizViewModel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        quizFragmentsViewPagerAdapter = QuizFragmentViewPagerAdapter(
                emptyList(), supportFragmentManager
        )
        vpQuizFragmentContainer.adapter = quizFragmentsViewPagerAdapter
        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)
        quizViewModel.questions.observe(this, Observer { list ->
            list?.let {
                quizFragmentsViewPagerAdapter.questions = it
            }
        })
        btnNext.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val currentItem = vpQuizFragmentContainer.currentItem
        val totalItems = quizFragmentsViewPagerAdapter.count
        if (currentItem < totalItems - 1) {
            vpQuizFragmentContainer.currentItem = currentItem + 1
        } else {
            quizViewModel.finishQuiz(this)
        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedItemId: Int) {
        quizViewModel.setUserAnswer(
                vpQuizFragmentContainer.currentItem,
                when (checkedItemId) {
                    R.id.rbOptionOne -> 0
                    R.id.rbOptionTwo -> 1
                    R.id.rbOptionThree -> 2
                    else -> 3
                }
        )
    }

}
