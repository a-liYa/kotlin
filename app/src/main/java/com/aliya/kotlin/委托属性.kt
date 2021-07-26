package com.aliya.kotlin

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * 委托属性
 *
 * @author a_liYa
 * @date 2020/10/14 11:08.
 *
 */

/*
  有一些常见的属性类型，虽然我们可以在每次需要的时候手动实现它们， 但是如果能够为大家把他们只实现一次并放入一个库会更好。
    -- 延迟属性（lazy properties）: 其值只在首次访问时计算；
    -- 可观察属性（observable properties）: 监听器会收到有关此属性变更的通知；
    -- 把多个属性储存在一个映射（map）中，而不是每个存在单独的字段中。
 */

class PropertyExample {
    // 语法：val/var <属性名>: <类型> by <表达式>
    var p: String by DelegateExample()
}

class DelegateExample {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

fun main() {
    val e = PropertyExample()
    println(e.p)
    e.p = "委托属性赋值"

//    println("\n---------------------------------------------------------------------------------\n")
//
//    lazyExample()
//
//    println("\n---------------------------------------------------------------------------------\n")
//
//    observableExample()
//
//    println("\n---------------------------------------------------------------------------------\n")
//
//    delegateOtherProperty()
//
//    println("\n---------------------------------------------------------------------------------\n")
//
//    delegateMap()
}

// 延迟属性 Lazy
/*
  lazy() 是接受一个 lambda 并返回一个 Lazy <T> 实例的函数，返回的实例可以作为实现延迟属性的委托：
  第一次调用 get() 会执行已传递给 lazy() 的 lambda 表达式并记录结果， 后续调用 get() 只是返回记录的结果。
 */
val lazyValue: String by lazy {
    println("属性懒加载，仅在第一次使用时调用!")
    "Hello"
}

fun lazyExample() {
    println(lazyValue)
    println(lazyValue)
}

// 可观察属性 Observable
/*
  每当我们给属性赋值时会调用该处理程序（在赋值后执行）
 */
var name: String by Delegates.observable("<no name>") {
        property, old, new -> // 三个参数：被赋值的属性、旧值与新值
    println("$property:$old -> $new")
}

fun observableExample(){
    println(name)
    name = "name1"
    name = "name2"
}

// 委托给另一个属性
// Kotlin v1.4 支持
class PropertyClass {
    var newName: Int = 0
    @Deprecated("Use 'newName' instead", ReplaceWith("newName"))
    var oldName: Int by this::newName
}

fun delegateOtherProperty() {
    val bean = PropertyClass()
    // 通知：'oldName: Int' is deprecated.
    // Use 'newName' instead
    bean.oldName = 42
    println(bean.newName) // 42
}

// 将属性储存在映射中
// 经常出现在像解析 JSON 或者做其他“动态”事情的应用中
class PropertyUser(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int     by map
}

fun delegateMap() {
    val user = PropertyUser(mapOf(
        "name" to "John Doe",
        "age"  to 25
    ))
    println(user.name) // Prints "John Doe"
    println(user.age)  // Prints 25
}
