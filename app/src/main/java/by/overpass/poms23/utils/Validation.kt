package by.overpass.poms23.utils

import java.util.regex.Pattern

fun isUsernameValid(username: String): Boolean {
    val pattern = Pattern.compile("^[a-zA-Z0-9._-]{4,}\$")
    return username.isNotEmpty() && pattern.matcher(username).matches()
}