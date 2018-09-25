package by.overpass.poms23.ui.quiz.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RadioGroup
import by.overpass.poms23.R
import by.overpass.poms23.USERNAME_KEY
import by.overpass.poms23.ui.quiz.adapter.QuizFragmentViewPagerAdapter
import by.overpass.poms23.viewmodel.quiz.QuizViewModel
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private lateinit var quizFragmentsViewPagerAdapter: QuizFragmentViewPagerAdapter
    private lateinit var quizViewModel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        quizFragmentsViewPagerAdapter = QuizFragmentViewPagerAdapter(supportFragmentManager)
        vpQuizFragmentContainer.adapter = quizFragmentsViewPagerAdapter
        vpQuizFragmentContainer.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                quizViewModel.checkAnswers(position)
            }
        })
        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)
        quizViewModel.username = intent.getStringExtra(USERNAME_KEY)
        quizViewModel.questions.observe(this, Observer { list ->
            list?.let {
                quizFragmentsViewPagerAdapter.questions = it
            }
        })
        quizViewModel.correctAnswers.observe(this, Observer {
            tvCorrect.text = it.toString()
        })
        quizViewModel.incorrectAnswers.observe(this, Observer {
            tvIncorrect.text = it.toString()
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
