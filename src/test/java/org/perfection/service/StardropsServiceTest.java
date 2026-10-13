package org.perfection.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.perfection.domain.Stardrops;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StardropsServiceTest {
    @TempDir
    Path tempDir;

    Stardrops startingStardrops = new Stardrops(1);

    @Test
    void getStardrops_returns_0_if_file_is_null() {
        StardropsService testStardropsService = new StardropsService(tempDir.resolve("test-stardrops.txt").toString());
        assertEquals(0, testStardropsService.getStardrops().getCount());
    }

    //add happy path
    @Test
    void add_increments_count() {
        //GIVEN
        int increase = 1;
        //WHEN
        StardropsService testStardropsService = new StardropsService(tempDir.resolve("test-stardrops.txt").toString());
        testStardropsService.setCount(startingStardrops.getCount());
        //THEN
        int actualCount = testStardropsService.add(increase).getCount();

        assertEquals(2, actualCount);
    }

    @Test
    void add_does_not_exceed_max() {
        //GIVEN
        int increase = 7;
        //WHEN
        StardropsService testStardropsService = new StardropsService(tempDir.resolve("test-stardrops.txt").toString());
        testStardropsService.setCount(startingStardrops.getCount());
        //THEN
        int actualCount = testStardropsService.add(increase).getCount();

        assertEquals(7, actualCount);
    }

    @Test
    void set_count_does_not_exceed_max() {
        //GIVEN
        StardropsService testStardropsService = new StardropsService(tempDir.resolve("test-stardrops.txt").toString());
        //WHEN
        Stardrops actualStardrops = testStardropsService.setCount(Stardrops.MAX_STARDROPS + 1);
        //THEN
        assertEquals(Stardrops.MAX_STARDROPS, actualStardrops.getCount());
        assertEquals(Stardrops.MAX_STARDROPS, testStardropsService.getStardrops().getCount());
    }

}
