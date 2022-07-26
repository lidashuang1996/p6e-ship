package club.p6e.ship.netty;

import club.p6e.ship.core.CoreFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public class NettyOutletHandler implements ChannelInboundHandler {

    /** 日志系统 */
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyOutletHandler.class);

    /** 出口管道对象 */
    private NettyOutletPipe outletPipe;

    @Override
    public void channelRegistered(ChannelHandlerContext context) {
        LOGGER.info("channelRegistered ==> " + context);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext context) {
        LOGGER.info("channelUnregistered ==> " + context);
    }

    @Override
    public void channelActive(ChannelHandlerContext context) {
        LOGGER.info("channelActive ==> " + context);
        this.outletPipe = new NettyOutletPipe(context.name(), context.channel());
        CoreFactory.getOutletPipeCore().create(this.outletPipe);
    }

    @Override
    public void channelInactive(ChannelHandlerContext context) {
        LOGGER.info("channelInactive ==> " + context);
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object o) {
        LOGGER.info("channelRead ==> " + context + ", message: " + o);
        // 心跳判断
        if (o instanceof final NettyMessage message
                && message.getData() != null
                && message.getData().get("type") != null
                && message.getData().get("type").equals("1000")) {
            // 更新心跳时间戳
            this.outletPipe.setHeartbeat(System.currentTimeMillis());
            // 向客户端发送心跳确认消息
            final NettyMessage h = new NettyMessage();
            final Map<String, String> m = new HashMap<>(1);
            m.put("type", "1001");
            h.setData(m);
            context.channel().writeAndFlush(h);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext context) {
        LOGGER.info("channelReadComplete ==> " + context);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext context, Object o) {
        LOGGER.info("userEventTriggered ==> " + context);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext context) {
        LOGGER.info("channelWritabilityChanged ==> " + context);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext context) {
        LOGGER.info("handlerAdded ==> " + context);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext context) {
        LOGGER.info("handlerRemoved ==> " + context);
        CoreFactory.getOutletPipeCore().remove(this.outletPipe);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable) {
        LOGGER.info("exceptionCaught ==> " + context + ", throwable: " + throwable.getMessage());
        LOGGER.error(throwable.getMessage());
    }

}
