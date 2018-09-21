package by.overpass.poms23.ui.result.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import by.overpass.poms23.CURRENT_SCORE_KEY
import by.overpass.poms23.R
import by.overpass.poms23.ui.launch.activity.LaunchActivity
import by.overpass.poms23.utils.PrefUtil
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        tvScore.text = PrefUtil.getInt(CURRENT_SCORE_KEY).toString()
        btnReturn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        finish()
        startActivity(Intent(this, LaunchActivity::class.java))
    }

}
