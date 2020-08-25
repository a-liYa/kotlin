package com.aliya.kotlin

/**
 * Range 区间使用示例
 *
 * @author a_liYa
 * @date 2020/8/25 17:08.
 *
 */
fun main() {

    // 闭区间：包含 10
    for (i in 1..10) {
        print("$i, ")
    }

    println("\n")

    // 半开区间：不包含 10
    for (i in 1 until 10) {
        print("$i, ")
    }

    println("\n")

    for (x in 1..10 step 2) {
        print("$x, ")
    }

    println("\n")

    for (x in 10 downTo 1) {
        print("$x, ")
    }

    println("\n")
}
