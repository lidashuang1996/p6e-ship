package club.p6e.ship.core;

/**
 * @author lidashuang
 * @version 1.0
 */
public class CoreFactory {

    private static OutletPipeCore outletPipeCore;


    static {
        outletPipeCore = new OutletPipeCore();
    }

    public static void setOutletPipeCore(OutletPipeCore outletPipeCore) {
        CoreFactory.outletPipeCore = outletPipeCore;
    }

    public static OutletPipeCore getOutletPipeCore() {
        return CoreFactory.outletPipeCore;
    }
}
