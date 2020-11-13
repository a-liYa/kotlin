package com.aliya.kotlin

/**
 * Null
 *
 * @author a_liYa
 * @date 2020/8/23 21:24.
 *
 */

class Null {


}

/**
 * Kotlin引入了空安全的概念，并在编译时开展对象是否为空的校验。相关的操作符说明概括如下：
 *
 * ①、声明对象实例时，在类型名称后面加？，表示该对象可以为空；
 *
 * ②、调用对象方法时，在实例名称后面？，表示一旦实例为空就返回null；
 *
 * ③、新引入运算符“?:”，一旦实例为空就返回该运算符右边的表达式；?: -> Elvis 操作符表达
 *
 * ④、新引入运算符“!!”，通知编译器不做非空校验，运行时一旦发现实例为空就扔出异常；
 */
fun main() {

    var info: String? = null // ①

    println(info?.length) // ② 输出 null

    println(info?.length ?: "is null") // ③

    try {
        // ④ 空指针异常 KotlinNullPointerException
        println(info!!.length)
    } catch (e: Exception) {
        println(e)
    }

    println(info.toJson())

    info = ""

    println(info.toJson())


}

// 给所有对象扩展toJson方法，包括null类型
fun Any?.toJson(): String {
    return if (this == null) "null" else "toJson"
}