package by.overpass.poms23.ui.quiz.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.View
import by.overpass.poms23.R
import by.overpass.poms23.ui.quiz.adapter.QuizFragmentViewPagerAdapter
import by.overpass.poms23.ui.result.activity.ResultActivity
import by.overpass.poms23.viewmodel.quiz.QuizViewModel
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var quizFragmentsViewPagerAdapter: PagerAdapter
    private lateinit var quizViewModel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)
        quizViewModel.questions.observe(this, Observer { list ->
            list?.let {
                quizFragmentsViewPagerAdapter = QuizFragmentViewPagerAdapter(
                        it, supportFragmentManager
                )
                vpQuizFragmentContainer.adapter = quizFragmentsViewPagerAdapter
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
            finish()
            startActivity(Intent(this, ResultActivity::class.java))
        }
    }

}
