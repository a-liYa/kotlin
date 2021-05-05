package com.aliya.kotlin

/**
 * 作用域函数 let run with apply also
 *
 * 两个主要区别：
 *     1. 上下文对象用 this(run with apply) 还是 it
 *     2. 返回值, let、run、with会返回 lambda 表达式结果
 *     3. run 可单独使用
 *
 * 作用：
 *     1. 显得具有代码结构层次感
 *     2. 搭配 ?. 进行非空判断
 *     3. 使用 it/this 的名别提高代码可阅读性
 *
 * @author a_liYa
 * @date 2021/5/5 15:46.
 *
 */
fun main() {

    var person : Person? = Person("a_liYa").apply {
        this.age = 30
        city = "HangZhou" // this 访问接收器对象的成员时可省略
    }

    val personItSelf = person.also {
        println("Person: ${it?.name} ${it?.age} ${it?.city}")
    }

    val personCity = with(person!!) {
        this.age = 31
        city = "YuHang"
        city
    }
    println("personCity $personCity")

    val personAge = person?.run {
        this.age = 32
        city = "XianLin"
        age
    }
    println("personAge $personAge")

    val personName = person.let {
        it.name = "v_liYa"
        it.name
    }
    println("personName $personName")


}
