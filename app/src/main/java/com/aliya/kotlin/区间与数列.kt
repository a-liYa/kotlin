package com.aliya.kotlin

/**
 * 区间与数列
 *
 * @author a_liYa
 * @date 2020/10/22 15:51.
 *
 */
fun main() {

    rangeToSample(2)
}

// rangeTo() 函数及其操作符形式的 .. 表示区间
fun rangeToSample(i: Int) {

    if (i in 1..4) {  // 等同于 1 <= i && i <= 4
        print(i)
    }

    println("\n")

    // 另一个特性：迭代
    for (i in 1..4) print(i) // 等同于 1.rangeTo(4) 和 1 rangeTo 4

    println("\n")

    // 反向迭代 downTo
    for (i in 4 downTo 1)  // 等同于 4.downTo(1)
        print(i)

    // 函数 step 定义 增步长 2
    for (i in 1..8 step 2) print(i)

    println("\n")

    // 函数 step 定义 增步长 2
    for (i in 8 downTo 1 step 2) print(i)

    println("\n")

    // i in [1, 10), 10被排除
    for (i in 1 until 10) {
        print(i)
    }

    println("\n")

    // 以上示例，可视为等差数列，
    // 数列实现 Iterable<N>，其中 N 分别是 Int、Long 或 Char，因此可以在各种集合函数（如 map、filter 与其他）中使用它们。
    println((1..10).filter { it % 2 == 0 })

}