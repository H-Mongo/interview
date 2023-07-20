package cn.h2uwei.rpc.consumer;

import cn.h2uwei.rpc.netty.RpcClient;

import java.util.Scanner;

/**
 * service consumer
 *
 * @author h.mongo
 * @date 2023/7/18 17:07
 */
public class ServiceConsumer {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            RpcClient.bind("127.0.0.1",8888).start();
//            Thread thread = asyncStart(scanner);
//            thread.start();
//            thread.join();
        } catch (Exception e) {
            System.out.println("启动RpcClient异常了！");
            e.printStackTrace();
        }
    }

    private static Thread asyncStart(Scanner scanner) {
        return new Thread(() -> {
            try {
                RpcClient.bind("127.0.0.1",9999).start();
            } catch (Exception e) {
                System.out.println("启动RpcClient异常了！");
                e.printStackTrace();
            }
        });
    }

}
