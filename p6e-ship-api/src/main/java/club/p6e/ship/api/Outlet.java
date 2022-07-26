package club.p6e.ship.api;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface Outlet {

    public void bind(int port);
    public void shutdown();

}
