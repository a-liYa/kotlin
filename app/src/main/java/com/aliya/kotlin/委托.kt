package com.aliya.kotlin

/**
 * 委托
 *
 * @author a_liYa
 * @date 2020/10/12 16:43.
 *
 */
interface Base1 {
    fun print()
}

class BaseImpl(val x: Int) : Base1 {
    override fun print() { print(x) }
}

class Derived1(b: Base1) : Base1 by b

// 类似于 代理模式
fun main() {
    val b = BaseImpl(10)
    Derived1(b).print()
}