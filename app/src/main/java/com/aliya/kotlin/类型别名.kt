package com.aliya.kotlin

import java.io.File
import java.net.HttpURLConnection

/**
 * 类型别名
 *
 * @author a_liYa
 * @date 2020/10/12 15:50.
 *
 */

typealias httpSet = Set<HttpURLConnection>

typealias FileTable<K> = MutableMap<K, MutableList<File>>

// 可为函数类型提供另外的别名
typealias MyHandler = (Int, String, Any) -> Unit

typealias Predicate<T> = (T) -> Boolean

// 可为内部类和嵌套类创建新名称：
class OuterC {
    inner class Inner
}

typealias CInner = OuterC.Inner
