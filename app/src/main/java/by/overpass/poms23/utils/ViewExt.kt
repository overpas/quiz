package by.overpass.poms23.utils

import android.view.View
import android.widget.Toast

fun View.shortToast(stringResId: Int) {
    Toast.makeText(this.context, stringResId, Toast.LENGTH_SHORT).show()
}