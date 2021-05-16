package com.aliya.kotlin

/**
 * 泛型 Generics
 *
 * Java     Producer extends Consumer super
 * Kotlin   Producer out Consumer in
 *
 * @author a_liYa
 * @date 2020/10/10 16:50.
 *
 */

class Box<T>(t: T) {
    var value = t
}

val box: Box<Int> = Box<Int>(1)

val boxInt = Box(1) // 1 具有类型 Int，所以编译器知道我们说的是 Box<Int>。

/*
    只能从中读取的对象为生产者，并称那些你只能写入的对象为消费者。
    型变
        声明处型变，消费者 in, 生产者 out
        类型投影
 */

/**
 * 生产者
 */
interface Producer<out T> {
    fun nextT(): T // 只读，生产者
    // fun add(t: T)  // 报错
}

fun demo(strs: Producer<String>) {
    val objects: Producer<Any> = strs
}

interface Comparable<in T> {
    operator fun compareTo(other: T): Int // 只写，为消费者
}

fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 拥有类型 Double，它是 Number 的子类型
    // 因此，我们可以将 x 赋给类型为 Comparable <Double> 的变量
    val y: Comparable<Double> = x // OK！
}

// 上界
// 最常见的约束类型是与 Java 的 extends 关键字对应的 上界：
fun <T : Comparable<T>> sort(list: List<T>) {
    /*...*/
}