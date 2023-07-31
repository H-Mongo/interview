package cn.h2uwei.unsafe;

import sun.misc.Unsafe;

/**
 * 基于对外内存分配空间的的数组
 *
 * @author h.mongo
 * @date 2023/7/29 14:09
 */
public class OffHeapArray {
    private final static int BYTE = 1;
    private final long size;
    private final long address;

    public OffHeapArray(long size) {
        this.size = size;
        address = getUnsafe().allocateMemory(size * BYTE);
    }

    private Unsafe getUnsafe() {
        return UnsafeHolder.getUnsafe();
    }

    public void set(long i, byte value) {
        getUnsafe().putByte(address + i * BYTE, value);
    }

    public int get(long idx) {
        return getUnsafe().getByte(address + idx * BYTE);
    }

    public long size() {
        return size;
    }

    public void freeMemory() {
        getUnsafe().freeMemory(address);
    }
}
