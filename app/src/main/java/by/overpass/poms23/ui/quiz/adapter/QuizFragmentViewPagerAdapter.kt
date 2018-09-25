package by.overpass.poms23.ui.quiz.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import by.overpass.poms23.data.model.pojo.Question
import by.overpass.poms23.ui.quiz.fragment.QuizFragment

class QuizFragmentViewPagerAdapter(
        fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager) {

    internal var questions: List<Question> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItem(position: Int): Fragment =
            QuizFragment.getInstance(questions.first { it.ordinalNumber == position })

    override fun getCount(): Int = questions.size

}