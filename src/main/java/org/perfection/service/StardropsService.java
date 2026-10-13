package org.perfection.service;

import org.perfection.domain.Stardrops;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

@Service
public class StardropsService {
    private final Path saveFile;

    public StardropsService(@Value("${stardrops.save-file:./stardrops.txt}") String saveFile) {
        this.saveFile = Path.of(saveFile);
    }

    public Stardrops getStardrops() {
        int count = readCountFromFile();
        return new Stardrops(count);
    }

    public Stardrops setCount(int count) {
        Stardrops stardrops = new Stardrops(count);
        saveCount(stardrops.getCount());
        return stardrops;
    }

    public Stardrops add(int increase) {
        return setCount(getStardrops().getCount() + increase);
    }

    private int readCountFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(saveFile.toFile()))) {
            String line = reader.readLine();

            if (line == null || line.trim().isEmpty()) {
                return 0;
            }

            return Integer.parseInt(line.trim());
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
    }

    private void saveCount(int count) {
        try (FileWriter writer = new FileWriter(saveFile.toFile())) {
            writer.write(String.valueOf(count));
        } catch (IOException e) {
            throw new RuntimeException("Could not save Stardrop count.", e);
        }
    }
}