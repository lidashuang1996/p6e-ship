package club.p6e.ship.core;

import club.p6e.ship.api.OutletPipe;
import io.netty.buffer.ByteBuf;

import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public class OutletPipeCore {

    public void create(OutletPipe outletPipe) {
        OutletPipeCache.create(outletPipe);
    }

    public void remove(String name) {
        OutletPipeCache.remove(name);
    }

    public void remove(OutletPipe outletPipe) {
        OutletPipeCache.remove(outletPipe);
    }

    public void push(Map<String, String> data, ByteBuf content) {
       // 执行发送消息
    }

}
