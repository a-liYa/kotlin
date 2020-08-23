package com.aliya.kotlin

/**
 * Conditional
 *
 * @author a_liYa
 * @date 2020/8/23 23:17.
 *
 */
fun main() {

}

// if表示的三目运算符
fun maxOf(a: Int, b: Int): Int {
    return if (a > b) a else b
}

// when 表达式
fun whenShow(obj: Any): String =
    when (obj) {
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



