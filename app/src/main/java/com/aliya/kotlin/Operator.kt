package com.aliya.kotlin

/**
 * Operator 运算符
 *
 * @author a_liYa
 * @date 2020/8/25 16:43.
 *
 */
/*
 运算符都有对应的方法的名字, eg: a + b -> a.plus(B)
 1. in 运算符 (1).检测某个数字是否在指定区间内, (2). 判断集合内是否包含某实例, (3). for循环迭代
    (x in 1..10) / (x in listOf)
 2. ===
 3. is 运算符检测一个表达式是否某类型的一个实例，检测后的分支中可当作该类型使用，无需显式转换
    (obj is String) / (obj !is String)
 */

fun main() {

    if (1 in 1..10) {
        println("1 in 1..10")
    }

    val listOf = listOf(1, 2, 3, 4)
    if (1 in listOf) {
        println("1 in $listOf")
    }

    for (i in listOf) {
        println("listOf $i")
    }

    val mapOf = mapOf(1 to "A", 2 to "B", 3 to "C")
    for ((k, v) in mapOf) {
        println("$k -> $v")
    }
}