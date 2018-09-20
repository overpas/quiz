package by.overpass.poms23.ui.quiz.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import by.overpass.poms23.data.model.pojo.Question
import by.overpass.poms23.ui.quiz.fragment.QuizFragment

class QuizFragmentViewPagerAdapter(
        private val questions: List<Question>,
        fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment =
            QuizFragment.getInstance(questions.first { it.ordinalNumber == position })

    override fun getCount(): Int = questions.size

}