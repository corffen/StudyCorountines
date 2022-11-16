package com.gordon

import kotlinx.coroutines.*

//<editor-fold desc="调度器和线程">
//fun main() = runBlocking<Unit> {
//    launch { // 运行在父协程的上下文中，即 runBlocking 主协程
//        println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
//    }
//    launch(Dispatchers.Unconfined) { // 不受限的——将工作在主线程中
//        println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
//    }
//    /**
//     * 默认线程池跟GlobalScope使用的就是默认线程调度器
//     * 后台共享的线程池
//     */
//    launch(Dispatchers.Default) { // 将会获取默认调度器
//        println("Default               : I'm working in thread ${Thread.currentThread().name}")
//    }
//    launch(newSingleThreadContext("MyOwnThread")) { // 将使它获得一个新的线程
//        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
//    }
//}
//</editor-fold>

//<editor-fold desc="未指定的协程调度器">
//fun main() = runBlocking<Unit> {
//    launch(Dispatchers.Unconfined) { // 非受限的——将和主线程一起工作
//        println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
//        delay(500)
//        println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
//    }
//    launch { // 父协程的上下文，主 runBlocking 协程
//        println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
//        delay(1000)
//        println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
//    }
//}
//</editor-fold>

val threadLocal = ThreadLocal<String?>() // 声明线程局部变量

fun main() = runBlocking<Unit> {
    threadLocal.set("main")
    println("Pre-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
    val job = launch(Dispatchers.Default + threadLocal.asContextElement(value = "launch")) {
        println("Launch start, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        yield()
        println("After yield, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
    }
    job.join()
    println("Post-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
}