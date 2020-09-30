package com.aliya.kotlin

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * KtClass
 *
 * @author a_liYa
 * @date 2020/9/30 16:09.
 *
 */

fun main() {
    val initOrderDemo = InitOrderDemo("name:李亚飞")
}


/**
 * 类声明由类名、类头（指定其类型参数、主构造函数等）以及由花括号包围的类体构成, 类头与类体是可选的
 */
// 无类头
class Invoice {
    /* ... */
}

// 无类头、无类体
class Empty


/**
 * 构造函数，关键字 constructor 修饰 ，一个类可有一个主构造函数及一个或多个次构造函数
 */
// 主构造函数，没有任何注解或者可见性修饰符时，可省略 constructor 关键字
class Person constructor(firstName: String) { /*...*/ }

class Person1(name: String) { /*...*/ }

// 主构造函数不能包含任何的代码，关键字 init 修饰的初始化块可初始化代码，
class InitOrderDemo(name: String) {
    val firstProperty = "1 First property: $name".also(::println)

    init {
        println("2 First initializer block that prints $name")
    }

    val secondProperty = "3 Second property: ${name.length}".also(::println)

    init {
        println("4 Second initializer block that prints ${name.length}")
    }
}

// 次构造函数
class Person2 {
    var children: MutableList<Person2> = mutableListOf()

    constructor(parent: Person2) {
        parent.children.add(this)
    }
}

// 如果类有一个主构造函数，每个次构造函数需要委托给主构造函数，
class Person3(val name: String) {
    var children: MutableList<Person3> = mutableListOf()

    constructor(name: String, parent: Person3) : this(name) {
        parent.children.add(this)
    }
}

/**
 * 继承 - 超类 Any
 */

/**
 * open 关键字，表示类可继承，方法可覆盖
 */

open class Base // 该类开放继承，并从 Any 隐式继承

// 继承 Base
class Derived(p: Int) : Base()

class MyView : View {
    // 如果派生类没有主构造函数，那么每个次构造函数必须使用 super 关键字初始化其基类型
    constructor(ctx: Context) : this(ctx, null)

    constructor(ctx: Context, attrs: AttributeSet?) : this(ctx, attrs, 0)

    constructor(ctx: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(ctx, attrs, defStyleAttr)
}

// 覆盖方法
open class Shape {
    open val vertexCount: Int = 0
    open fun draw() { /*……*/ }
    fun fill() { /*……*/ }
}

class Circle() : Shape() {
    // 因父类 Shape#draw open 修饰，所以可覆盖，覆盖时必须使用 override 修饰， 若想禁止子类再次覆盖，加 final 关键字
    override fun draw() { /*……*/ }
}

// 覆盖属性
open class Rectangle : Shape() {
    // 类似于覆盖方法，一个 var 属性可覆盖一个 val 属性，反之不行
    override val vertexCount = 4
}

// 主构造函数中覆盖属性时使用 override 关键字作为属性声明的一部分
class Polygon(override val vertexCount: Int = 0): Shape() {
}

// 派生类中的代码可以使用 super 关键字调用其超类的函数与属性访问器的实现：
// 在一个内部类中访问外部类的超类，可以通过由外部类名限定的 super 关键字来实现：super@Outer：
class FilledRectangle: Rectangle() {
    override fun draw() { /* …… */ }
    val borderColor: String get() = "black"

    inner class Filler {
        fun fill() { /* …… */ }
        fun drawAndFill() {
            super@FilledRectangle.draw() // 调用 Rectangle 的 draw() 实现
            fill()
        }
    }
}


open class Rectangle1 {
    open fun draw() { /* …… */ }
}

interface Polygon1 {
    fun draw() { /* …… */ } // 接口成员默认就是“open”的
}

class Square() : Rectangle1(), Polygon1 {
    // 编译器要求覆盖 draw()：
    override fun draw() { // 不是很懂
        super<Rectangle1>.draw() // 调用 Rectangle.draw()
        super<Polygon1>.draw() // 调用 Polygon.draw()
    }
}

