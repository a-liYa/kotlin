package com.aliya.kotlin

/**
 * 集合分组
 *
 * @author a_liYa
 * @date 2020/10/23 14:09.
 *
 */

fun main() {

    groupBy()
}

// 分组
private fun groupBy() {
    val numbers = listOf("one", "two", "three", "four", "five")

    // groupBy 分组结果 key value(List<String>)
    println(numbers.groupBy { it.first() })
    println(numbers.groupBy(keySelector = { it.first() }, valueTransform = { it.toUpperCase() }))

    // groupingBy 惰性分组
    val groupingBy = numbers.groupingBy { it.first() }
    println("惰性分组，执行实际操作前应用分组")
    println(groupingBy.eachCount())
}