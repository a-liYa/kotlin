package com.aliya.kotlin

/**
 * StringTemplate
 *
 * @author a_liYa
 * @date 2020/8/23 20:17.
 *
 */
fun main() {

    stringTemplate()

    // 支持三个引号 """ 扩起来的字符串，支持多行字符串
    // --- 自己不用关系 \n 换行
    val infoMesage = """
        AAAAAAAAAAA
        BBBBBBBBBBB
        CCCCCCCCCCC
        DDDDDDDDDDD
        EEEEEEEEEEE
    """
    println(infoMesage)

    val infoMesage2 = """
        AAAAAAAAAAA
        BBBBBBBBBBB   
        CCCCCCCCCCC
        DDDDDDDDDDD
        EEEEEEEEEEE
    """.trimIndent()  // 去除行的相同前置空格
    println(infoMesage2 + "\n")

    val infoMesage3 = """
        ?AAAAAAAAAAA
          ?BBBBBBBBBBB
        ?CCCCCCCCCCC
        ?DDDDDDDDDDD
        ?EEEEEEEEEEE
    """.trimMargin("?")  // 去除每行?前面的空格
    println(infoMesage3 + "\n")

    // 需求：显示特殊符号 $
    val price = """${'$'}9.99"""
    println(price)
}

// $varName 表示变量值

// ${varName.fun()} 表示变量的方法返回值

// 字符串模板示例
fun stringTemplate() {
    var a = 1
    // 模板中的简单名称：
    val s1 = "a is $a"

    a = 2
    // 模板中的任意表达式：
    val s2 = "${s1.replace("is", "was")}, but now is $a"

    println(s2)
}