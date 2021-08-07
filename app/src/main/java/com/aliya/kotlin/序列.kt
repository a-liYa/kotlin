package com.aliya.kotlin

/**
 * 序列 Sequence<T>
 *
 * @author a_liYa
 * @date 2020/10/22 16:51.
 *
 */

/*
 Iterable 与 Sequence 区别：
    当 Iterable 的处理包含多个步骤时，它们会优先执行：每个处理步骤完成并返回其结果——中间集合。
    序列的多步处理在可能的情况下会延迟执行：仅当请求整个处理链的结果时才进行实际计算。

    序列可避免生成中间步骤的结果，提高集合处理链的性能，但，序列的延迟性质也增加了一些开销，此开销在处理较小的集合或进行更简单的计算时可能很重要。
 */

fun main() {
    /**
     * 构造序列的方式
     */

    /*
     * 方式一：sequenceOf() 函数
     */
    var numbersSequence = sequenceOf("four", "three", "two", "one")
    println(numbersSequence.toList())
    // 打印结果 [four, three, two, one]


    /*
     * 方式二：asSequence()
     */
    numbersSequence = listOf("one", "two", "three", "four").asSequence()
    println(numbersSequence.toList())
    // 打印结果 [one, two, three, four]


    /*
     * 方式三：generateSequence(seed) 参数 seed 表示第一个元素
     */
    var oddNumbers = generateSequence(1) { it + 2 } // `it` 是上一个元素
    println(oddNumbers.take(5).toList()) // 取前五个元素

    // oddNumbers.iterator().forEach { println(it) } // 无限打印

    // 创建有限序列，函数返回为 null 即结束序列
    val oddNumbersLessThan10 = generateSequence(1) { if (it + 2 < 10) it + 2 else null }
    println(oddNumbersLessThan10.toList())


    /*
     * 方式四：sequence block
     */
    // 由组块
    oddNumbers = sequence {
        yield(1)
        yieldAll(listOf(3, 5))
        yieldAll(generateSequence(7) { it + 2 })
    }
    println(oddNumbers.take(10).toList())


    // 对比区别 Iterable、Sequence, 参考日志打印
    println("\nwordIterable\n")
    wordIterable()
    println("\nwordSequence\n")
    wordSequence()
}

fun wordIterable() {
    val words = "The quick brown fox jumps over the lazy dog".split(" ")
    val lengthsList = words.filter { println("filter: $it"); it.length > 3 }
        .map { println("map length: ${it.length}"); it.length }
        .take(4)

    println("字符数超过3的前4个单词:")
    println(lengthsList)
}

fun wordSequence() {
    val words = "The quick brown fox jumps over the lazy dog".split(" ")
    // 将列表转换为序列
    val wordsSequence = words.asSequence()

    val lengthsSequence = wordsSequence.filter { println("filter: $it"); it.length > 3 }
        .map { println("map length: ${it.length}"); it.length }
        .take(4)

    println("Lengths of first 4 words longer than 3 chars")
    // 末端操作：以列表形式获取结果。
    println(lengthsSequence.toList())
}