package cn.h2uwei.unsafe;

import sun.misc.Unsafe;

/**
 * 基于CAS实现的计数器
 *
 * @author h.mongo
 * @date 2023/7/29 14:21
 */
class CASCounter {
    private final Unsafe unsafe;
    private volatile long counter = 0;
    private long offset;


    public CASCounter() throws NoSuchFieldException {
        unsafe = UnsafeHolder.getUnsafe();
        offset = unsafe.objectFieldOffset(CASCounter.class.getDeclaredField("counter"));
    }

    public void increment() {
        long before = counter;
        while (!unsafe.compareAndSwapLong(this, offset, before, before + 1)) {
            before = counter;
        }
    }

    public long getCounter() {
        return counter;
    }
}
