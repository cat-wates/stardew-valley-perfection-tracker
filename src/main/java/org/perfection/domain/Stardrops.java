package org.perfection.domain;

public class Stardrops {
    public static final int MAX_STARDROPS = 7;
    private final int count;

    public Stardrops(int count) {
        if (count < 0) {
            count = 0;
        } else if (count > MAX_STARDROPS) {
            count = MAX_STARDROPS;
        }
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public int getMaxCount() {
        return MAX_STARDROPS;
    }
}