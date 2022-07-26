package club.p6e.ship.core;

import club.p6e.ship.api.OutletPipe;

/**
 * @author lidashuang
 * @version 1.0
 */
class OutletPipeTroughGroup {

    private volatile int online = 0;
    private volatile OutletPipeTrough[] troughs = new OutletPipeTrough[0];

    public void create(OutletPipe outletPipe) {
        synchronized (this) {
            if (this.isExpansion()) {
                this.expansion();
            }
            this.online ++;
            this.troughs[this.troughs.length - 1].create(outletPipe);
        }
    }

    public void remove(OutletPipe outletPipe) {
        synchronized (this) {
            if (this.isShrinkage()) {
                this.shrinkage();
            }
            this.online --;
            this.troughs[this.troughs.length - 1].remove(outletPipe);
        }
    }

    private void expansion() {
        final OutletPipeTrough[] source = this.troughs;
        final OutletPipeTrough[] extended = new OutletPipeTrough[source.length + 1];
        extended[source.length] = new OutletPipeTrough();
        System.arraycopy(source, 0, extended, 0, source.length);
        this.troughs = extended;
    }

    private void shrinkage() {

    }

    private boolean isExpansion() {
        return troughs.length <= 0 && online + 50 <= troughs.length * 100;
    }

    private boolean isShrinkage() {
        return troughs.length > 1 && online * 50 <= troughs.length * 100;
    }

}
