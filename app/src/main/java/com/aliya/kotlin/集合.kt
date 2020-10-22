package com.aliya.kotlin

import java.util.*

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
    val arrayList: List<String> = listOf("one", "two", "one")
    printAll(arrayList)



    // Set 默认实现 LinkedHashSet
    val linkedHashSet = setOf("one", "two", "three", "two")
    printAll(linkedHashSet)

    // Map 默认实现 LinkedHashMap
    val linkedHashMap = mapOf(Pair("key1", "value1"))
    linkedHashMap.forEach() {
        print("${it.key} - ${it.value}, ")
    }

    mapOf("key1" to "value1") // 使用 中缀函数 to 代替 Pair，性能差创建了短时存活对象 Pair。

    // 创建空集合时须指明类型
     mutableListOf<String>()
    // mutableSetOf<String>()
    // mutableMapOf<String, String>()

    // 访问
    arrayList[0] == arrayList.get(0)
    val numbersMap =
        mutableMapOf<String, String>().apply { this["one"] = "1"; this["two"] = "2" }
    numbersMap["one"] == numbersMap.get("one")

    val emptyList = emptyList<String>() // listOf<String>() 即调用的该方法

    // list 的初始化函数
    val doubled = List(3) { it * 2 }  // 如果你想操作这个集合，应使用 MutableList
    println(doubled)

    // 创建具体类型的集合，可使用该类型的构造函数，eg：LinkedList
    val linkedList = LinkedList<String>(listOf("one", "two", "three"))

    // 深拷贝，toList()、toMutableList()、toSet()
    val sourceList = mutableListOf(1, 2, 3)
    val copyList = sourceList.toMutableList()
    val readOnlyCopyList = sourceList.toList()
    sourceList.add(4)
    println("Copy size: ${copyList.size}，Source size：${sourceList.size}")

    // 过滤列表会创建与过滤器匹配的新元素列表：
    val numbersList = listOf("one", "two", "three", "four")
    val longerThan3 = numbersList.filter { it.length > 3 }
    println(longerThan3)

    // 映射生成转换结果列表：
    val numbersSet = setOf(1, 2, 3)
    println(numbersSet.map { it * 3 })
    println(numbersSet.mapIndexed { idx, value -> value * idx })

    // 关联生成 Map:
    val numbers = listOf("one", "two", "three", "four")
    println(numbers.associateWith { it.length })

}

fun printAll(strings: Collection<String>) {
    for(s in strings) print("$s ")
    println()
}