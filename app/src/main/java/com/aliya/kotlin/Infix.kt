package com.aliya.kotlin

/**
 * Infix 中缀调用 - 示例
 *
 * @author a_liYa
 * @date 2020/8/25 15:06.
 *
 */
fun main() {

    /*
     中缀调用结构: A (中缀函数名) B
     使用infix关键字修饰的函数， 满足条件：A.fun(B)
        1. 函数参数有且仅有一个
        2. 函数的参与者只有两个元素
     */

    /*
    初始化map
     */
    // 普通利用Pair()初始化一个map
    var map = mapOf(Pair(1, "A"), Pair(2, "B"))
    map.forEach { key, value ->
        println("key: $key   value:$value")
    }

    // 利用to函数初始化一个map
    map = mapOf(3.to("C"), 4.to("D"))
    map.forEach { key, value ->
        println("key: $key   value:$value")
    }

    // 利用to函数中缀调用初始化一个map
    map = mapOf(5 to "E", 6 to "F")
    map.forEach { key, value ->
        println("key: $key   value:$value")
    }

    /*
    字符串比较
     */
    val strA: String? = null
    val strB: String? = null
    // 普通使用字符串对比
    if (strA == strB) {
        println("str is the equals")
    }
    // 中缀调用 sameAs
    if (strA sameAs strB) {
        println("str is the sameAs")
    }

    /*
    判断一个元素是否在集合中
     */
    val list = listOf(1, 3, 5, 7, 9)
    val element = 1
    if (list.contains(element)) {
        println("list contains element: $element")
    }
    // 中缀调用，这样的写法，会更加接近我们自然语言的表达，更容易理解
    if (element into list) {
        println("element: $element is into list")
    }

}

private infix fun <A, B> A.sameAs(that: B): Boolean = this == that

private infix fun <T> T.into(other: Collection<T>): Boolean = other.contains(this)