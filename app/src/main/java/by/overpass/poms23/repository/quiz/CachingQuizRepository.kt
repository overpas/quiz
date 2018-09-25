package by.overpass.poms23.repository.quiz

import android.arch.lifecycle.LiveData
import by.overpass.poms23.data.model.pojo.Question

class CachingQuizRepository(
        private val remoteRepository: QuizRepository = RemoteQuizRepository(),
        private val localQuizRepository: LocalQuizRepository = LocalQuizRepository()
) : QuizRepository {

    override fun getQuestions(): LiveData<List<Question>> {
        remoteRepository.getQuestions().observeForever { list ->
            list?.let { localQuizRepository.addQuestions(it) }
        }
        return localQuizRepository.getQuestions()
    }

}