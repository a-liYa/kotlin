package com.aliya.kotlin

/**
 * 嵌套类
 *
 * @author a_liYa
 * @date 2020/10/12 14:22.
 *
 */
class OuterA {
    private val barInt: Int = 1

    // 嵌套类不能访问外部类的成员
    class Nested {
        fun foo() = 2
    }

    // 嵌套类标记 inner，即为内部类，可以访问外部类的成员
    inner class Inner {
        fun foo() = barInt
    }
}

private class OuterB { // 隐式标签 @OuterB
    inner class B { // 隐式标签 @B
        fun Int.foo() { // 隐式标签 @foo
            val a = this@OuterB // A 的 this
            val b = this@B // B 的 this

            val c = this // foo() 的接收者，一个 Int
            val c1 = this@foo // foo() 的接收者，一个 Int

            val funLit = lambda@ fun String.() {
                val d = this // funLit 的接收者
            }


            val funLit2 = { s: String ->
                // foo() 的接收者，因为它包含的 lambda 表达式
                // 没有任何接收者
                val d1 = this
            }
        }
    }
}