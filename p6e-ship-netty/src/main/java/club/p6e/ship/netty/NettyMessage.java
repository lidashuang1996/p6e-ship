package club.p6e.ship.netty;

import io.netty.buffer.ByteBuf;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public class NettyMessage {

    private ByteBuf content;
    private Map<String, String> data;

    public NettyMessage() {}

    public NettyMessage(Map<String, String> data, ByteBuf content) {
        this.data = data;
        this.content = content;
    }

    public ByteBuf getContent() {
        return content;
    }

    public void setContent(ByteBuf content) {
        this.content = content;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
