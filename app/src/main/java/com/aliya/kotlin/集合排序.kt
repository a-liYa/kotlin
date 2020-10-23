package com.aliya.kotlin

/**
 * 集合排序，默认从小到大排序
 *
 * @author a_liYa
 * @date 2020/10/23 16:16.
 *
 */
fun main() {
    //
    println(Version(1, 2) > Version(1, 3))
    println(Version(2, 0) > Version(1, 5))

    // 自定义匿名比较器
    val lengthComparator = Comparator { str1: String, str2: String ->
        str1.length - str2.length
    }
    val listOf = listOf("aaa", "bb", "c");
    println(listOf.sortedWith(lengthComparator))

    // 定义一个 Comparator 的另一实现方式是 compareBy() 函数
    println(listOf.sortedWith(compareBy { it.length }))

    // 看源码调用 sortedWith(compareBy((T) -> R?))
    val sortedNumbers = listOf.sortedBy { it.length }
    println("Sorted by length ascending: $sortedNumbers")
    val sortedByLast = listOf.sortedByDescending { it.last() }
    println("Sorted by the last letter descending: $sortedByLast")

    val numbers = listOf(5, 1, 2, 3, 4)
    // 自然排序，基于接口 Comparable
    println("Sorted ascending: ${numbers.sorted()}")            // 从小到大
    println("Sorted descending: ${numbers.sortedDescending()}") // 从大到小

    // 返回随机顺序
    println(numbers.shuffled())

    // 倒序
    // 1。reversed() 函数以相反的顺序检索集合
    // 2。asReversed()——返回相同集合实例的一个反向视图
}

// 继承 Comparable 实现 compareTo() 即定义了自然排序
private class Version(val major: Int, val minor: Int) : Comparable<Version> {
    override fun compareTo(other: Version): Int {
        return if (this.major != other.major) {
            this.major - other.major
        } else
            this.minor - other.minor
    }
}



