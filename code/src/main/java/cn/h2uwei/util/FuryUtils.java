package cn.h2uwei.util;

import cn.h2uwei.rpc.model.Invocation;
import io.fury.Fury;
import io.fury.Language;

/**
 * fury utils
 *
 * @author h.mongo
 * @date 2023/7/20 16:22
 */
public class FuryUtils {
    private FuryUtils() {
    }

//    public static void main(String[] args) {
//        Invocation invocation = new Invocation();
//        invocation.setClassName("cn.h2uwei.rpc.service.EchoService");
//        invocation.setMethodName("echo");
//        invocation.setParams(new Object[]{"mongo"});
//        serialize(Invocation.class, invocation);
//    }

    private static final Fury FURY;

    static {
        FURY = Fury.builder().withLanguage(Language.JAVA)
                .withRefTracking(true)
                // Allow to deserialize objects unknown types,
                // more flexible but less secure.
                // .withSecureMode(false)
                .build();
    }

    public static <T> byte[] serialize(Class<T> classType, T value) {
        // Registering types can reduce class name serialization overhead, but not mandatory.
        // If secure mode enabled, all custom types must be registered.
        FURY.register(classType);
        byte[] bytes = FURY.serialize(value);
//        System.out.println(FURY.deserialize(bytes));
        return bytes;
    }

    public static Object deserialize(byte[] bytes) {
        return FURY.deserialize(bytes);
    }


}
