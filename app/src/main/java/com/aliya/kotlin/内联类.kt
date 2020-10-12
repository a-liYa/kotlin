package com.aliya.kotlin

/**
 * 内联类
 *
 * @author a_liYa
 * @date 2020/10/12 15:59.
 *
 */
// 内联类必须含有唯一的一个属性在主构造函数中初始化
inline class Password(val value: String)

// 在运行时，将使用这个唯一属性来表示内联类的实例

// 不存在 'Password' 类的真实实例对象
// 在运行时，'securePassword' 仅仅包含 'String'
val securePassword = Password("Don't try this in production")

// 内联类支持普通类中的一些功能。特别是，内联类可以声明属性与函数
inline class Name(val s: String) {
    val length: Int
        get() = s.length

    fun greet() {
        println("Hello, $s")
    }
}

fun main() {
    val name = Name("Kotlin")
    name.greet() // `greet` 方法会作为一个静态方法被调用
    println(name.length) // 属性的 get 方法会作为一个静态方法被调用
}
