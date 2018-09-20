package by.overpass.poms23.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors

private val backgroundExecutor = Executors.newSingleThreadExecutor()
private val uiHandler = Handler(Looper.getMainLooper())

fun runInBackground(task: () -> Unit) {
    backgroundExecutor.execute(task)
}

fun runOnUI(task: () -> Unit) {
    uiHandler.post(task)
}