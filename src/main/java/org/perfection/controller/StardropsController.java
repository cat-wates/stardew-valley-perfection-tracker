package org.perfection.controller;

import org.perfection.domain.Stardrops;
import org.perfection.service.StardropsService;

public class StardropsController {
    private final StardropsService stardropsService;

    public StardropsController() {
        this.stardropsService = new StardropsService();
    }

    public static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  stardrops show");
        System.out.println("  stardrops set <count>");
        System.out.println("  stardrops add <amount>");
    }

    public void handle(String[] args) {
        if (args.length < 2) {
            printUsage();
            return;
        }

        String action = args[1];

        if ("show".equalsIgnoreCase(action)) {
            show();
        } else if ("set".equalsIgnoreCase(action)) {
            set(args);
        } else if ("add".equalsIgnoreCase(action)) {
            add(args);
        } else {
            System.out.println("Unknown Stardrops command: " + action);
            printUsage();
        }
    }

    private void show() {
        Stardrops stardrops = stardropsService.getStardrops();
        printStardrops(stardrops);
    }

    private void set(String[] args) {
        if (args.length < 3) {
            System.out.println("Missing Stardrop value.");
            printUsage();
            return;
        }

        Integer count = Integer.parseInt(args[2]);

        if (count == null) {
            System.out.println("Stardrop count must be a number.");
        }

        try {
            Stardrops stardrops = stardropsService.setCount(count.intValue());
            System.out.println("Updated Stardrops.");
            printStardrops(stardrops);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void add(String[] args) {
        if (args.length < 3) {
            System.out.println("Missing Stardrop value.");
            printUsage();
            return;
        }

        Integer increase = parseNumber(args[2]);

        if (increase == null) {
            System.out.println("Stardrop value must be a number.");
        }

        try {
            Stardrops stardrops = stardropsService.add(increase.intValue());
            System.out.println("Updated Stardrops.");
            printStardrops(stardrops);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private Integer parseNumber(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void printStardrops(Stardrops stardrops) {
        System.out.println("Stardrops collected: " + stardrops.getCount() + " / " + stardrops.getMaxCount());
    }
}