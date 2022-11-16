package com.gordon

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

//<editor-fold desc="1.Flow的基本使用">
//fun simple(): Flow<Int> = flow { // 流构建器
//    for (i in 1..3) {
//        delay(100) // 假装我们在这里做了一些有用的事情
//        emit(i) // 发送下一个值
//    }
//}
//
//fun main() = runBlocking<Unit> {
//    // 启动并发的协程以验证主线程并未阻塞
//    launch {
//        for (k in 1..3) {
//            println("I'm not blocked $k")
//            delay(100)
//        }
//    }
//    // 收集这个流
//    simple().collect { value -> println(value) }
//}
//</editor-fold>

//<editor-fold desc="2.为什么说流是冷的?">
//fun simple(): Flow<Int> = flow {
//    println("Flow started")
//    for (i in 1..3) {
//        delay(100)
//        emit(i)
//    }
//}
//
//fun main() = runBlocking<Unit> {
//    println("Calling simple function...")
//    val flow = simple()
//    println("Calling collect...")
//    flow.collect { value -> println(value) }
//    println("Calling collect again...")
//    flow.collect { value -> println(value) }
//}
//</editor-fold>

//<editor-fold desc="改变上游流的执行上下文">
//fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
//
//fun simple(): Flow<Int> = flow {
//    for (i in 1..3) {
//        Thread.sleep(100) // 假装我们以消耗 CPU 的方式进行计算
//        log("Emitting $i")
//        emit(i) // 发射下一个值
//    }
//}.flowOn(Dispatchers.Default) // 在流构建器中改变消耗 CPU 代码上下文的正确方式
//
//fun main() = runBlocking<Unit> {
//    simple().collect { value ->
//        log("Collected $value")
//    }
//}
//</editor-fold>

//<editor-fold desc="zip操作符">
//fun main() = runBlocking<Unit> {
//
//    val nums = (1..3).asFlow().onEach { delay(300) } // numbers 1..3 every 300 ms
//    val strs = flowOf("one", "two", "three").onEach { delay(400) } // strings every 400 ms
//    val startTime = System.currentTimeMillis() // remember the start time
//    nums.zip(strs) { a, b -> "$a -> $b" } // compose a single string with "zip"
//        .collect { value -> // collect and print
//            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
//        }
//}
//</editor-fold>

fun main() = runBlocking<Unit> {

    val nums = (1..3).asFlow().onEach { delay(300) } // numbers 1..3 every 300 ms
    val strs = flowOf("one", "two", "three").onEach { delay(400) } // strings every 400 ms
    val startTime = System.currentTimeMillis() // remember the start time
    nums.combine(strs) { a, b -> "$a -> $b" } // compose a single string with "combine"
        .collect { value -> // collect and print
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}
//<editor-fold desc="定时器">
//fun countDownCoroutines(
//    scope: CoroutineScope,
//    onTick: (Int) -> Unit,
//    onStart: (() -> Unit)? = null,
//    onFinish: (() -> Unit)? = null,
//): Job {
//    return (1..5).asFlow().map {
//        delay(1000)
//        6-it
//    }.flowOn(Dispatchers.IO)
//        .onStart { onStart?.invoke() }
//        .onCompletion { onFinish?.invoke() }
//        .onEach { onTick.invoke(it) }
//        .launchIn(scope)
//}
//
//fun main() = runBlocking {
//
//        countDownCoroutines(this, onTick = {
//            println("onTick:$it")
//        }, onStart = {
//            println("onStart")
//        }, onFinish = {
//            println("onFinish")
//        })
//    println("done")
//}
//</editor-fold>
