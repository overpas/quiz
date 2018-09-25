package by.overpass.poms23.ui.start.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import by.overpass.poms23.BEST_SCORE_KEY
import by.overpass.poms23.R
import by.overpass.poms23.ui.start.adapter.UserRecyclerViewAdapter
import by.overpass.poms23.utils.PrefUtil
import by.overpass.poms23.utils.isUsernameValid
import by.overpass.poms23.viewmodel.start.UserViewModel
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var usersRecyclerViewAdapter: UserRecyclerViewAdapter
    private lateinit var usersViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        rvBestResults.layoutManager = LinearLayoutManager(this)
        usersRecyclerViewAdapter = UserRecyclerViewAdapter()
        rvBestResults.adapter = usersRecyclerViewAdapter
        usersViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        usersViewModel.users.observe(this, Observer { list ->
            list?.let { usersRecyclerViewAdapter.users = it }
        })
        tvBestScore.text = PrefUtil.getInt(BEST_SCORE_KEY).toString()
        btnPlay.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val username = etUsername.text.toString().trim()
        if (!isUsernameValid(username)) {
            etUsername.apply {
                error = this@StartActivity.getString(R.string.improper_username_text)
                postDelayed({ error = null }, 2500)
            }
            return
        }
        usersViewModel.playQuiz(this, username)
    }
}
