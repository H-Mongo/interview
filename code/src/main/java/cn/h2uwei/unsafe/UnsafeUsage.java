package cn.h2uwei.unsafe;

import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.IntStream;

/**
 * 应用Unsafe类型
 *
 * @author h.mongo
 * @date 2023/7/28 22:54
 */
public class UnsafeUsage {

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

    public static void main(String[] args) throws Exception {
//        useUnsafe2CreateClassInstance();
//        useUnsafe2SetFieldValue();
//        useUnsafe2ThrowException();
//        useUnsafe2AllocateOffHeapMemory();
//        useUnsafe2ImplCASCounter();
        useUnsafe2SwitchThreadsContext();
    }

    /**
     * 使用unsafe来完成类的实例化
     * <pre>
     * 结论:
     *     采用new进行类实例化操作会执行构造方法中的赋值代码块；但是通过Unsafe进行类实例化并不会执行构造方法中的代码块，因为它只是进行了内存地址分配以及为属性赋予默认值
     * </pre>
     */
    private static void useUnsafe2CreateClassInstance() throws Exception {
        System.out.println("这里的User类型被重写了默认构造方法，并在构造方法内完成了对name字段的赋值，接下来通过两种不同的实例化对象，并访问该属性！");
        System.out.println("1）new关键字：" + new User().getName());
        User u = (User) unsafe.allocateInstance(User.class);
        System.out.println("1）Unsafe#allocateInstance方法：" + u.getName());
    }

    /**
     * 使用unsafe来完成实例对象的属性赋值
     */
    private static void useUnsafe2SetFieldValue() throws Exception {
        System.out.println("采用Unsafe修改User实例name字段的值：");
        User user = new User();
        System.out.println("1）name修改前：" + user.getName());
        final Field name = User.class.getDeclaredField("name");
        // 获取name属性的指针（即内存引用地址）
        long nameAddr = unsafe.objectFieldOffset(name);
        unsafe.putObject(user, nameAddr, "xiao li");
        System.out.println("1）name修改后：" + user.getName());
    }

    /**
     * 使用unsafe抛出异常
     */
    private static void useUnsafe2ThrowException() {
        System.out.println("这里会出现通过Unsafe所抛出的异常，调用方并不需要显示捕获（即使抛出一个受检异常）：");
        unsafe.throwException(new IOException("我是通过Unsafe抛出的IO异常"));
    }

    /**
     * 使用unsafe分配一块对堆外内存
     */
    private static void useUnsafe2AllocateOffHeapMemory() throws Exception {
        System.out.println("通过unsafe分配对外内存，该内存不受JVM的管制（GC无法进行回收，需要手动释放）：");
        long SUPER_SIZE = (long) Integer.MAX_VALUE * 2;
        OffHeapArray array = new OffHeapArray(SUPER_SIZE);

        int sum = 0;
        for (int i = 0; i < 100; i++) {
            array.set((long) Integer.MAX_VALUE + i, (byte) 3);
            sum += array.get((long) Integer.MAX_VALUE + i);
        }

        System.out.printf("数组真实大小size：%d, 创建指定的size：%d%n", array.size(), SUPER_SIZE);
        System.out.printf("数组元素累加和sum：%d, 期待的结果：300%n", sum);
    }

    /**
     * 使用unsafe实现一个CAS计数器
     */
    private static void useUnsafe2ImplCASCounter() throws Exception {
        System.out.println("这里演示基于Unsafe实现的CAS计数器：");
        int NUM_OF_THREADS = 1_000;
        int NUM_OF_INCREMENTS = 10_000;
        ExecutorService service = Executors.newFixedThreadPool(NUM_OF_THREADS);
        CASCounter casCounter = new CASCounter();
//        CountDownLatch latch = new CountDownLatch(NUM_OF_THREADS - 1);
        IntStream.rangeClosed(0, NUM_OF_THREADS - 1)
                .forEach(i -> {
                    Future<?> future = service.submit(() -> IntStream
                            .rangeClosed(0, NUM_OF_INCREMENTS - 1)
                            .forEach(j -> casCounter.increment()));
                    try {
                        future.get();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                });
        System.out.printf("期待计数器返回的数量：%d，计数器真实结果：%d%n", NUM_OF_INCREMENTS * NUM_OF_THREADS, casCounter.getCounter());
    }

    /**
     * 使用unsafe实现线程切换
     */
    private static void useUnsafe2SwitchThreadsContext() throws Exception {

        System.out.println("LockSupport内部基于Unsafe实现了线程切换，通过它来操作线程切换：");
        CountDownLatch latch = new CountDownLatch(2);
        Thread a = new Thread(() -> {
            System.out.println("1. 当前线程[thread-A]执行这句话后，开始切换线程[thread-B]执行，[thread-B]执行完成后唤起该线程继续执行！");
            LockSupport.park();
            System.out.println("3. 当前线程[thread-A]被[thread-B]唤醒后执行当前这句话，[thread-A]执行完成！");
            latch.countDown();
        }, "thread-A");
        Thread b = new Thread(() -> {
            System.out.println("2. 当前线程[thread-B]执行这句话后，执行完成并唤起被挂起的[thread-B]！");
            LockSupport.unpark(a);
            latch.countDown();
        }, "thread-B");
        a.start();
        b.start();
        latch.await();

    }

}
