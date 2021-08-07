package com.aliya.kotlin.coroutines.flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 * ChannelFlow
 *
 * @author a_liYa
 * @date 2021/8/7 11:27.
 *
 */
fun main() {

//    consumer()
    consumer2()
}

/**
 * 通过Kotlin的Flow可以轻松实现生产者消费者模型。
 * Flow默认是Cold的，生产者和消费者的通信是**同步非阻塞**的，也就是生产和消费会顺序交替进行
 */
private suspend fun producer() = flow<Int> {
    for (i in 1..10) {
        delay(100)
        println("produce $i")
        emit(i)
    }
}

private fun consumer() {
    runBlocking {
        producer().collect {
            delay(200)
            println("consumption $it")
        }
    }
}

/**
 * channelFlow 实现异步非阻塞模型
 * collect之后才触发生产,生产和消费两端实现了并行执行
 */
private suspend fun producer2() = channelFlow<Int> {
    for (i in 1..10) {
        delay(100)
        send(i) //emit 变为 send
        println("produce $i")
    }
}

private fun consumer2() {
    runBlocking {
        producer2().collect {
            delay(500)
            println("consumption $it")
        }
    }
}




