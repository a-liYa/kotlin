package com.aliya.kotlin

/**
 * DataClass
 *
 * @author a_liYa
 * @date 2020/10/10 15:55.
 *
 */
data class User(val name: String, var age: Int)

/*
    编译器自动从主构造函数中声明的所有属性导出以下成员：
        equals() / hashCode() 对；
        toString() 格式是 "User(name=John, age=42)"；
        componentN() 函数 按声明顺序对应于所有属性；
        copy() 函数。
 */

/*
    在 toString()、 equals()、 hashCode() 以及 copy() 的实现中只会用到 name 属性，
    并且只有一个 component 函数 component1()。虽然两个 PePersonUserrson 对象可以有不同的年龄，但它们会视为相等。
 */
data class PersonUser(val name: String) {
    var age: Int = 0
}

fun main() {
    val user1 = User("a_liYa", 29)
    // val user2 = User() // 若想使用无参构造，必须设置主构造参数有默认值

    // 解构声明
    val (name1, age1) = user1
    println("$name1, $age1 years of age")

}