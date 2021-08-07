package com.aliya.kotlin

import com.aliya.kotlin.util.printDivider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

/**
 * 异步流(Flow)
 *
 * 挂起函数可以异步的返回单个值，但是流（Flow）可异步返回多个计算好的值
 *
 * @author a_liYa
 * @date 2020/11/10 09:23.
 *
 */
fun main() {



    sequenceSimple().forEach { value -> println("time: ${System.currentTimeMillis()} - value: $value")}

    printDivider("流的简单演示")

    // 这段代码在不阻塞主线程的情况下每等待 100 毫秒打印一个数字。在主线程中运行一个单独的协程每 100 毫秒打印
    runBlocking<Unit> {
        // 启动并发的协程以验证主线程并未阻塞
        launch {
            for (k in 1..3) {
                println("I'm not blocked $k")
                delay(100)
            }
        }
        // 收集这个流
        flowSimple().collect { value -> println(value) }
    }

//    printDivider("流的执行顺序")
//
//    runBlocking<Unit> {
//        println("Calling simple function...")
//        val flow = flowCallingSimple()
//        println("Calling collect...")
//        flow.collect { value -> println(value) }
//        println("Calling collect again...")
//        flow.collect { value -> println(value) }
//    }
//
//    printDivider("流的取消")
//
//    runBlocking<Unit> {
//        withTimeoutOrNull(250) { // 在 250 毫秒后超时
//            flowCancelSimple().collect { value -> println(value) }
//        }
//        println("Done")
//    }
//
//    printDivider("流的构建器")
//
//    flowBuildSimple()
//
//    printDivider("过度流操作符")
//
//    flowOperatorSimple()

    // 流的该属性称为 上下文保存
}

private fun sequenceSimple(): Sequence<Int> = sequence { // 序列构建器
    for (i in 1..3) {
        Thread.sleep(1000) // 假装我们正在计算
        yield(i) // 产生下一个值
    }
}

/**
 * 注意：
 *  -- 名为 flow 的 Flow 类型构建器函数
 *  -- flow { ... } 构建块中的代码可以挂起
 *  -- 函数 不再标有 suspend 修饰符。
 *  -- 流使用 emit 函数 发射 值
 */
private fun flowSimple(): Flow<Int> = flow { // flow 流构建器
    for (i in 1..3) {
        delay(100) // 假装我们在这里做了一些有用的事情
        // Thread.sleep(100)
        emit(i) // 发送下一个值，
    }
}

/**
 * Flow 是一种类似于序列的冷流，这段 flow 构建器中的代码直到流被收集的时候才运行
 *
 * 冷流：只有订阅者订阅时，才开始执行发射数据流的代码。并且冷流和订阅者只能是一对一的关系，当有多个不同的订阅者时，消息是重新完整发送的。
 * 也就是说对冷流而言，有多个订阅者的时候，他们各自的事件是独立的。
 *
 * 热流：无论有没有订阅者订阅，事件始终都会发生。当热流有多个订阅者时，热流与订阅者们的关系是一对多的关系，可以与多个订阅者共享信息。
 */
private fun flowCallingSimple(): Flow<Int> = flow {
    println("Flow started")
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

private fun flowCancelSimple(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        println("Emitting $i")
        emit(i)
    }
}

/**
 * 流构建器
 * flow { ... } 构建器是最基础的一个
 * flowOf 构建器定义了一个发射固定值集的流
 * 使用 .asFlow() 扩展函数，可以将各种集合与序列转换为流
 */
private fun flowBuildSimple() = runBlocking {

    // 发射一个固定值elements集的流
    flowOf(10, 12, 15).collect { println(it) }

    // 将一个整数区间转化为流
    (1..3).asFlow().collect { value -> println(value) }
}

/**
 * 过渡流操作符
 * 可以使用操作符转换流，就像使用集合与序列一样。 过渡操作符应用于上游流，并返回下游流。
 */
private fun flowOperatorSimple() = runBlocking {

    // 映射操作符
    (1..3).asFlow() // 一个请求流
        .map { request -> performRequest(request) } // 等价 { performRequest(it) }
        .collect { response -> println(response) } // 等价 { println(it) }

    // 转换操作符
    (1..3).asFlow() // 一个请求流
        .transform { request ->
            // transform 操作符中，我们可以 发射 任意值 任意次
            emit("Making request $request")
            emit(performRequest(request))
        }
        .collect { println(it) }

    // 限长操作符
    flow {
        /**
         * 协程中的取消操作总是通过抛出异常来执行
         * 这样所有的资源管理函数（如 try {...} finally {...} 块）会在取消的情况下正常运行
         */
        try {
            emit(1)
            emit(2)
            println("This line will not execute")
            emit(3)
        } finally {
            println("Finally in numbers")
        }
    }
        .take(2)
        .collect { println(it) }

    // 末端流操作符
    val sum = (1..5).asFlow()
        .reduce { a, b -> a + b } // 求和（末端操作符）
    println(sum)


    // 流是连续的，流的每次单独收集都是按顺序执行的
    (1..5).asFlow()
        .filter {
            println("Filter $it")
            it % 2 == 0
        }
        .map {
            println("Map $it")
            "string $it"
        }.collect {
            println("Collect $it")
        }
}

private suspend fun performRequest(request: Int): String {
    delay(100) // 模仿长时间运行的异步工作
    return "response $request"
}

