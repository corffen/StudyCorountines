package com.gordon

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun getUserName(): String {
    delay(500)
    return "Coder"
}

fun main() {
    GlobalScope.launch {
        var userName = getUserName()
        println(userName)
    }

    Thread.sleep(1500)
}