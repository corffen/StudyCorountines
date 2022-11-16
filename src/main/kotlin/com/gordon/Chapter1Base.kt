package com.gordon

import kotlinx.coroutines.*
import java.lang.Thread.sleep
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

//<editor-fold desc="launch启动协程程序">
//fun main() {
//    GlobalScope.launch { // 在后台启动一个新的协程并继续
//        delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
//        println("World!") // 在延迟后打印输出
//    }
//    println("Hello,") // 协程已在等待时主线程还在继续
////    sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
//}
//</editor-fold>

//<editor-fold desc="async启动协程">
//fun main() = runBlocking<Unit> {
//    val a = async {
//        log("I'm computing a piece of the answer")
//        delay(1000)
//        6
//    }
//    log("The answer is ${a.await()}")
//}
//</editor-fold>
//<editor-fold desc="runBlocking启动协程">

//fun main() = runBlocking { // 开始执行主协程
//    GlobalScope.launch { // 在后台启动一个新的协程并继续
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello,") // 主协程在这里会立即执行
//    delay(2000L)      // 延迟 2 秒来保证 JVM 存活
//}

//</editor-fold>

//<editor-fold desc="Join协程">
//fun main() = runBlocking {
//    val job = GlobalScope.launch { // 启动一个新协程并保持对这个作业的引用
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello,")
//    job.join() // 等待直到子协程执行结束
//}

//fun main() = runBlocking {
//   launch { // 启动一个新协程并保持对这个作业的引用
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello,")
//}
//</editor-fold>

//<editor-fold desc="结构化并发">
//fun main() = runBlocking {
//    val parentJob: Job
//    var job1: Job? = null
//    var job2: Job? = null
//    var job3: Job? = null
//    parentJob = launch {
//        job1 = launch {
//            delay(500L)
//            log("job1 done")
//        }
//        job2 = launch {
//            delay(300L)
//            log("job2 done")
//        }
//        job3 = launch {
//            delay(100L)
//            log("job3 done")
//        }
//    }
//    delay(20L)
//    parentJob.children.forEachIndexed { index, job ->
//        when (index) {
//            0 -> println("job1 === job is ${job1 === job}")
//            1 -> println("job2 === job is ${job2 === job}")
//            2 -> println("job3 === job is ${job3 === job}")
//        }
//    }
//    parentJob.join()
//    log("Process end!")
//}
//</editor-fold>


//<editor-fold desc="作用域构建器">
//fun main() = runBlocking { // this: CoroutineScope
//    launch {
//        delay(200L)
//        log("Task from runBlocking")
//    }
////注意这个是挂起函数
//    coroutineScope { // 创建一个协程作用域
//        launch {
//            delay(500L)
//            log("Task from nested launch")
//        }
//
//        delay(100L)
//        log("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
//    }
//
//    log("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
//}
//</editor-fold>

//<editor-fold desc="协程很轻量">
//fun main() = runBlocking {
//    repeat(100_000) { // 启动大量的协程
//        launch {
//            delay(2000L)
//            print(".")
//        }
//    }
//}

//</editor-fold>
//<editor-fold desc="10万个线程">
//fun main() {
//    val consumeTime = measureTimeMillis {
//        repeat(100_000) { // 启动大量的线程
//            thread {
//                sleep(2000L)
//                print(".")
//            }
//        }
//    }
//    println("consumeTime:$consumeTime")
//}


//</editor-fold>

//<editor-fold desc="全局协程就像守护线程">
//fun main() = runBlocking {
//    GlobalScope.launch {
//        repeat(1000) { i ->
//            println("I'm sleeping $i ...")
//            delay(500L)
//        }
//    }
//    delay(1300L) // 在延迟后退出
//}
//</editor-fold>
