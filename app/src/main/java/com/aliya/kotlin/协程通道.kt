package com.aliya.kotlin

import com.aliya.kotlin.util.printDivider
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce

/**
 * 协程通道
 *
 * @author a_liYa
 * @date 2020/11/11 22:33.
 *
 */
fun main() {

    channelSample()

    printDivider("关闭与迭代通道")

    channelCloseSample()

    printDivider("构建通道生产者")

    buildChannelProduce()

    printDivider("")

    startConnectChannel()

    printDivider("利用管道生成素数")

    channelPrimeNumber()

    printDivider("带缓冲的管道")

    channelBufferSample()
}

/**
 * 一个 Channel 是一个和 BlockingQueue 非常相似的概念。
 * 挂起的 send 代替阻塞的 put 操作，挂起的 receive 替代了阻塞的 take 操作。
 */
private fun channelSample() = runBlocking {
    val channel = Channel<Int>()
    launch {
        // 这里可能是消耗大量 CPU 运算的异步逻辑，我们将仅仅做 5 次整数的平方并发送
        for (x in 1..5) {
            channel.send(x * x)
        }
    }
    // 这里我们打印了 5 次被接收的整数：
    repeat(5) { println(channel.receive()) }
    println("Done!")
}

// 关闭与迭代通道
private fun channelCloseSample() = runBlocking {
    val channel = Channel<Int>()
    launch {
        for (x in 1..5) channel.send(x * x)
        println("close channel before")
        channel.close() // 我们结束发送
        println("close channel after")
    }
// 这里我们使用 `for` 循环来打印所有被接收到的元素（直到通道被关闭）
    for (y in channel) {
        delay(100)
        println(y)
    }
    println("Done!")
}

private fun buildChannelProduce() = runBlocking {
    val squares = produceSquares()
    squares.consumeEach { println(it) }
    println("Done!")
}

private fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce {
    for (x in 1..5) send(x * x)
}


// 管道是一种一个协程在流中开始生产可能无穷多个元素的模式：
private fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1
    while (true) send(x++) // 在流中开始从 1 生产无穷多个整数
}

private fun CoroutineScope.square(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
    for (x in numbers) send(x * x)
}

private fun startConnectChannel() = runBlocking {
    val numbers = produceNumbers() // 从 1 开始生成整数
    val squares = square(numbers) // 整数求平方
    repeat(5) {
        println(squares.receive()) // 输出前五个
    }
    println("Done!") // 至此已完成
    coroutineContext.cancelChildren() // 取消子协程
}

private fun CoroutineScope.numbersFrom(start: Int) = produce<Int> {
    var x = start
    while (true) send(x++) // 开启了一个无限的整数流
}

private fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) = produce<Int> {
    for (x in numbers) if (x % prime != 0) send(x)
}

private fun channelPrimeNumber() = runBlocking {
    var cur = numbersFrom(2)
    repeat(10) {
        val prime = cur.receive()
        println(prime)
        cur = filter(cur, prime)
    }
    coroutineContext.cancelChildren() // 取消所有的子协程来让主协程结束
}

// 扇出 多个协程也许会接收相同的管道

// 扇入 多个协程可以发送到同一个通道

/**
 * 无缓冲的通道在发送者和接收者相遇时传输元素（也称“对接”）。
 *  -- 如果发送先被调用，则它将被挂起直到接收被调用， 如果接收先被调用，它将被挂起直到发送被调用。
 *
 * 带缓冲的通道
 */

private fun channelBufferSample() = runBlocking {
    val channel = Channel<Int>(4) // 启动带缓冲的通道
    val sender = launch { // 启动发送者协程
        repeat(10) {
            println("Sending $it") // 在每一个元素发送前打印它们
            channel.send(it) // 将在缓冲区被占满时挂起
        }
    }
    // 没有接收到东西……只是等待……
    delay(1000)
    sender.cancel() // 取消发送者协程
}


// 通道是公平的