package com.aliya.kotlin

/**
 * 集合 - 概论
 *
 * @author a_liYa
 * @date 2020/10/21 11:42.
 *
 */
/*
    只读集合：List、       Set、       Map
    可变集合：MutableList、MutableSet、MutableMap

    只读集合类型是型变的，如果类 Rectangle 继承自 Shape，则
      var rectangles: List<Rectangle>
      var shapes: List<Shape> = rectangles

    可变集合不是型变的

 */

fun main() {
    // List 默认实现 ArrayList
    val arrayList = listOf("one", "two", "one")
    printAll(arrayList)
    // arrayList[0] == arrayList.get(0)

    // Set 默认实现 LinkedHashSet
    val linkedHashSet = setOf("one", "two", "three", "two")
    printAll(linkedHashSet)

    // Map 默认实现 LinkedHashMap
    val linkedHashMap = mapOf(Pair("key1", "value1"))
    linkedHashMap.forEach() {
        print("${it.key} - ${it.value}, ")
    }

    // mutableListOf<String>()
    // mutableSetOf<String>()
    // mutableMapOf<String, String>()
}

fun printAll(strings: Collection<String>) {
    for(s in strings) print("$s ")
    println()
}