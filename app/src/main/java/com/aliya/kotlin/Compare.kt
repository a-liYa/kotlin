package com.aliya.kotlin

/**
 * Compare
 *
 * @author a_liYa
 * @date 2020/8/23 22:53.
 *
 */
fun main() {

    val name1: String = "张三"
    val name2: String = "张三"

    // == 比较值本身
    // Kotlin equals 和 == 等价 Java 的 equals
    println(name1.equals(name2)) // true
    println(name1 == name2) // true


    // === 比较对象地址， 等价 Java 的 ==
    val test1 =  10000
    val test2 =  10000
    println(test1 === test2) // false

}