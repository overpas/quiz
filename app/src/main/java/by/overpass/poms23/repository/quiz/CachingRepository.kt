package by.overpass.poms23.repository.quiz

import android.arch.lifecycle.LiveData
import by.overpass.poms23.data.model.pojo.Question

class CachingRepository(
        private val remoteRepository: QuizRepository = RemoteRepository(),
        private val localRepository: LocalRepository = LocalRepository()
) : QuizRepository {

    override fun getQuestions(): LiveData<List<Question>> {
        remoteRepository.getQuestions().observeForever { list ->
            list?.let { localRepository.addQuestions(it) }
        }
        return localRepository.getQuestions()
    }

}