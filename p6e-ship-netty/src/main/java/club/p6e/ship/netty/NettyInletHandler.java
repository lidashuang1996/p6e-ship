package club.p6e.ship.netty;

import club.p6e.ship.core.CoreFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lidashuang
 * @version 1.0
 */
public class NettyInletHandler implements ChannelInboundHandler {

    /** 日志系统 */
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyInletHandler.class);

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

    }

    @Override
    public void channelInactive(ChannelHandlerContext context) {
        LOGGER.info("channelInactive ==> " + context);
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object o) {
        LOGGER.info("channelRead ==> " + context + ", message: " + o);
        if (o instanceof final NettyMessage message
                && message.getData() != null
                && message.getData().get("type") != null) {
            final String type = message.getData().get("type");
            if (type.equals("1000") || type.equals("1001")) {
                // 消息是心跳消息不用继续下发
                LOGGER.info("channelRead message type ==> " + type + ", message type screening !");
            } else {
                // 消息向订阅该频道的人继续下发
                CoreFactory.getOutletPipeCore().push(message.getData(), message.getContent());
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext context) {
        LOGGER.info("channelReadComplete ==> " + context);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext context, Object o) {
        LOGGER.info("userEventTriggered ==> " + context + ", object: " + o);
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
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable) {
        LOGGER.info("exceptionCaught ==> " + context + ", throwable: " + throwable.getMessage());
        LOGGER.error(throwable.getMessage());
    }

}
