package com.aliya.kotlin

import kotlinx.coroutines.*
import kotlin.concurrent.thread

/**
 * 协程 coroutines
 *
 * 协程设计的初衷是为了解决并发问题，让「协作式多任务」实现起来更加方便
 *
 * @author a_liYa
 * @date 2020/11/7 21:13.
 *
 */
fun main() {

//    coroutinesSample()
//
//    ofEqualValueThread()
//
//    ofEqualValueRunBlocking()
//
//    ofEqualValueJob()
//
//    ofEqualValue4()
//
//    作用域构建器()

//    repeatCoroutines(10000)

    runBlocking<Unit> {
        GlobalScope.launch {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        }
        delay(1300L) // 在延迟后退出
    }
}

fun coroutinesSample() {
    GlobalScope.launch { // 在后台启动一个新的协程并继续
        delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
        println("World!${Thread.currentThread().name}") // 在延迟后打印输出
    }
    println("Hello,${Thread.currentThread().name}") // 协程已在等待时主线程还在继续
    runBlocking {     // 但是这个表达式阻塞了主线程
        delay(2000L)  // ……我们延迟 2 秒来保证 JVM 的存活
    }
}

//  等价1
fun ofEqualValueThread() {
    thread {
        Thread.sleep(1000)
        println("_World!")
    }
    println("_Hello,")
    Thread.sleep(2000L)
}

// 等价2
fun ofEqualValueRunBlocking() {
    runBlocking<Unit> { // 开始执行主协程
        GlobalScope.launch { // 在后台启动一个新的协程并继续
            delay(1000L)
            println("World!")
        }
        println("Hello,") // 主协程在这里会立即执行
        delay(2000L)      // 延迟 2 秒来保证 JVM 存活
    }
}


// 等价3
fun ofEqualValueJob() {
    runBlocking<Unit> {
        val job = GlobalScope.launch { // 启动一个新协程并保持对这个作业的引用
            delay(1000L)
            println("_World!")
        }
        println("_Hello,")
        job.join() // 等待直到子协程执行结束
    }
}

// 等价4 结构化的并发
fun ofEqualValue4() {
    runBlocking<Unit> {
        launch {// 在 runBlocking 作用域中启动一个新协程
            delay(1000L)
            println("4_World!")
        }
        println("4_Hello,")
        // 作用域中省略了 job.join()
    }
}

/**
 * 作用域构建器
 * runBlocking 与 coroutineScope 异同
 *  -- 它们都会等待其协程体以及所有子协程结束。
 *  -- runBlocking 方法会阻塞当前线程来等待， 而 coroutineScope 只是挂起，会释放底层线程用于其他用途。
 *  -- 由于存在这点差异，runBlocking 是常规函数，而 coroutineScope 是挂起函数。
 */
fun 作用域构建器() {
    runBlocking {
        launch {
            delay(200L)
            println("Task from runBlocking")
        }

        coroutineScope { // 创建一个协程作用域
            launch {
                delay(500L)
                println("Task from nested launch")
            }

            delay(100L)
            println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
        }

        println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
    }
}

fun repeatCoroutines(times: Int) {
    runBlocking {
        repeat(times) { // 启动大量的协程
            launch {
                delay(5000L)
                print(".${Thread.currentThread().name}")
            }
        }
    }
}

