package com.aliya.kotlin

import java.util.Arrays.asList

/**
 * 函数
 *
 * @author a_liYa
 * @date 2020/10/19 14:27.
 *
 */

/*
 使用 fun 关键字声明

 函数参数 使用 Pascal 表示法定义，name：type，多个参数用逗号隔开

 函数参数可以有默认值，减少重载数量


 */

open class AA {
    open fun foo(i: Int = 10) { /*……*/ }
}

class BB : AA() {
    override fun foo(i: Int) { println(i) /*……*/ }  // 不能有默认值
}

// 如果一个默认参数在一个无默认值的参数之前，那么该默认值只能通过使用具名参数调用该函数来使用：
fun funFoo(
    bar: Int = 0,
    baz: Int,
) { /*……*/ }

// 如果在默认参数之后的最后一个参数是 lambda 表达式，那么它既可以作为具名参数在括号内传入，也可以在括号外传入：
fun lambdaFoo(
    bar: Int = 0,
    baz: Int = 1,
    qux: () -> Unit,
) { /*……*/ }

// 如果一个函数不返回任何有用的值，它的返回类型是 Unit

// 单表达式函数，显示声明返回类型可省略
fun double(x: Int): Int = x * 2

/*
  显式返回类型
    具有块代码体的函数必须始终显式指定返回类型，除非他们旨在返回 Unit
 */

// 对于 JVM 平台：在调用 Java 函数时不能使用具名参数语法

/*
  可变数量的参数
    函数的参数（通常是最后一个）可以用 vararg 修饰符标记;
    在函数内部，类型 T 的 vararg 参数的可见方式是作为 T 数组;
 */

/*
  函数作用域
    顶层函数、局部函数、成员函数、扩展函数
 */
fun main() {
    funFoo(baz = 1) // 具名参数


    val a = arrayOf(1, 2, 3)
    val list = listOf(0, *a, 4) // 伸展（spread）操作符（在数组前面加 *）
    // list = [0, 1, 2, 3, 4]

    println("\n---------------------------------------------------------------------------------\n")

    println(findFixPoint())
    println(findFixPointCycle())
}



/*
  尾递归函数：
    当一个函数用 tailrec 修饰符标记并满足所需的形式时, 编译器会优化该递归，留下一个快速而高效的基于循环的版本
 */
val eps = 1E-10 // "good enough", could be 10^-15
tailrec fun findFixPoint(x: Double = 1.0): Double
        = if (Math.abs(x - Math.cos(x)) < eps) x else findFixPoint(Math.cos(x))

private fun findFixPointCycle(x: Double = 1.0): Double{
    var value = x
    var count = 0
    while (Math.abs(value - Math.cos(value)) >= eps) {
        value = Math.cos(value)
        count++
    }
    return value
}