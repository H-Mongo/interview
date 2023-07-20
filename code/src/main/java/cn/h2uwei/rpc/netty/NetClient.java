package cn.h2uwei.rpc.netty;

import java.io.Closeable;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

/**
 * NettyClient
 *
 * @author h.mongo
 * @date 2023/7/18 14:30
 */
public class NetClient {
    public static void main(String[] args) throws Exception {
        Scanner reader = new Scanner(System.in);
        String msg = null;
//
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(8888));
        while (true) {
//            readMsg(socket.getInputStream());
            if (reader.hasNext()) {
                msg = reader.next();
            }
            System.out.println("input msg:" + msg);
            if ("exit".equals(msg)) {
                break;
            }
//            if (msg != null) {
                socket.getOutputStream().write(msg.getBytes(StandardCharsets.UTF_8));
                close(socket.getOutputStream());
//            }
        }
        close(socket.getInputStream());
        close(socket.getOutputStream());
    }

    private static void readMsg(InputStream in) throws Exception {
        byte[] bytes = new byte[512];
        StringBuilder sb = new StringBuilder();
        while (in.read(bytes, 0, bytes.length) != -1) {
            sb.append(Arrays.toString(bytes));
        }
        System.out.println("accept: " + sb);
        close(in);
    }

    private static void close(Closeable close) {
        try {
            if (close != null) {
                close.close();
            }
        } catch (Exception e) {
            System.out.println("异常：");
            e.printStackTrace();
        }
    }

}
