package cn.h2uwei.rpc.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * rpc client base on netty
 *
 * @author h.mongo
 * @date 2023/7/17 19:35
 */
public class RpcClient {

    private final String host;
    private final int port;

    private RpcClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static RpcClient bind(String host, int port) {
        return new RpcClient(host, port);
    }

    public static void main(String[] args) throws Exception {
        new RpcClient("127.0.0.1", 8888).start();
    }

    public void start() throws Exception {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(bossGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new RpcChannelInitializer());

            ChannelFuture future = bootstrap.connect(new InetSocketAddress(port)).sync();
            Channel channel = future.channel();
//            channel.closeFuture().sync();
            channel.writeAndFlush("hello world");
            // 获取控制台输入对象
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String next = scanner.next();
                if ("exit".equals(next)) {
                    break;
                }
                System.out.println("发送: " + next);
                //向服务端写入控制台中输入的信息
                channel.writeAndFlush(Unpooled.copiedBuffer(next, CharsetUtil.UTF_8));
            }
        } finally {
            bossGroup.shutdownGracefully();
        }

    }

    static class RpcChannelInitializer extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new RpcClientHandler());
        }
    }

}
