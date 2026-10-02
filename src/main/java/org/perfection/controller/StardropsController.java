package org.perfection.controller;

import org.perfection.domain.Stardrops;
import org.perfection.service.StardropsService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class StardropsController {
    private final StardropsService stardropsService;

    public StardropsController(StardropsService stardropsService) {
        this.stardropsService = stardropsService;
    }

    @ShellMethod(key = "stardrops show", value = "Show the Stardrops count")
    public String show() {
        return formatStardrops(stardropsService.getStardrops());
    }

    @ShellMethod(key = "stardrops set", value = "Set the Stardrops count")
    public String set(int count) {
        return formatUpdatedStardrops(stardropsService.setCount(count));
    }

    @ShellMethod(key = "stardrops add", value = "Add to the Stardrops count")
    public String add(int amount) {
        return formatUpdatedStardrops(stardropsService.add(amount));
    }

    private String formatUpdatedStardrops(Stardrops stardrops) {
        return "Updated Stardrops.\n" + formatStardrops(stardrops);
    }

    private String formatStardrops(Stardrops stardrops) {
        return "Stardrops collected: " + stardrops.getCount() + " / " + stardrops.getMaxCount();
    }
}