package com.aliya.kotlin

import com.aliya.kotlin.IntPredicate as IntPredicate

/**
 * SAM Single Abstract Method
 *
 * @author a_liYa
 * @date 2020/10/10 11:34.
 *
 */
fun main() {

    // 创建一个类的实例
    var isEven = object : IntPredicate {
        override fun accept(i: Int): Boolean {
            return i % 2 == 0
        }
    }

    // 通过 lambda 表达式创建一个实例 注：kotlin v1.4 才支持
//     val isEven = IntPredicate { it % 2 == 0 }

    println("Is 7 even? - ${isEven.accept(7)}")

}

// 只有一个抽象方法的接口称为函数式接口或 SAM（单一抽象方法）接口。
interface KRunnable {
    fun invoke()
}

interface IntPredicate {
    fun accept(i: Int): Boolean
}


