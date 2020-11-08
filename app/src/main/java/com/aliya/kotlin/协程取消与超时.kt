package com.aliya.kotlin

import kotlinx.coroutines.*

/**
 * 协程取消与超时
 *
 * @author a_liYa
 * @date 2020/11/8 22:15.
 *
 */
fun main() {

//    cancelCoroutines()

//    cancelTest()

//    cancelHandle()

//    timeoutTest()

    timeoutOrNullTest()
}

// 取消协程，取消时抛出 CancellationException
private fun cancelCoroutines() {
    runBlocking {
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            } catch (e: CancellationException) {
                println("协程取消异常")
            } finally {
                // 一般在此释放资源

                // 协程已经取消，finally 块调用挂起函数依然会抛 CancellationException，
                // 当需要挂起一个被取消的协程，将相应的代码包装在 withContext(NonCancellable) {……} 中
                println("job: I'm running finally")
                withContext(NonCancellable) {
                    delay(1000L)
                    println("job: And I've just delayed for 1 sec because I'm non-cancellable")
                }
            }

        }
        delay(1300L) // 延迟一段时间
        println("main: I'm tired of waiting!")
        job.cancel() // 取消该作业
        job.join() // 等待作业执行结束
        println("main: Now I can quit.")
    }
}


// 协程的取消是 协作 的。一段协程代码必须协作才能被取消。
private fun cancelTest() {
    runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (i < 10) { // 一个执行计算的循环，只是为了占用 CPU
                // 每秒打印消息两次
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }
        delay(1300L) // 等待一段时间
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // 取消一个作业并且等待它结束
        println("main: Now I can quit.")
    }
}

// 使计算代码可取消
private fun cancelHandle() {
    runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (isActive) { // 可以被取消的计算循环
                // 每秒打印消息两次
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }
        delay(1300L) // 等待一段时间
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // 取消该作业并等待它结束
        println("main: Now I can quit.")
    }
}


// 此代码会抛崩溃异常 TimeoutCancellationException
private fun timeoutTest() = runBlocking {
    withTimeout(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
}

// 使用 withTimeoutOrNull 内部拦截崩溃
private fun timeoutOrNullTest() = runBlocking {
    val result = withTimeoutOrNull(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
        "Done" // 在它运行得到结果之前取消它
    }
    println("Result is $result")

}