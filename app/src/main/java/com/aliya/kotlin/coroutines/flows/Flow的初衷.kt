package com.aliya.kotlin.coroutines.flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Flow 解决了什么问题？
 *
 * 官方的解释：挂起函数可以异步的返回单个值，但是该如何异步返回多个计算好的值呢？这正是 Kotlin 流（Flow）的用武之地。
 *
 * @author a_liYa
 * @date 2021/8/7 9:22.
 *
 */
fun main() {
//    returnValues1()

//    returnValues2()

//    returnValues3()

    returnValues4()

}

suspend fun simple(): List<Int> {
    delay(1000) // 假装我们在这里做了一些异步的事情
    return listOf(1, 2, 3)
}


/**
 * 返回多个值的方式
 */

// 方式一：集合
private fun returnValues1() {

    // simple() 通过集合达到**一次性**返回多个值的目的
    fun simple(): List<Int> = listOf(1, 2, 3)
    simple().forEach { value -> println(value) }
}


// 方式二：序列
private fun returnValues2() {

    // simple() 通过 sequence 对单个数值**同步**计算后逐条返回
    fun simple(): Sequence<Int> = sequence { // 序列构建器
        for (i in 1..3) {
            Thread.sleep(100) // 假装我们正在计算，阻塞UI线程
            yield(i) // 产生下一个值
        }
    }
    simple().forEach { value -> println(value) }
}

// 方式三：挂起函数
private fun returnValues3() {

    suspend fun simple(): List<Int> {
        delay(1000) // 假装我们在这里做了一些异步的事情
        return listOf(1, 2, 3)
    }

    runBlocking {
        simple().forEach { value -> println(value) }
    }

}


// 方式四：Flow
private fun returnValues4() {

    fun simple(): Flow<Int> = flow { // 流构建器
        for (i in 1..3) {
            delay(100) // 假装我们在这里做了一些有用的事情
            emit(i) // 发送下一个值
        }
    }

    runBlocking<Unit> {
        // 启动并发的协程以验证主线程并未阻塞
        launch {
            for (k in 1..3) {
                println("I'm not blocked $k")
                delay(100)
            }
        }
        // 收集这个流
        simple().collect { value -> println(value) }
    }

}
