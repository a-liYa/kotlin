package com.aliya.kotlin

/**
 * 对象表达式与对象声明
 *
 * @author a_liYa
 * @date 2020/10/12 15:10.
 *
 */

// 需要创建一个对某个类做了轻微改动的类的对象，而不用为之显式声明新的子类

// 对象表达式
// 要创建一个继承自某个（或某些）类型的匿名类的对象

open class ClassA(x: Int) {
    public open val y: Int = x
}

interface ClassB { /*……*/ }

fun main() {
    val ab: ClassA = object : ClassA(1), ClassB {
        override val y = 15
    }

    // 任何时候，如果我们只需要“一个对象而已”，并不需要特殊超类型，那么我们可以简单地写：
    val adHoc = object {
        var x: Int = 1
        var y: Int = 2

        fun sum() = x + y
    }
    print(adHoc.x + adHoc.y + adHoc.sum())

    DataProviderManager.foo()

    val instance = MyClass.create()
}

// 对象声明

// 单例, 对象声明不能在局部作用域（即直接嵌套在函数内部
object DataProviderManager {
    fun method(){}

    fun foo(){}
}

// 伴生对象
class MyClass {
    companion object Factory {
        fun create(): MyClass = MyClass()
    }
}

