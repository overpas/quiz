package by.overpass.poms23.ui.quiz.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import by.overpass.poms23.QUESTION_KEY

import by.overpass.poms23.R
import by.overpass.poms23.data.model.pojo.Question
import by.overpass.poms23.ui.quiz.activity.QuizActivity
import kotlinx.android.synthetic.main.fragment_quiz.*

class QuizFragment : Fragment() {

    companion object {
        fun getInstance(question: Question): QuizFragment {
            val arguments = Bundle()
            arguments.putParcelable(QUESTION_KEY, question)
            val fragment = QuizFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments
                ?.getParcelable<Question>(QUESTION_KEY)
                ?.let {
                    tvQuestion.text = it.text
                    rbOptionOne.text = it.options?.get(0)
                    rbOptionTwo.text = it.options?.get(1)
                    rbOptionThree.text = it.options?.get(2)
                    rbOptionFour.text = it.options?.get(3)
                }
        rgOptions.setOnCheckedChangeListener(activity as QuizActivity)
    }

}
