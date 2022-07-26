package club.p6e.ship.netty;

import club.p6e.ship.api.InletPipe;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public class NettyInletPipe implements InletPipe {

    private final String host;
    private final int port;

    private final Map<String, Object> attributes = new HashMap<>();

    public NettyInletPipe(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public String getHost() {
        return host;
    }

}
