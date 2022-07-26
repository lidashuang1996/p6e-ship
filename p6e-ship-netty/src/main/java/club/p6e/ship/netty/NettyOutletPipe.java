package club.p6e.ship.netty;

import club.p6e.ship.api.OutletPipe;
import io.netty.channel.Channel;

/**
 * @author lidashuang
 * @version 1.0
 */
public class NettyOutletPipe extends OutletPipe {

    public NettyOutletPipe(String name, Channel channel) {
        super(name, channel);
    }

}
