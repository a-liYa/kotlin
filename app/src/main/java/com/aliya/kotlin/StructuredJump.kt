package com.aliya.kotlin

/**
 * 结构化跳转
 *
 * Kotlin 有三种结构化跳转表达式：return、break、continue
 *
 * @author a_liYa
 * @date 2020/8/31 11:00.
 *
 */
fun main() {

    nestedShow("return ")
    nestedShow( null)

    println("------------------------")

    labelShow()

    println("------------------------")

    lambdaReturnShow()

    println("------------------------")

    lambdaLabelShow()

    println("------------------------")

    lambdaLabelShow1()
}

// return、break、continue 这些表达式都可以用作更大表达式的一部分
fun nestedShow(name: String?) {
    val s = name ?: return
    println("s == null 时执行不到这里，s = $s")
}

// 在 Kotlin 中任何表达式都可以用标签（label）来标记， 标签的格式：标识符后跟 @ 符号
fun labelShow() {

    // 自定义标签
    label@ for (i in 1..5) {
        for (j in 1..5) {
            if (i == 1) break // break j循环

            println("i:$i, j:$j")

            if (i == 2) continue@label // 等价于 if (i == 2) break

            if (i == 4) {
                break@label // break i循环
            }
        }
    }

    // 标签限制的 break 跳转到刚好位于该标签指定的循环后面的执行点，此处相当于结束外循环，整个循环结束
    // continue 继续标签指定的循环的下一次迭代。

    println("end")
}

// 打印结果 1 2
fun lambdaReturnShow() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return // 非局部直接返回到 lambdaReturnShow() 的调用者, 整个函数结束
        println(it)
    }
    println("这里是不可能执行到的")
}

// 打印结果 1 2 4 5
fun lambdaLabelShow() {
    // 显示标签
    listOf(1, 2, 3, 4, 5).forEach lit@{
        if (it == 3) return@lit // 局部返回到该 lambda 表达式的调用者，即 forEach 循环
        println(it)
    }
    println("这里可以执行到")
}

// 打印结果 1 2 4 5
fun lambdaLabelShow1() {
    // 隐式标签
    listOf(1, 2, 3, 4, 5).forEach{
        if (it == 3) return@forEach // 局部返回到该 lambda 表达式的调用者，即 forEach 循环
        println(it)
    }
    println("这里可以执行到")
}

// 打印结果 1 2 4 5
fun lambdaLabelShow2() {
    // 匿名函数内部的 return 语句将从该匿名函数自身返回
    listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
        if (value == 3) return  // 局部返回到匿名函数的调用者，即 forEach 循环
        print(value)
    })
    println("这里可以执行到")
}