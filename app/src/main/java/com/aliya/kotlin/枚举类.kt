package com.aliya.kotlin

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*
import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator

/**
 * 枚举类
 *
 * @author a_liYa
 * @date 2020/10/12 14:39.
 *
 */
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

// 初始化
enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

// 匿名类
enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}

// 在枚举类中实现接口
@RequiresApi(Build.VERSION_CODES.N)
enum class IntArithmetics : BinaryOperator<Int>, IntBinaryOperator {
    PLUS {
        override fun apply(t: Int, u: Int): Int = t + u
    },
    TIMES {
        override fun apply(t: Int, u: Int): Int = t * u
    };

    override fun applyAsInt(t: Int, u: Int) = apply(t, u)
}

enum class RGB { RED, GREEN, BLUE }

inline fun <reified T : Enum<T>> printAllValues() {
    print(enumValues<T>().joinToString { it.name })
}

fun main() {
    val NORTH = Direction.valueOf("NORTH")
    println(NORTH.name)
    println(Direction.values().asList())

    printAllValues<RGB>()
}


