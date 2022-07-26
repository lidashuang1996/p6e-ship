package club.p6e.ship.api;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface Inlet {

    public void connect(InletPipe pipe);
    public void shutdown();
}
