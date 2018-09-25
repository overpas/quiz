package by.overpass.poms23.repository.quiz

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import by.overpass.poms23.DB
import by.overpass.poms23.data.model.pojo.Question
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class RemoteQuizRepository : QuizRepository {

    override fun getQuestions(): LiveData<List<Question>> {
        val questions: MutableLiveData<List<Question>> = MutableLiveData()
        FirebaseFirestore
                .getInstance()
                .collection(DB.QUESTIONS_REMOTE_COLLECTION_NAME)
                .orderBy(DB.FIELD_ORDINAL_NUMBER, Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        questions.value = task
                                .result
                                .documents
                                .map {
                                    it.toObject(Question::class.java)!!
                                }
                    } else {

                    }
                }
        return questions
    }

}