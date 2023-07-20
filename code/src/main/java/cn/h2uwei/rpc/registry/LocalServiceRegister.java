package cn.h2uwei.rpc.registry;

import cn.h2uwei.rpc.service.EchoService;
import cn.h2uwei.rpc.service.impl.EchoServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * local service register
 *
 * @author h.mongo
 * @date 2023/7/18 16:57
 */
public class LocalServiceRegister implements ServiceRegister {

    public static final LocalServiceRegister INSTANCE = new LocalServiceRegister();

    private static final Map<String, Object> serviceMap = new HashMap<>(64);


    @Override
    public <T> void registry(Class<T> serviceClass, T serviceImpl) {
        serviceMap.put(buildServiceKey(serviceClass), serviceImpl);
    }

    @Override
    public <T> T find(Class<T> serviceClass) {
        return find(buildServiceKey(serviceClass));
    }

    @Override
    public <T> T find(String serviceName) {
        return (T) serviceMap.get(serviceName);
    }

    private static <T> String buildServiceKey(Class<T> serviceClass) {
        return serviceClass.getCanonicalName();
    }

}
