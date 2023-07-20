package cn.h2uwei.rpc.provider;

import cn.h2uwei.rpc.netty.RpcServer;
import cn.h2uwei.rpc.registry.LocalServiceRegister;
import cn.h2uwei.rpc.service.EchoService;
import cn.h2uwei.rpc.service.impl.EchoServiceImpl;

/**
 * service provider
 *
 * @author h.mongo
 * @date 2023/7/18 17:07
 */
public class ServiceProvider {


    public static void main(String[] args) {
        try {
//            final Thread thread = asyncStart();
            LocalServiceRegister.INSTANCE.registry(EchoService.class, new EchoServiceImpl());
            RpcServer.create(8888).start();
//            thread.start();
//            thread.join();
        } catch (Exception e) {
            System.out.println("启动RpcServer异常了！");
            e.printStackTrace();
        }
    }

    private static Thread asyncStart() {
        return new Thread(() -> {
            try {
                RpcServer.create(9999).start();
            } catch (Exception e) {
                System.out.println("启动RpcServer异常了！");
                e.printStackTrace();
            }
        });
    }
}
