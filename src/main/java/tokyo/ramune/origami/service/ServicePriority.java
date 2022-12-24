package tokyo.ramune.origami.service;

public enum ServicePriority {
    SYSTEM(0),
    VERY0_LOW(1),
    LOW(2),
    NORMAL(3),
    HIGH(4),
    VERY_HIGH(5);

    private final int slot;

    ServicePriority(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }
}
