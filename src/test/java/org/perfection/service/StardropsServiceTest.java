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

}
