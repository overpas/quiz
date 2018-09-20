package by.overpass.poms23.ui.launch.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import by.overpass.poms23.BEST_SCORE_KEY
import by.overpass.poms23.R
import by.overpass.poms23.ui.quiz.activity.QuizActivity
import by.overpass.poms23.utils.PrefUtil
import kotlinx.android.synthetic.main.activity_launch.*

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        tvBestScore.text = PrefUtil.getInt(BEST_SCORE_KEY).toString()
        btnPlay.setOnClickListener {
            finish()
            startActivity(Intent(this@LaunchActivity, QuizActivity::class.java))
        }
    }
}
