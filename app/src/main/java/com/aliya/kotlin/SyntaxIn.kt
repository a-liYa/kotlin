package com.aliya.kotlin

/**
 * Syntax In
 *
 * @author a_liYa
 * @date 2020/8/23 22:41.
 *
 */
fun main() {

    // 1 到 9
    for (i in 1..9) {
         print(i)
    }

    println()

    // 不会输出
    for (i in 9..1) {
         print(i)
    }

    println()

    // 大 到 小
    var max = 9
    for (i in max downTo 1) {
         print(i)
    }

    println()

    // 用区间做判断
    val value = 88
    if (value in 1..100) {
         print("$value 在 1 到 100")
    }

    println()

    // 步长指定
    for (i in 1..10 step 2) {
        // 1 3 5 7 ...
         print(i)
    }

    println()

    // 排除 最后元素
    for (i in 1 until 10) {
        print(i)
    }
}

fun rangeShow() {
    val y = 9
    if (1 in 1..y + 1) {
        println("1 fits in range")
    }
    if (0 !in 1..y + 1) {
        println("0 not fits in range")
    }

    // 区间迭代
    for (x in 1..5) {
        print(x)
    }
    println("\n1 -> 10 跳2步")
    for (x in 1..10 step 2) {
        print(x)
    }
    println("\n9 -> 0 降序跳3步迭代")
    for (x in 9 downTo 0 step 3) {
        print(x)
    }
}