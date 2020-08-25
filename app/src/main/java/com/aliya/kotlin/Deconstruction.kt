package com.aliya.kotlin

/**
 * Deconstruction 解构声明
 *
 * @author a_liYa
 * @date 2020/8/25 16:21.
 *
 */
fun main() {

    // 解构声明实际上就是将对象中所有属性，解构成一组属性变量，而且这些变量可以单独使用
    val student = Student("a_liYa", 18, 99.0)
    // 将一个 student 对象解构成一组3个单独的变量
    val (name, age, grade) = student
    // 解构后的3个变量可以脱离对象，直接单独使用
    println("My name is $name , I'm $age years old, I get $grade score")

    /*
    原理：每个属性值的获得最后都编译成通过调用与之对应的component()方法
     */

    // 下划线_ 忽略name属性
    val (_, age1, grade1) = student
    println("I'm $age1 years old, I get $grade1 score")

}

data class Student(var name: String, var age: Int, var grade: Double)