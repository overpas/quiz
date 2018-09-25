package by.overpass.poms23.ui.result.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import by.overpass.poms23.CURRENT_SCORE_KEY
import by.overpass.poms23.R
import by.overpass.poms23.USERNAME_KEY
import by.overpass.poms23.ui.start.activity.StartActivity
import by.overpass.poms23.utils.PrefUtil
import by.overpass.poms23.utils.shortToast
import by.overpass.poms23.viewmodel.start.UserViewModel
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        userViewModel = ViewModelProviders
                .of(this)
                .get(UserViewModel::class.java)
        userViewModel
                .updateResult(
                        intent.getStringExtra(USERNAME_KEY),
                        intent.getIntExtra(CURRENT_SCORE_KEY, 0))
                .observe(this, Observer {
                    if (it != null && it == true) {
                        tvScore.shortToast(R.string.score_uploaded_text)
                    }
                })
        tvScore.text = PrefUtil.getInt(CURRENT_SCORE_KEY).toString()
        btnReturn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        finish()
        startActivity(Intent(this, StartActivity::class.java))
    }

}
