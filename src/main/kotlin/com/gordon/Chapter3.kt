package com.gordon

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

//<editor-fold desc="顺序执行的挂起函数">
//fun main() = runBlocking<Unit> {
//    val time = measureTimeMillis {
//        val one = doSomethingUsefulOne()
//        val two = doSomethingUsefulTwo()
//        println("The answer is ${one + two}")
//    }
//    println("Completed in $time ms")
//}
//
//suspend fun doSomethingUsefulOne(): Int {
//    delay(1000L) // 假设我们在这里做了些有用的事
//    return 13
//}
//
//suspend fun doSomethingUsefulTwo(): Int {
//    delay(1000L) // 假设我们在这里也做了一些有用的事
//    return 29
//}
//</editor-fold>


//<editor-fold desc="并发执行的挂起函数">
//fun main() = runBlocking<Unit> {
//    val time = measureTimeMillis {
//        val one = async { doSomethingUsefulOne() }
//        val two = async { doSomethingUsefulTwo() }
//        println("The answer is ${one.await() + two.await()}")
//    }
//    println("Completed in $time ms")
//}
//
//suspend fun doSomethingUsefulOne(): Int {
//    delay(1000L) // 假设我们在这里做了些有用的事
//    return 13
//}
//
//suspend fun doSomethingUsefulTwo(): Int {
//    delay(1000L) // 假设我们在这里也做了些有用的事
//    return 29
//}
//</editor-fold>

//<editor-fold desc="惰性执行的async">
fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
        // 执行一些计算
        one.start() // 启动第一个
        two.start() // 启动第二个
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // 假设我们在这里做了些有用的事
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // 假设我们在这里也做了些有用的事
    return 29
}
//</editor-fold>