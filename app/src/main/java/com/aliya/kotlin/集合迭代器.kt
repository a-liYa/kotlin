package com.aliya.kotlin

/**
 * 集合迭代器
 *
 * @author a_liYa
 * @date 2020/10/22 14:21.
 *
 */

fun main() {

    val numbers = listOf("one", "two", "three", "four")

    // 迭代器 迭代
    val numbersIterator = numbers.iterator()
    while (numbersIterator.hasNext()) {
        print("${numbersIterator.next()} ")
    }

    println("\n---------------------------------------------------------------------------------\n")

    // 隐式获取迭代器迭代
    for (item in numbers) {
        print("$item ")
    }

    println("\n---------------------------------------------------------------------------------\n")

    numbers.forEach {
        print("$it ")
    }

    println("\n---------------------------------------------------------------------------------\n")

    listIteratorSample()

    println("\n---------------------------------------------------------------------------------\n")

    mutableListIteratorSample()
}

//  ListIterator 它支持列表双向迭代：正向与反向。 反向迭代由 hasPrevious() 和 previous() 函数实现
fun listIteratorSample() {
    val numbers = listOf("one", "two", "three", "four")

    val listIterator = numbers.listIterator()

    while (listIterator.hasNext()) listIterator.next()
    println("Iterating backwards:")
    while (listIterator.hasPrevious()) {
        print("Index: ${listIterator.previousIndex()}")
        println(", value: ${listIterator.previous()}")
    }
}

//  可变集合的 MutableIterator：可删除元素； MutableListIterator：删除、插入、替换
fun mutableListIteratorSample() {
    var numbers = mutableListOf("one", "two", "three", "four")
    val mutableIterator = numbers.iterator()
    // 删除第一个元素
    mutableIterator.next()
    mutableIterator.remove()
    println("After removal: $numbers")

    numbers = mutableListOf("one", "four", "four")
    val mutableListIterator = numbers.listIterator()
    // 第一个元素后面新增一个元素，next 位于新增元素这里
    mutableListIterator.next()
    mutableListIterator.add("two")
    // 下一个元素替换成 three
    mutableListIterator.next()
    mutableListIterator.set("three")
    println(numbers)
}