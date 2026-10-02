package org.perfection.service;

import org.perfection.domain.Stardrops;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class StardropsService {
    private static final String STARDROPS_SAVE_FILE_NAME = "stardrops.txt";

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
        Stardrops stardrops = getStardrops();
        int updatedCount = stardrops.getCount() + increase;
        stardrops.setCount(updatedCount);
        saveCount(stardrops.getCount());
        return stardrops;
    }

    private int readCountFromFile() {
        File file = new File(STARDROPS_SAVE_FILE_NAME);

        if (!file.exists()) {
            return 0;
        }

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            if (line == null || line.trim().length() == 0) {
                return 0;
            }

            return Integer.parseInt(line.trim());
        } catch (IOException e) {
            return 0;
        } catch (NumberFormatException e) {
            return 0;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    private void saveCount(int count) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(STARDROPS_SAVE_FILE_NAME);
            writer.write(String.valueOf(count));
        } catch (IOException e) {
            throw new RuntimeException("Could not save Stardrop count.", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

}