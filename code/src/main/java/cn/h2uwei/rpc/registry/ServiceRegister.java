package cn.h2uwei.rpc.registry;

/**
 *
 * service register
 *
 * @author h.mongo
 * @date 2023/7/18 16:54
 */
public interface ServiceRegister {

    <T> void registry(Class<T> serviceClass, T serviceImpl);

    <T> T find(Class<T> serviceClass);

    <T> T find(String serviceName);

}
