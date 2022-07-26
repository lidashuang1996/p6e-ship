package club.p6e.ship.core;

import club.p6e.ship.api.OutletPipe;

import java.util.Hashtable;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public final class OutletPipeCache {

    private static final Map<String, OutletPipe> DATA = new Hashtable<>();

    private final OutletPipeTroughGroup outletPipeTroughGroup = new OutletPipeTroughGroup();

    public static void create(OutletPipe outletPipe) {
        DATA.put(outletPipe.toString(), outletPipe);
    }

    public static void remove(String name) {
        remove(DATA.get(name));
    }

    public static void remove(OutletPipe outletPipe) {
        DATA.remove(outletPipe);
    }

    public OutletPipeTroughGroup getOutletPipeTroughGroup() {
        return outletPipeTroughGroup;
    }
}
