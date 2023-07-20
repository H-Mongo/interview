package cn.h2uwei.rpc.netty;

import cn.h2uwei.rpc.registry.LocalServiceRegister;
import cn.h2uwei.rpc.service.EchoService;
import cn.h2uwei.rpc.service.impl.EchoServiceImpl;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * rpc server base on netty
 *
 * @author h.mongo
 * @date 2023/7/17 19:35
 */
public class RpcServer {
    private final int port;

    private RpcServer(int port) {
        this.port = port;
    }

    public static RpcServer create(int port) {
        return new RpcServer(port);
    }

    public static void main(String[] args) throws Exception {
        new RpcServer(8888).start();
    }

    public void start() throws Exception {
        LocalServiceRegister.INSTANCE.registry(EchoService.class, new EchoServiceImpl());
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new RpcServerHandler(LocalServiceRegister.INSTANCE));
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

}
