package com.learning;

// One thing the co-op buys. Already written.
public class Produce {

    private final String name;
    private final String unit;
    private final int shillings;
    private final boolean inSeason;

    public Produce(String name, String unit, int shillings, boolean inSeason) {
        this.name = name;
        this.unit = unit;
        this.shillings = shillings;
        this.inSeason = inSeason;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public int getShillings() {
        return shillings;
    }

    public boolean isInSeason() {
        return inSeason;
    }
}
