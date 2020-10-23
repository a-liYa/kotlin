package com.aliya.kotlin

import com.aliya.kotlin.util.printDivider

/**
 * 集合操作
 *
 * @author a_liYa
 * @date 2020/10/23 09:16.
 *
 */
fun main() {
    printDivider(" filter ")

    filterOpt()

    printDivider(" sort ")

    sortOpt()

    printDivider(" map ")

    mapOpt()

    printDivider(" zip ")

    zipOpt()

    printDivider(" associate ")

    associateOpt()

    printDivider(" flat ")

    flatOpt()

    printDivider(" joinTo ")

    joinToOpt()
}

/**
 * 过滤操作
 */
private fun filterOpt() {
    val numbers = listOf("one", "two", "three", "four")
    val longerThan3 = numbers.filter { it.length > 3 } // 结果存储在 `longerThan3` 中
    println("numbers longer than 3 chars are $longerThan3")

    // 可使用 filterTo() 代替 filter()，带 To 后缀，表示把结果存储到目标集合
    val filterResults = mutableListOf<String>()  // 目标对象
    val filterTo = numbers.filterTo(filterResults) { it.length > 3 }
    val filterIndexedTo = numbers.filterIndexedTo(filterResults) { index, _ -> index == 0 }

    println(filterResults) // 包含两个操作的结果
    println(filterResults === filterTo) // true
    println(filterResults == filterIndexedTo) // true

}

/**
 * 排序操作
 */
private fun sortOpt() {
    // 有些操作，有成对的函数可以执行相同的操作：一个函数就地应用该操作，另一个函数将结果作为单独的集合返回
    val numbers = mutableListOf("one", "two", "three", "four")
    val sortedNumbers = numbers.sorted()
    println(numbers == sortedNumbers)  // false
    numbers.sort()
    println(numbers == sortedNumbers)  // true
}

/**
 * 映射操作
 */
private fun mapOpt() {
    val numbers = setOf(1, 2, 3)
    println(numbers.map { if (it == 2) null else it * 3 })
    println(numbers.mapIndexed { idx, value -> if (idx == 0) null else value * idx })

    // 过滤 null 值
    println(numbers.mapNotNull { if (it == 2) null else it * 3 })
    println(numbers.mapIndexedNotNull { idx, value -> if (idx == 0) null else value * idx })

    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
    println(numbersMap.mapKeys { it.key.toUpperCase() })
    println(numbersMap.mapValues { it.value + it.key.length })
}

/**
 * 双路合并操作
 */
private fun zipOpt() {
    // List<T>.zip(List<R>) 结果返回 List<Pair<T, R>>
    val colors = listOf("red", "brown", "grey")
    val animals = listOf("fox", "bear", "wolf")
    println(colors zip animals)

    val twoAnimals = listOf("fox", "bear")
    println(colors.zip(twoAnimals))

    // 合并的结果类型：List<String>
    println(colors.zip(animals) { color, animal -> "The ${animal.capitalize()} is $color" })

    val numberPairs = listOf("one" to 1, "two" to 2, "three" to 3, "four" to 4)
    println(numberPairs.unzip()) // 结果类型：Pair<List<String>, List<String>
}

/**
 * 关联操作
 */
private fun associateOpt() {
    val numbers = listOf("one", "two", "three", "four")
    // 元素作为 key，拖尾函数返回值为 value
    println(numbers.associateWith { it.length })

    // 元素作为 value，拖尾函数返回值为 key
    println(numbers.associateBy { it.first().toUpperCase() })

    // key、value 均通过参数函数转换结果
    println(
        numbers.associateBy(
            keySelector = { it.first().toUpperCase() },
            valueTransform = { it.length })
    )

    val names = listOf("Alice Adams", "Brian Brown", "Clara Campbell")
    // key、value 由 Pair 对象获取
    println(names.associate { name ->
        name.split(" ").let {
            it.first() to it.last()
        }
    })
}

/**
 * 打平操作
 */
private fun flatOpt() {
    val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
    // 返回嵌套集合中的所有元素的新 List
    println(numberSets.flatten())

//    val containers = listOf(
//        StringContainer(listOf("one", "two", "three")),
//        StringContainer(listOf("four", "five", "six")),
//        StringContainer(listOf("seven", "eight"))
//    )
//    println(containers.flatMap { it.values })
}


private fun joinToOpt() {
    val numbers = listOf("one", "two", "three", "four")

    println(numbers)
    // 遍历集合，元素 + "," + 元素
    println(numbers.joinToString())

    val listString = StringBuffer("The list of numbers: ")
    numbers.joinTo(listString)
    println(listString)

    println(numbers.joinToString(separator = " | ", prefix = "start: ", postfix = ": end"))

    val newNumbers = (1..100).toList()
    println(newNumbers.joinToString(limit = 10, truncated = "<...>"))
}