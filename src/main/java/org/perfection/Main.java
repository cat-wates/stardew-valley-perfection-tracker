package org.perfection;

import org.perfection.controller.StardropsController;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.perfection.controller.StardropsController.printUsage;

//

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            printUsage();
            return;
        }
        String command = args[0];

        if ("stardrops".equalsIgnoreCase(command)) {
            StardropsController stardropsController = new StardropsController();
            stardropsController.handle(args);
        } else {
            System.out.println("Unknown command: " + command);
        }
    }
        }