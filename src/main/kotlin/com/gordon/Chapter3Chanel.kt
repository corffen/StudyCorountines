package com.gordon

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.suspendCoroutine

//<editor-fold desc="通道的基本使用">

//fun main() = runBlocking {
//    val channel = Channel<Int>() //类似于BlockingQueue
//    launch {
//        // 这里可能是消耗大量 CPU 运算的异步逻辑，我们将仅仅做 5 次整数的平方并发送
//        for (x in 1..5) channel.send(x * x) //相当于put数据
//    }
//    // 这里我们打印了 5 次被接收的整数：
//    repeat(5) { println(channel.receive()) } //receive()相当于take
//    println("Done!")
//}

//</editor-fold>

//<editor-fold desc="通道的关闭">

//fun main() = runBlocking {
//    val channel = Channel<Int>()
//    launch {
//        for (x in 1..5) channel.send(x * x)
//        channel.close() // 我们结束发送
//    }
//    // 这里我们使用 `for` 循环来打印所有被接收到的元素（直到通道被关闭）
//    for (y in channel) println(y)
//    println("Done!")
//}

//</editor-fold>

//<editor-fold desc="通道上的数据处理">

fun main() = runBlocking {
    val numbers = produceNumbers() // 从 1 开始生成整数
    val squares = square(numbers) // 整数求平方
    repeat(5) {
        println(squares.receive()) // 输出前五个
    }
    println("Done!") // 至此已完成
    coroutineContext.cancelChildren() // 取消子协程

}

fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1
    while (true) send(x++) // 从 1 开始的无限的整数流
}

fun CoroutineScope.square(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
    for (x in numbers) send(x * x)
}

//</editor-fold>


//<editor-fold desc="取出前10个素数">
//fun main() = runBlocking {
//    var cur = numbersFrom(2)
//    repeat(10) {
//        val prime = cur.receive()
//        println(prime)
//        cur = filter(cur, prime)
//    }
//    coroutineContext.cancelChildren() // 取消所有的子协程来让主协程结束
//}
//
//fun CoroutineScope.numbersFrom(start: Int) = produce<Int> {
//    var x = start
//    while (true) {
//        send(x) // 从 start 开始过滤整数流
//        println("send x=$x")
//        x++
//    }
//}
//
//fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) = produce<Int> {
//    for (x in numbers) {
//        println("filter x=$x,prime=$prime")
//        if (x % prime != 0) {
//            println("prime x=$x")
//            send(x)
//        }
//    }
//}
//</editor-fold>