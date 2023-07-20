package cn.h2uwei.rpc.netty;

import cn.h2uwei.rpc.model.Invocation;
import cn.h2uwei.rpc.protocol.RpcProtocol;
import cn.h2uwei.rpc.registry.ServiceRegister;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.lang.reflect.Method;

/**
 * RpcHandler implement
 *
 * @author h.mongo
 * @date 2023/7/19 10:23
 */
public class RpcServerHandler implements ChannelInboundHandler {

    private final ServiceRegister register;

    public RpcServerHandler(ServiceRegister register) {
        this.register = register;
    }


    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//        ByteBuf
//        System.out.println(ctx.channel().remoteAddress() + " channelRegistered");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " channelUnregistered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " channelActive");
        channels.add(ctx.channel());
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
        String rpcSchema = byteBuf.toString(CharsetUtil.UTF_8);
        System.out.println("[" + ctx.channel().remoteAddress() + "]发来消息：" + rpcSchema);
        try {
            Invocation invocation = RpcProtocol.praseInvocation(rpcSchema);
            Object result = null;
            Object o = register.find(invocation.getClassName());
            for (Method method : o.getClass().getDeclaredMethods()) {
                if (method.getName().equals(invocation.getMethodName())) {
                    result = method.invoke(o, invocation.getParams());
                    break;
                }
            }
            ctx.writeAndFlush(Unpooled.copiedBuffer("rpc调用..." + result, CharsetUtil.UTF_8));
        } catch (Exception e) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(e.getMessage(), CharsetUtil.UTF_8));
        }
//        System.out.println("收到客户端" + ctx.channel().remoteAddress() + "发送的消息：" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(ctx.channel().remoteAddress() + " channelReadComplete");
        //发送消息给客户端
//        ctx.writeAndFlush(Unpooled.copiedBuffer("服务端已收到消息，请求处理完毕！", CharsetUtil.UTF_8));
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
}