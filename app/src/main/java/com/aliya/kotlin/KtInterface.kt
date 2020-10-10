package com.aliya.kotlin

/**
 * interface 接口
 *
 * @author a_liYa
 * @date 2020/10/9 10:34.
 *
 */
// 包含抽象方法的声明也包含实现
interface MyInterface {
    val prop: Int // 抽象的

    val propertyWithImplementation: String
        get() = "foo"

    fun foo() {
        print(prop)
    }
}

class Child : MyInterface {
    override val prop = 29
}

// 实现多个接口时，可能会遇到同一方法继承多个实现的问题
interface A {
    fun foo() { print("A") }
    fun bar()
}

interface B {
    fun foo() { print("B") }
    fun bar() { print("bar") }
}

class C : A {
    override fun bar() { print("bar") }
}

class D : A, B {
    // 解决覆盖冲突
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
    }

    override fun bar() {
        super.bar()
    }
}