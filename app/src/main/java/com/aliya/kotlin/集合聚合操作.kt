package com.aliya.kotlin

import com.aliya.kotlin.util.printDivider

/**
 * 集合聚合操作：（基于集合内容返回单个值的操作）
 *
 * @author a_liYa
 * @date 2020/10/23 16:50.
 *
 */
fun main() {
    val numbers = listOf(6, 42, 10, 4)

    println("Count: ${numbers.count()}")    // 返回集合中元素的数量
    println("Max: ${numbers.max()}")        // 返回最大的元素
    println("Min: ${numbers.min()}")        // 返回最小的元素
    println("Average: ${numbers.average()}") // 返回数字集合中元素的平均值
    println("Sum: ${numbers.sum()}")        // 返回数字集合中元素的总和

    /*
        maxBy()/minBy() 接受一个选择器函数并返回使选择器返回最大或最小值的元素。
        maxWith()/minWith() 接受一个 Comparator 对象并且根据此 Comparator 对象返回最大或最小元素。
     */
    val min3Remainder = numbers.minBy { it % 3 }
    println(min3Remainder)

    val strings = listOf("one", "two", "three", "four")
    val longestString = strings.maxWith(compareBy { it.length })
    println(longestString)

    /*
        sumBy() 使用对集合元素调用返回 Int 值的函数。
        sumByDouble() 与返回 Double 的函数一起使用
     */
    println(numbers.sumBy { it * 2 })
    println(numbers.sumByDouble { it.toDouble() / 2 })

    printDivider("fold or Reduce")

    foldOrReduce()

}

private fun foldOrReduce() {
    val numbers = listOf(5, 2, 10, 4)

    // reduce 第一步则将第一个和第二个元素作为第一步的操作参数
    val sum = numbers.reduce { sum, element -> test_sum(sum, element)}
    println(sum)

    val sumDoubled = numbers.fold(0) { sum, element -> sum + element * 2 }
    println(sumDoubled)

}

private fun test_sum(a: Int, b: Int): Int {
    println("sum: $a + e: $b")
    return a + b
}
