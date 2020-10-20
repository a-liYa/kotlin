package com.aliya.kotlin

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

/**
 * 内联函数
 *
 * @author a_liYa
 * @date 2020/10/19 18:45.
 *
 */
/*
    使用高阶函数会带来一些运行时的效率损失：每一个函数都是一个对象，并且会捕获一个闭包。
    inline 消除这类开销

    禁止内联 noinline
 */


/*
    具体化的类型参数
 */
// 这么写不够优雅
fun <T> Context.findOfType(clazz: Class<T>): T? {
    var base = this
    while (base != null && !clazz.isInstance(base) && base is ContextWrapper) {
        base = base.baseContext
    }
    return base as T?
}

// 内联函数支持具体化的类型参数
inline fun <reified T> Context.findOfType(): T? {
    var base = this
    while (base != null && base !is T && base is ContextWrapper) {
        base = base.baseContext
    }
    return base as T?
}

fun main() {
    var context = Activity()
    // 不优雅的使用
    var activity = context.findOfType(Activity::class.java)
    println(activity)

    // 优雅使用
    activity = context.findOfType<Activity>()
    println(activity)
}