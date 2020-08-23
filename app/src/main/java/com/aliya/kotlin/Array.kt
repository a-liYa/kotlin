package com.aliya.kotlin

/**
 * Array
 *
 * @author a_liYa
 * @date 2020/8/23 23:02.
 *
 */
fun main() {

    // 第一种形式
    val numbers = arrayOf(1, 2, "3", 4,'5', 6L, 7)
    for (number in numbers) {
         print(number)
    }

    println(numbers[0])


    // 第二种形式  value=0
    val numbers2 = Array(10,  {value: Int -> (value + 200) })
    for (value in numbers2) {
        println(value)
    }

}