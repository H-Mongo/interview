package cn.h2uwei.rpc.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * DiscardServer
 *
 * @author h.mongo
 * @date 2023/7/18 11:11
 */
public class DiscardServer {
    private final int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 9999;
        }
        new DiscardServer(port).run();
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
//                            ch.pipeline()
//                                    .addLast(new DiscardServerHandler())
//                                    .addLast(new ReceivedServerHandler())
//                                    .addLast(new EchoServerHandler())
//                                    .addLast(new TimeServerHandler());
                            System.out.println("jjjjjj");
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 绑定并启动连接
            ChannelFuture future = bootstrap.bind(port).sync();
            // socket连接关闭后，优雅的关闭服务端通道
            future.channel().closeFuture().sync();
        } finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

//    static class DiscardServerHandler extends ChannelHandlerAdapter {
//        @Override
//        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
////            System.out.println("msg type:" + msg.getClass().getSimpleName());
//            // 偷偷丢弃数据
//            ((ByteBuf) msg).release();
//        }
//
//        @Override
//        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//            cause.printStackTrace();
//            ctx.channel();
//        }
//    }


//    static class ReceivedServerHandler extends ChannelHandlerAdapter {
//        @Override
//        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//            ctx.write(msg); // (1)
//            ctx.flush(); // (2)
//        }
//
//        @Override
//        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//            cause.printStackTrace();
//            ctx.channel();
//        }
//    }

//    static class EchoServerHandler extends ChannelHandlerAdapter {
//
//        @Override
//        public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
//            super.connect(ctx, remoteAddress, localAddress, promise);
////            InetSocketAddress remoteInetSocketAddress = (InetSocketAddress) remoteAddress;
////            System.out.println(String.format("client[%s:%s] connected!", remoteInetSocketAddress.getHostName(), remoteInetSocketAddress.getPort()));
////            ctx.channel().write("hello client!", promise);
//        }
//
//        @Override
//        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//            ByteBuf in = (ByteBuf) msg;
//            try {
//                while (in.isReadable()) { // (1)
//                    System.out.print((char) in.readByte());
//                    System.out.flush();
//                }
//            } finally {
////                ReferenceCountUtil.release(msg); // (2)
//            }
//        }

//        @Override
//        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//            cause.printStackTrace();
//            ctx.channel();
//        }
//    }


//    static class TimeServerHandler extends ChannelHandlerAdapter {
//
//        @Override
//        public void channelActive(final ChannelHandlerContext ctx) { // (1)
//            final ByteBuf time = ctx.alloc().buffer(4); // (2)
//            time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
//
//            final ChannelFuture f = ctx.writeAndFlush(time); // (3)
//            f.addListener(new ChannelFutureListener() {
//
//                @Override
//                public void operationComplete(ChannelFuture future) {
//                    assert f == future;
//                    ctx.close();
//                }
//            }); // (4)
//        }
//
//        @Override
//        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//            cause.printStackTrace();
//            ctx.close();
//        }
//    }

}
