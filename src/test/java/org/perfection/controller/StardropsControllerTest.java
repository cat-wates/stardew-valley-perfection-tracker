package org.perfection.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.perfection.domain.Stardrops;
import org.perfection.service.StardropsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StardropsControllerTest {

    @Mock
    StardropsService stardropsService;

    @InjectMocks
    StardropsController stardropsController;

    @Test
    void show_formats_current_count() {
        when(stardropsService.getStardrops()).thenReturn(new Stardrops(3));

        assertEquals("Stardrops collected: 3 / 7", stardropsController.show());
        verify(stardropsService).getStardrops();
    }

    @Test
    void set_saves_count_and_formats_result() {
        when(stardropsService.setCount(5)).thenReturn(new Stardrops(5));

        assertEquals("Updated Stardrops.\nStardrops collected: 5 / 7", stardropsController.set(5));
        verify(stardropsService).setCount(5);
    }

    @Test
    void add_amends_count_and_formats_result() {
        when(stardropsService.add(2)).thenReturn(new Stardrops(3));

        assertEquals("Updated Stardrops.\nStardrops collected: 3 / 7", stardropsController.add(2));
        verify(stardropsService).add(2);
    }
}
