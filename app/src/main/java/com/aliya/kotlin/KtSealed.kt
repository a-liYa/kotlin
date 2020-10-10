package com.aliya.kotlin

/**
 * KtSealed 密封类
 *
 * @author a_liYa
 * @date 2020/10/10 16:35.
 *
 */
// sealed修饰符 表示 密封类
sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

/*
    sealed class 所有的子类必须和 sealed class 在同一个文件声明. (注意如果是子类的子类(间接继承), 则可在任何地方.)
    sealed class本身是抽象的, 不能被直接实例化, 可以有abstract成员.
    密封类的构造函数默认是private的，密封类不允许有非private构造函数。
 */

// 用处：sealed class 常用在 when 表达式中，如果所有情形都覆盖到了, 可以省略 else。

fun eval(expr: Expr): Double = when(expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
    // 不需要else语句，因为我们已涵盖所有情况
}