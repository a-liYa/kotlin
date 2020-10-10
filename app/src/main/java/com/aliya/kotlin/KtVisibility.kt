package com.aliya.kotlin

/**
 * KtVisibility - 可见性修饰符
 *
 * @author a_liYa
 * @date 2020/10/10 13:56.
 *
 */

// Kotlin 中四个可见性修饰符：private、 protected、 internal 和 public，默认为 public

/*
    包内的顶层声明：函数、属性和类、对象和接口
        -- 如果你不指定任何可见性修饰符，默认为 public；
        -- 如果你声明为 private，它只会在声明它的文件内可见；
        -- 如果你声明为 internal，它会在相同模块内随处可见；
        -- protected 不适用于顶层声明。
 */

fun main() {
    bar = 10;
}

private fun foo() { } // 在 KtVisibility.kt 内可见

var bar: Int = 5 // 该属性随处可见
    private set  // setter 只在 KtVisibility.kt 内可见

internal val baz = 6    // 相同模块内可见

/*
    类和接口 - 对于类内部声明的成员:
        -- private 意味着只在这个类内部（包含其所有成员）可见；
        -- protected 和 private一样 + 在子类中可见。
        -- internal 能见到类声明的 本模块内 的任何客户端都可见其 internal 成员；
        -- public 能见到类声明的任何客户端都可见其 public 成员。
 */

open class Outer {
    private val a = 1
    protected open val b = 2
    internal val c = 3
    val d = 4  // 默认 public

    protected class Nested {
        public val e: Int = 5
    }
}

class Subclass : Outer() {
    // a 不可见
    // b、c、d 可见
    // Nested 和 e 可见

    override val b = 5   // “b”为 protected
}

class Unrelated(o: Outer) {
    // o.a、o.b 不可见
    // o.c 和 o.d 可见（相同模块）
    // Outer.Nested 不可见，Nested::e 也不可见
}

// 主构造函数的可见性，注：需要添加一个显式 constructor 关键字
class Clazz1 private constructor(a: Int) { }