package club.p6e.ship.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.CombinedChannelDuplexHandler;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public class NettyCodec extends CombinedChannelDuplexHandler<NettyCodec.Decoder, NettyCodec.Encoder> {

    public NettyCodec() {
        this.init(new Decoder(), new Encoder());
    }

    public static class Decoder extends ByteToMessageDecoder {

        @Override
        protected void decode(ChannelHandlerContext context, ByteBuf byteBuf, List<Object> list) throws Exception {

        }
    }

    public static class Encoder extends MessageToByteEncoder<NettyMessage> {

        @Override
        protected void encode(ChannelHandlerContext context, NettyMessage o, ByteBuf byteBuf) throws Exception {
            if (o != null && o.getContent() != null) {
                final ByteBuf buf = ByteBufUtil.writeUtf8();
                final int cLength = o.getContent().readableBytes();
                if (o.getData() == null) {

                } else {

                }
            }
        }
    }
}
