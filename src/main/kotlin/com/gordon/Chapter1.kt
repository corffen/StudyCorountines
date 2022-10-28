package com.gordon

import kotlinx.coroutines.*
import java.lang.Thread.sleep
import kotlin.concurrent.thread

//<editor-fold desc="第一个协程程序">
//fun main() {
//    GlobalScope.launch { // 在后台启动一个新的协程并继续
//        delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
//        println("World!") // 在延迟后打印输出
//    }
//    println("Hello,") // 协程已在等待时主线程还在继续
//    Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
//}
//</editor-fold>


//<editor-fold desc="非阻塞与阻塞协程">
//fun main() {
//    GlobalScope.launch { // 在后台启动一个新的协程并继续
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello,") // 主线程中的代码会立即执行
//    runBlocking {     // 但是这个表达式阻塞了主线程
//        launch {
//            delay(4000)
//            println("runBlocking inner start coroutines")
//        }
//        delay(2000L)  // ……我们延迟 2 秒来保证 JVM 的存活
//    }
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
//</editor-fold>

//<editor-fold desc="结构化并发">
//fun main() = runBlocking { // this: CoroutineScope
//    launch { // 在 runBlocking 作用域中启动一个新协程
//        delay(1000L)
//        println("World!")
//    }
//
//    launch { // 在 runBlocking 作用域中启动一个新协程
//        delay(500L)
//        println("World 2!")
//    }
//
//    launch { // 在 runBlocking 作用域中启动一个新协程
//        delay(200L)
//        println("World 3!")
//    }
//    println("Hello,")
//}
//</editor-fold>


//<editor-fold desc="作用域构建器">
//fun main() = runBlocking { // this: CoroutineScope
//    launch {
//        delay(200L)
//        println("Task from runBlocking")
//    }
//
//    coroutineScope { // 创建一个协程作用域
//        launch {
//            delay(500L)
//            println("Task from nested launch")
//        }
//
//        delay(100L)
//        println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
//    }
//
//    println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
//}
//</editor-fold>

//<editor-fold desc="协程很轻量">
//fun main() = runBlocking {
//    repeat(100_000) { // 启动大量的协程
//        launch {
//            delay(5000L)
//            print(".")
//        }
//    }
//}

//</editor-fold>
//<editor-fold desc="10万个线程">
//fun main() = runBlocking {
//    repeat(100_000) { // 启动大量的协程
//        thread {
//            sleep(5000L)
//            print(".")
//        }
//    }
//}
//</editor-fold>

//<editor-fold desc="全局协程就像守护线程">
fun main() = runBlocking {
    GlobalScope.launch {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // 在延迟后退出
}
//</editor-fold>
