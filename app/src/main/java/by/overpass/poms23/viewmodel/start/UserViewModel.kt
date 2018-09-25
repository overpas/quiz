package by.overpass.poms23.viewmodel.start

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Intent
import by.overpass.poms23.USERNAME_KEY
import by.overpass.poms23.data.model.pojo.User
import by.overpass.poms23.repository.start.CachingUsersRepository
import by.overpass.poms23.repository.start.UsersRepository
import by.overpass.poms23.ui.quiz.activity.QuizActivity
import by.overpass.poms23.ui.start.activity.StartActivity

class UserViewModel : ViewModel() {

    private val usersRepository: UsersRepository = CachingUsersRepository()

    val users: LiveData<List<User>>
        get() = usersRepository.getUsers()

    fun updateResult(username: String, result: Int): LiveData<Boolean> =
            usersRepository.upsertUser(User(username, result))

    fun playQuiz(startActivity: StartActivity, username: String) {
        startActivity.finish()
        val intent = Intent(startActivity, QuizActivity::class.java)
        intent.putExtra(USERNAME_KEY, username)
        startActivity.startActivity(intent)
    }

}