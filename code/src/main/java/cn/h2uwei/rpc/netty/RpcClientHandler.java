package cn.h2uwei.rpc.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;

import java.net.SocketAddress;

/**
 * RpcHandler implement
 *
 * @author h.mongo
 * @date 2023/7/19 10:23
 */
public class RpcClientHandler implements ChannelInboundHandler/*, ChannelOutboundHandler*/ {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " channelRegistered");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " channelUnregistered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " channelActive");
        //发送消息到服务端
        ctx.writeAndFlush(Unpooled.copiedBuffer("建立连接了！", CharsetUtil.UTF_8));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " channelInactive");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " channelRead");
        //获取客户端发送过来的消息
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("收到服务端" + ctx.channel().remoteAddress() + "发送的消息：" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " channelReadComplete");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " userEventTriggered");
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " channelWritabilityChanged");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " handlerAdded");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " handlerRemoved");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " exceptionCaught");
    }


    //------------------- out -------------------------

//    @Override
//    public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " bind");
//    }
//
//    @Override
//    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " connect");
//    }
//
//    @Override
//    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " disconnect");
//    }
//
//    @Override
//    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " close");
//    }
//
//    @Override
//    public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " deregister");
//    }
//
//    @Override
//    public void read(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " read >>> ");
//    }
//
//    @Override
//    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " write >> " + ((ByteBuf) msg).toString(CharsetUtil.UTF_8));
//    }
//
//    @Override
//    public void flush(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " flush >>> ");
//    }
}