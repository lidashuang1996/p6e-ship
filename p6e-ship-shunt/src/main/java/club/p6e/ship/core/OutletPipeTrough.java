package club.p6e.ship.core;

import club.p6e.ship.api.OutletPipe;
import java.util.LinkedList;

/**
 * @author lidashuang
 * @version 1.0
 */
class OutletPipeTrough {

    private final LinkedList<OutletPipe> linkedList;

    public OutletPipeTrough() {
        this.linkedList = new LinkedList<>();
    }

    public void create(OutletPipe outletPipe) {
        this.linkedList.add(outletPipe);
    }

    public void remove(OutletPipe outletPipe) {
        this.linkedList.remove(outletPipe);
    }

}
