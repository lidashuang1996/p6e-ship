package club.p6e.ship.api;

import io.netty.channel.Channel;

/**
 * @author lidashuang
 * @version 1.0
 */
public abstract class OutletPipe {

    protected final String name;
    protected final Channel channel;

    protected long heartbeat;

    public OutletPipe(String name, Channel channel) {
        this.name = name;
        this.channel = channel;
    }

    public void setHeartbeat(long heartbeat) {
        this.heartbeat = heartbeat;
    }

    public String getName() {
        return name;
    }

    public Channel getChannel() {
        return channel;
    }

    public long getHeartbeat() {
        return heartbeat;
    }
}
