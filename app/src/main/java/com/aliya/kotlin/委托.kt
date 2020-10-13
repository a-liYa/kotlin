package com.aliya.kotlin

/**
 * 委托
 *
 * @author a_liYa
 * @date 2020/10/12 16:43.
 *
 */
interface BaseInterf {
    fun print()
    fun printOther()
}

class BaseImpl(val x: Int) : BaseInterf {
    override fun print() { println(x) }
    override fun printOther() {
        println("BaseImpl#printOther()")
    }
}

class Delegate(b: BaseInterf) : BaseInterf by b {
    // 覆盖由委托实现的接口成员
    override fun print() {
        println("Delegate#print()")
    }
}

// 类似于 代理模式
fun main() {
    val b = BaseImpl(10)
    Delegate(b).print() // 打印：Delegate#print()
    Delegate(b).printOther() // 打印：BaseImpl#printOther()
}