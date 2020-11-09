package com.aliya.kotlin

import com.aliya.kotlin.util.printDivider
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * 协程组合挂起
 *
 * @author a_liYa
 * @date 2020/11/9 22:07.
 *
 */
fun main() {

    // 循序调用
    runBlocking<Unit> {
        val time = measureTimeMillis {
            val one = doSomethingUsefulOne()
            val two = doSomethingUsefulTwo()
            println("The answer is ${one + two}")
        }
        println("Completed in $time ms")
    }

    // 并发调用 async
    runBlocking {
        /**
         * 概念上，async 就类似于 launch,它启动了一个单独的协程,与其它所有的协程一起并发的工作。
         * 不同: launch 返回一个 Job 并且不附带任何结果值, async 返回一个 Deferred,一个轻量级的非阻塞 future
         * Deferred 也是一个 Job,
         */
        val time = measureTimeMillis {
            val oneDeferred = async { doSomethingUsefulOne() }
            val twoDeferred = async { doSomethingUsefulTwo() }
            println("The answer is ${oneDeferred.await() + twoDeferred.await()}")
        }
        println("Completed in $time ms")
    }

    // 惰性启动的 async
    runBlocking {
        val time = measureTimeMillis {
            // 可选的, async 可以通过将 start 参数设置为 CoroutineStart.LAZY 而变为惰性的
            val oneDeferred = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
            val twoDeferred = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
            // 执行一些计算
            oneDeferred.start() // 启动第一个
            twoDeferred.start() // 启动第二个
            println("The answer is ${oneDeferred.await() + twoDeferred.await()}")
        }
        println("Completed in $time ms")
    }

    printDivider("async 风格的函数，强烈不推荐")

    // async 风格的函数，这种风格在 Kotlin 中是强烈不推荐。
    asyc风格函数()

    printDivider("使用 async 的结构化并发")

    // 使用 async 的结构化并发
    asyncConcurrentSample()

    printDivider("取消始终通过协程的层次结构来进行传递")

    runBlocking<Unit> {
        try {
            failedConcurrentSum()
        } catch(e: ArithmeticException) {
            println("Computation failed with ArithmeticException")
        }
    }
}

private suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // 假设我们在这里做了一些有用的事
    return 13
}

private suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // 假设我们在这里也做了一些有用的事
    return 29
}


// somethingUsefulOneAsync 函数的返回值类型是 Deferred<Int>
private fun somethingUsefulOneAsync() = GlobalScope.async {
    doSomethingUsefulOne()
}

// somethingUsefulTwoAsync 函数的返回值类型是 Deferred<Int>
private fun somethingUsefulTwoAsync() = GlobalScope.async {
    doSomethingUsefulTwo()
}

private fun asyc风格函数() {
    val time = measureTimeMillis {
        // 我们可以在协程外面启动异步执行
        val oneDeferred = somethingUsefulOneAsync()
        val twoDeferred = somethingUsefulTwoAsync()
        // 但是等待结果必须调用其它的挂起或者阻塞
        // 当我们等待结果的时候，这里我们使用 `runBlocking { …… }` 来阻塞主线程
        runBlocking {
            println("The answer is ${oneDeferred.await() + twoDeferred.await()}")
        }
    }
    println("Completed in $time ms")
}

private fun asyncConcurrentSample() = runBlocking {
    val time = measureTimeMillis {
        println("The answer is ${concurrentSum()}")
    }
    println("Completed in $time ms")
}


// 使用 async 的结构化并发
private suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}

// 注意，如果其中一个子协程（即 two）失败，第一个 async 以及等待中的父协程都会被取消
suspend fun failedConcurrentSum(): Int = coroutineScope {
    val one = async<Int> {
        try {
            delay(Long.MAX_VALUE) // Emulates very long computation
            42
        } finally {
            println("First child was cancelled")
        }
    }
    val two = async<Int> {
        println("Second child throws an exception")
        throw ArithmeticException()
    }
    one.await() + two.await()
}