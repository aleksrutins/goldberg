package com.farthergate.util

object Format {
    fun escape(s: String, esc: String): String {
        return "\u001b[" + esc + "m" + s + "\u001b[0m"
    }

    fun bold(s: String): String {
        return escape(s, "1")
    }

    fun red(s: String): String {
        return escape(s, "31")
    }
}
