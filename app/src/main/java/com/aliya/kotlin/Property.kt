package com.aliya.kotlin

/**
 * Property 属性
 *
 * @author a_liYa
 * @date 2020/10/9 09:25.
 *
 */
fun main() {
    val property = Property()
    property.stringRepresentation = "a_liYa"
    println("${property.stringRepresentation} - isEmpty:${property.isEmpty}")
}

class Property {
    /**
     * var <propertyName>[: <PropertyType>] [= <property_initializer>]
     *     [<getter>]
     *     [<setter>]
     */
    private var size: Int = 0

    var isEmpty: Boolean // 可以从 getter 推断出属性类型，则可省略
        get() = this.size == 0
        set(value) {
            size = 10
        }

    var stringRepresentation: String = "string"
        get() = field.toString()
        set(value) {
            size = 2
            field = value // 通过 field 引用幕后字段；field 标识符只能用在属性的访问器内。
        }

    // 如果你需要改变一个访问器的可见性或者对其注解，但是不需要改变默认的实现，你可以定义访问器而不定义其实现:
    var setterVisibility: String = "abc"
        private set // 此 setter 是私有的并且有默认实现
    var setterWithAnnotation: Any? = null
        @Inject set // 用 Inject 注解此 setter

    // 幕后字段
    /**
     * 何时会为属性生成幕后字段？
     * 1、如果属性至少一个访问器使用默认实现；
     * 2、自定义访问器通过 field 引用幕后字段；
     */

    // 幕后属性
    // 需求：希望一个属性：对外表现为只读，对内表现为可读可写，我们将这个属性定义为幕后属性
    private var _table: Map<String, Int>? = null
    val table: Map<String, Int>
        get() {
            if (_table == null) {
                _table = HashMap() // 类型参数已推断出
            }
            return _table ?: throw AssertionError("Set to null by another thread")
        }
    // 可以通过修改属性 table 写访问器 setter 为 private 来实现幕后属性的需求
}

annotation class Inject