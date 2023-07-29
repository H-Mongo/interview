package cn.h2uwei.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe实例持有者
 *
 * @author zuweih
 * @date 2023/7/29 14:23
 */
public class UnsafeHolder {

    private UnsafeHolder() {
    }

    private static Unsafe unsafe;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            System.out.println("获取Unsafe实例出错了！");
            e.printStackTrace();
        }

    }

    public static Unsafe getUnsafe() {
        return unsafe;
    }
}
