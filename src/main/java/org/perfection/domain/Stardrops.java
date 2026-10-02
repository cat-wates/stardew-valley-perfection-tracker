package org.perfection.domain;

public class Stardrops {
    public static final int MAX_STARDROPS = 7;
    private int count;

    public Stardrops() {
        this.count = 0;
    }

    public Stardrops(int count) {
        setCount(count);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (count < 0) {
            count = 0;
        } else if (count > MAX_STARDROPS) {
            count = MAX_STARDROPS;
        }
        this.count = count;
    }

    public int getMaxCount() {
        return MAX_STARDROPS;
    }
}