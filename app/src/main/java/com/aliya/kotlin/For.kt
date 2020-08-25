package com.aliya.kotlin

/**
 * For
 *
 * @author a_liYa
 * @date 2020/8/23 23:25.
 *
 */
fun main() {

    labelShow()

    // 遍历集合
    var items  = listOf<String>("李四", "张三", "王五")
    for (item in items) {
        println(item)
    }

    // forEach遍历
    /*
        _Collections.kt
        public inline fun <T> Iterable<T>.forEach(action: (T) -> Unit): Unit {
            for (element in this) action(element)
        }
     */
    items.forEach {
        println(it)
    }

    for (index in items.indices) {
        println("下标：$index,  对应的值：${items[index]}")
    }

}

fun labelShow() {

    // 自定义标签
    label@ for (i in 1..5) {
        for (j in 1..5) {
            if (i == 3) break // break j循环

            println("i:$i, j:$j")

            if (i == 5) {
                break@label // break i循环
            }
        }
    }

    println("end")
}