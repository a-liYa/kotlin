package com.aliya.kotlin

/**
 * Standards 编码规范
 *
 * @author a_liYa
 * @date 2020/8/27 09:16.
 *
 */
// 源文件名称：首字母大写的驼峰风格[单个类文件名应该与该类的名称相同, 其他情况选择描述该文件所包含内容的名称]

// 源文件组织：鼓励多个紧密关联的声明（类、顶级函数或者属性）放在同一个 Kotlin 源文件中

// 类布局
/*
    - 属性声明与初始化块
    - 次构造函数
    - 方法声明
    - 伴生对象
 */

// 命名规则
/*
    - 函数名：函数、属性与局部变量的名称以小写字母开头、使用驼峰风格，例外：创建类实例的工厂函数可以与类名相同的名称
    - 属性名：
        - 常量名：（const / val） 使用大写、下划线分割的名称。
        - 保存单例对象引用的属性的名称可以使用首字母大写的驼峰风格。
        - 枚举常量，参考常量名命名
        - 幕后属性，使用下划线作前缀
    - 使用首字母缩写作为名称的一部分时，若缩写由两个字母组成，就将其大写（IOStream）；三个及以上时，就只大写首字母
 */

// 类头格式化
/*
    - 具有少数主构造函数参数的类可以写成一行：
        class Person(id: Int, name: String)
    - 具有较长类头的类应该格式化
        class Person(
            id: Int,
            name: String,
            surname: String
        ): Human(id, name) { /*……*/ }
 */

// 函数格式化
/*
如果函数签名不适合单行，请使用以下语法（与构造函数参数一致）：
fun longMethodName(
    argument: ArgumentType = defaultValue,
    argument2: AnotherArgumentType
): ReturnType {
        // 函数体
}
 */

// 方法调用格式化
/*
在较长参数列表的左括号后添加一个换行符，将密切相关的多个参数分在同一行
drawSquare(
    x = 10, y = 10,
    width = 100, height = 100,
    fill = true
)
 */

// 控制流语句格式化
/*
if (!component.isSyncing &&
    !hasAnyKotlinRuntimeInScope(module)
) {
    return createKotlinNotConfiguredPanel(module)
}
 */

// 链式调用换行
/*
当对链式调用换行时，将 . 字符或者 ?. 操作符放在下一行
val anchor = owner
    ?.firstChild!!
    .siblings(forward = true)
    .dropWhile { it is PsiComment || it is PsiWhiteSpace }
 */


// 修饰符顺序
/*
    public / protected / private / internal
    expect / actual
    final / open / abstract / sealed / const
    external
    override
    lateinit
    tailrec
    vararg
    suspend
    inner
    enum / annotation
    companion
    inline
    infix
    operator
    data
 */
