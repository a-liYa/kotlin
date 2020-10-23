package com.aliya.kotlin

import com.aliya.kotlin.util.printDivider

/**
 * 集合取操作
 *
 * @author a_liYa
 * @date 2020/10/23 14:21.
 *
 */
fun main() {

    printDivider(" slice ")

    sliceOpt()

    printDivider(" take ")

    takeOpt()

    printDivider(" drop ")

    dropOpt()

    printDivider(" chunded ")

    chunkedOpt()

    printDivider(" windowed ")

    windowedOpt()

    printDivider(" zipWith ")

    zipWindowOpt()
}

/**
 * slice() 返回具有给定索引的集合元素列表
 */
private fun sliceOpt() {

    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.slice(1..3))
    println(numbers.slice(0..4 step 2))
    println(numbers.slice(setOf(3, 5, 0)))
}

/**
 * 要从头开始获取指定数量的元素，请使用 take() 函数。 要从尾开始获取指定数量的元素，请使用 takeLast()
 */
private fun takeOpt() {
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.take(3))
    println(numbers.takeLast(3))

    // 取集合元素直到 拖尾函数返回 false
    println(numbers.takeWhile { !it.startsWith('f') })
    println(numbers.takeLastWhile { it != "three" })
}

/**
 * 要从头或从尾去除给定数量的元素，请调用 drop() 或 dropLast() 函数
 */
private fun dropOpt() {
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.drop(3))
    println(numbers.dropLast(3))

    println(numbers.dropWhile { it.length == 3 })
    println(numbers.dropLastWhile { it.contains('i') })
}

/**
 * 指定大小进行分块
 */
private fun chunkedOpt() {
    val numbers = (0..13).toList()
    println(numbers.chunked(3))

    println(numbers.chunked(3) { it.sum() })  // `it` 为原始集合的一个块
}

/**
 * 检索给定大小的集合区间
 */
private fun windowedOpt() {
    // 可以检索给定大小的集合元素中所有可能区间
    val numbers = listOf("one", "two", "three", "four", "five", 6, 7, 8)
    println(numbers.windowed(3))

    // step, 相邻窗口的第一个元素之间的距离; partialWindows = true, 最后一个窗口不足 3 个时也进行分块
    println(numbers.windowed(3, step = 2, partialWindows = false))
}

/**
 * 构建两个元素的窗口
 */
private fun zipWindowOpt() {
    val numbers = listOf("one", "two", "three", "four", "five")
    println(numbers.zipWithNext())
    println(numbers.zipWithNext() { s1, s2 -> s1.length > s2.length})
}