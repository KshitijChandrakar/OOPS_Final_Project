package Handlers;

import java.io.FileWriter;
import java.io.IOException;

public class ScoreManager {

    private String fileName;

    public ScoreManager(String fileName) {
        this.fileName = fileName;
    }

    public void writeScore(int score) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(score + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
