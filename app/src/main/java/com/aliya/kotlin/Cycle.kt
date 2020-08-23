package com.aliya.kotlin

/**
 * Cycle
 *
 * @author a_liYa
 * @date 2020/8/23 20:04.
 *
 */
fun main(): Unit {

}





// for 循环展示
fun forShow() {
    val items = listOf("apple", "banana", "kiwifruit")

    for (item in items) {
        println(item)
    }
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
}

// while 循环展示
fun whileShow() {
    val items = listOf("apple", "banana", "kiwifruit")
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }
}


fun whenInShow() {
    val items = listOf("orange", "apple")
    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }
}