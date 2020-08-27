package com.aliya.kotlin

import kotlin.math.max

/**
 * 控制流：if、when、for、while
 *
 * @author a_liYa
 * @date 2020/8/23 23:17.
 *
 */
fun main() {
    ifShow(1, 5)
    whenShow(6)
    forShow()
    whileShow()
}

// if 表达式/语句
fun ifShow(a: Int, b: Int) {

    // 传统用法，if 语句
    var max = a
    if (a < b) max = b

    println("max = $max")

    // if 表示的三目运算符
    val maxOf = { a: Int, b: Int -> if (a > b) a else b }
    println("maxOf($a, $b) = " + maxOf(a, b))

    // if 的分支可以是代码块，最后的表达式作为该块的值
    max = if (a > b) {
        print("Choose a")
        a
    } else {
        print("Choose b")
        b
    }
    println(" max = $max")

}

// when 表达式/语句
fun whenShow(obj: Any) {

    // 作为表达式使用，必须有 else 分支，除非编译器能够检出所有可能情况都已覆盖
    val whenResult = when (obj) {
        1 -> "One"
        2, 3, 4, 5, 6 -> {
            // 代码块，这里可以做一些运算，最后一行为返回值
            "2..6"
        }
        in 100..200 -> "$obj in 100..200"
        "Hello" -> "Greeting"
        is Long -> "Long"
        !is String -> "Not a string"
        else -> "Unknown"
    }
    println("when($obj) = $whenResult")

    // when 也可以用来取代 if-else if 链.
    when {
        obj == "old" -> println("when -> 1")
        obj.hashCode() == 0 -> println("when -> 2")
        else -> println("when else.")
    }

    // 自 Kotlin 1.3 起，when 所判断的表达式捕获到变量中，在 when 主语中引入的变量的作用域仅限于 when 主体。
    when (val response : Any = max(1, obj.hashCode())) {
        is Int -> println("$response is Int")
        is Long -> println("$response is Long")
    }
}

// for 循环
fun forShow() {
    val items = listOf("apple", "banana", "kiwifruit")

    for (item in items) {
        println(item)
    }
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
}

// while 循环
fun whileShow() {
    val items = listOf("apple", "banana", "kiwifruit")
    var index = 0
    while (index < items.size) {
        println("while item at $index is ${items[index]}")
        index++
    }

    index = items.size - 1
    do {
        println("do while item at $index is ${items[index]}")
    } while (--index >= 0)
}

// 在循环中 Kotlin 支持传统的 break 与 continue 操作符

