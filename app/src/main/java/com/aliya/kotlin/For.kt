package com.aliya.kotlin

/**
 * For
 *
 * @author a_liYa
 * @date 2020/8/23 23:25.
 *
 */
fun main() {

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