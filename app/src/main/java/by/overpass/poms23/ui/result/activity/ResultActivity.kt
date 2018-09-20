package by.overpass.poms23.ui.result.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import by.overpass.poms23.R
import by.overpass.poms23.ui.launch.activity.LaunchActivity
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        // TODO: Set score
        btnReturn.setOnClickListener {
            finish()
            startActivity(Intent(this@ResultActivity, LaunchActivity::class.java))
        }
    }
}
