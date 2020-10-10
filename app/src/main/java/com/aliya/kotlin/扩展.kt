package com.aliya.kotlin

/**
 * 扩展
 *
 * @author a_liYa
 * @date 2020/10/10 14:39.
 *
 */
// 扩展函数 swap
fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // “this”对应该列表
    this[index1] = this[index2]
    this[index2] = tmp
}

// 扩展属性 lastIndex
val <T> List<T>.lastIndex: Int
    get() = size - 1

// 可空接收者
fun Any?.toString(): String {
    if (this == null) return "null"
    // 空检测之后，“this”会自动转换为非空类型，所以下面的 toString()
    // 解析为 Any 类的成员函数
    return toString()
}

fun main() {
    val list= mutableListOf(1, 2, 3)
    list.swap(0,2)
    println(list)

    println(list.lastIndex)

    // 调用扩展函数, 一般优先成员函数，此时调用的是 Any?.toString()
    var str: String? = null
    val toString = str.toString()
    println("toString:$toString")
}

// 扩展声明为成员 - 在一个类内部你可以为另一个类声明扩展
class Host(val hostname: String) {
    fun printHostname() { print(hostname) }
}

class Connection(val host: Host, val port: Int) {
    fun printPort() { print(port) }

    // 外部不能调用
    fun Host.printConnectionString() {
        printHostname()   // 调用 Host.printHostname()
        print(":")
        printPort()   // 调用 Connection.printPort()
    }

    fun connect() {
        /*……*/
        host.printConnectionString()   // 调用扩展函数
    }
}