package com.aliya.kotlin

/**
 * kotlin - 实现静态的三种方式
 *
 * 1. companion object - 伴随对象
 * 2. object 单例 - 静态单例，companion object 类似
 * 3. const - 包内唯一性，脱离类的束缚，kotlin 的特性
 *
 * 注：@JvmField / @JvmStatic 可使 Java 与 Kotlin 调用一致
 *
 * @author a_liYa
 * @date 2021/5/4 19:38.
 *
 */
fun main() {

    // java：Static.INSTANCE.staticMethod();
    Static.staticMethod()

    // java：Static.jvmStaticMethod()
    Static.jvmStaticMethod()

    StaticOuter.nameStatic
}

/* java code
public final class Static {
   private static String sField;
   public static String sJvmField;
   public static final Static INSTANCE;
   public final String getSField() {
      return sField;
   }

   public final void setSField(@NotNull String var1) {
      sField = var1;
   }

   public final void staticMethod() {
   }

   public static final void jvmStaticMethod() {
   }

   private Static() {
   }
   static {
      Static var0 = new Static();
      INSTANCE = var0;
      sField = "field";
   }
}
 */
object Static {

    var sField = "field"

    @JvmField
    var sJvmField = "jvm field"

    fun staticMethod() {
    }

    @JvmStatic
    fun jvmStaticMethod() {
    }
}

// Java使用：静态Kt.const_static_name
const val const_static_name: String = "变量名 - 全局唯一不能重复"


/* java code
public final class StaticOuter {
   private static String nameStatic = "BB";
   public static final StaticOuter.Companion Companion = new StaticOuter.Companion((DefaultConstructorMarker)null);

   public static final class Companion {
      public final String getNameStatic() {
         return StaticOuter.nameStatic;
      }

      public final void speakStatic() {
      }

      private Companion() {
      }
   }
}
 */
class StaticOuter {
    companion object {
        var nameStatic: String = "BB"
        fun speakStatic() {}

    }
}