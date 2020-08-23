package com.aliya.kotlin

/**
 * Kotlin
 *
 * @author a_liYa
 * @date 2020/8/20 23:27.
 *
 */

/*
    这是一个块注释
*/

// 这是一个行注释
class Kotlin {
    val name: String = "kotlin"
}


fun main() {


    // var <标识符> [: <类型>] = <初始化值> // 可变

    // val <标识符> [: <类型>] = <初始化值> // 不可变


    // 可变长参数 vararg value : Int -> Java: int... value


    println("add3(1, 2) = " + add3(1, 2))
}


// 函数的定义
/*
 fun <函数名>(<参数名>: <类型>, ...)[: <返回类型>] {
    // 函数体，最后一行结果为返回值
 }
*/


// 标准
fun add(number1: Int, number2: Int): Int {
    return number1 + number2
}

// 简洁 返回值 == 类型推导
fun add1(number1: Int, number2: Int) = number1 + number2

// lambda 表达式写法
val add3: (Int, Int) -> Int = { number1, number2 -> number1 + number2 }

// 带有两个 Int 参数、返回 Int 的函数
fun sum(a: Int, b: Int): Int {
    return a + b
}

// 将表达式作为函数体、返回值类型自动推断的函数 (单表达式函数)
fun product(a: Int, b: Int) = a * b

// 无返回值的函数, Unit类型可以省略
fun printSum(a: Int, b: Int): Unit {
    print(a + b)
}

// 函数的变长参数可以用 vararg 关键字进行标识
fun vars(vararg v: Int) {
    for (vt in v) {
        print(vt)
    }
}

// 定义只读局部变量使用关键字 val 定义。只能为其赋值一次
fun defineLocalVal() {
    val a: Int = 1  // 立即赋值
    val b = 2       // 自动推断出 `Int` 类型
    val c: Int      // 如果没有初始值, 类型不能省略
    c = 3
}

// 可重新赋值的变量使用 var 关键字
fun defineLocalVar() {
    var x = 5 // 自动推断出 `Int` 类型
    x += 1
}


/*
    使用可空值及 null 检测
    当某个变量的值可以为 null 的时候，必须在声明处的类型后添加 ? 来标识该引用可为空。
 */
fun parseInt(str: String): Int? {
    //
    return null
}

// 使用类型检测及自动类型转换
fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        // `obj` 在该条件分支内自动转换成 `String`
        return obj.length
    }

    // 在离开类型检测分支后，`obj` 仍然是 `Any` 类型
    return null
}

fun getStringLength2(obj: Any): Int? {
    if (obj !is String) return null

    // `obj` 在这一分支自动转换为 `String`
    return obj.length
}

fun getStringLength3(obj: Any): Int? {
    // `obj` 在 `&&` 右边自动转换成 `String` 类型
    if (obj is String && obj.length > 0) {
        return obj.length
    }
    return null
}