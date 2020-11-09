package com.aliya.kotlin

import com.aliya.kotlin.util.printDivider

/**
 * 集合List
 *
 * @author a_liYa
 * @date 2020/10/28 13:54.
 *
 */
fun main() {
    printDivider("二分法查找")
    binarySearchTest()

    printDivider()

    val productList = listOf(
        Product("WebStorm", 49.0),
        Product("AppCode", 99.0),
        Product("DotTrace", 129.0),
        Product("ReSharper", 149.0))

    println(productList.binarySearch(Product("AppCode", 99.0), compareBy<Product> { it.price }.thenBy { it.name }))

}

data class Product(val name: String, var price: Double)

private fun binarySearchTest() {
    val numbers = mutableListOf("one", "two", "three", "four")
    numbers.sort()
    println(numbers)
    println(numbers.binarySearch("two"))  // 3
    println(numbers.binarySearch("z")) // -5
    println(numbers.binarySearch("two", 0, 2))  // -3
}