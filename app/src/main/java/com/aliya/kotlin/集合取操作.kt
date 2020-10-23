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

    printDivider(" elementAt ")

    elementAt()

    printDivider(" firstOrLast ")

    firstOrLast()

    printDivider(" contains ")

    contains()
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

/**
 * 按位置取，Set集合会用到
 */
private fun elementAt() {
    val numbers = linkedSetOf("one", "two", "three", "four", "five")
    println(numbers.elementAt(3))

    val numbersSortedSet = sortedSetOf("one", "two", "three", "four")
    println(numbersSortedSet.elementAt(0)) // 元素以升序存储

    // 避免位置越界异常，可使用 elementAt() 的安全变体
    println(numbers.elementAtOrNull(5))
    // 越界时会 返回拖尾函数给定的值
    println(numbers.elementAtOrElse(5) { index -> "The value for index $index is undefined"})
}

/**
 * 按条件取
 */
private fun firstOrLast() {
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.first { it.length > 3 })
    println(numbers.last { it.startsWith("f") })

    // 如果没有元素与谓词匹配，两个函数都会抛异常,规避请改用
    println(numbers.firstOrNull { it.length > 6 })
    println(numbers.lastOrNull { it.length > 6 })
    // 等价于 以下两行代码
    println(numbers.find { it.length > 6 })
    println(numbers.findLast { it.length > 6 })
}

/**
 * 检测存在与否
 */
private fun contains() {
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.contains("four"))
    println("zero" in numbers)

    println(numbers.containsAll(listOf("four", "two")))
    println(numbers.containsAll(listOf("one", "zero")))
}