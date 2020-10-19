package com.aliya.kotlin

/**
 * 高阶函数
 *
 * @author a_liYa
 * @date 2020/10/19 16:08.
 *
 */

/*
 高阶函数是将函数用作参数或返回值的函数。
 */

fun <T, R> Collection<T>.fold(
    initial: R,
    combine: (acc: R, nextElement: T) -> R
): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}

fun main() {
    val items = listOf(1, 2, 3, 4, 5)

    // Lambdas 表达式是花括号括起来的代码块。
    val fold = items.fold(0, { acc: Int, i: Int -> acc + i })
    println("fold 累加 = $fold")
    // lambda 表达式的参数类型是可选的，如果能够推断出来的话：
    val joinedToString = items.fold("Elements:", { acc, i ->  "$acc $i"})

    println(joinedToString)

    println("\n---------------------------------------------------------------------------------\n")

    funInvoke()
}

/*
 函数类型
    声明类似：(Int) -> String
    1、所有函数类型都有一个圆括号括起来的参数类型列表以及一个返回类型，均不可省略，无参无返回时声明() -> Unit
 */


/*
  函数类型实例化
    使用函数字面值的代码块：
        -- lambda 表达式: { a, b -> a + b },
        -- 匿名函数: fun(s: String): Int { return s.toIntOrNull() ?: 0 }

    使用已有声明的可调用引用
        -- 顶层、局部、成员、扩展函数：::isOdd、 String::toInt，
        -- 顶层、成员、扩展属性：List<Int>::size，
        -- 构造函数：::Regex
 */

// 函数类型实例调用: f.invoke(x) 或者直接 f(x)
fun funInvoke() {

    val stringPlus: (String, String) -> String = String::plus
    val intPlus: Int.(Int) -> Int = Int::plus

    println(stringPlus.invoke("<-", "->"))
    println(stringPlus("Hello, ", "world!"))

    println(intPlus.invoke(1, 1))
    println(intPlus(1, 2))
    println(2.intPlus(3)) // 类扩展调用
}


/*
    Lambda 表达式与匿名函数
      lambda 表达式与匿名函数是“函数字面值”，即未声明的函数
        语法：val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
        lambda 表达式总是括在花括号中

        拖尾 lambda 表达式：如果函数的最后一个参数是函数，那么作为相应参数传入的 lambda 表达式可以放在圆括号之外。

      匿名函数：常规函数省略函数名
        定义：fun(x: Int, y: Int): Int = x + y

      区别：1、lambda 表达式缺少指定函数的返回类型的能力，2、非局部返回的行为
 */