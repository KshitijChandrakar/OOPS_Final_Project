package MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HighScoreButton extends MenuButton {
    public HighScoreButton(String text, Font font, int number) {
        super(text, font, number);
        addActionListener();
    }

    private void addActionListener() {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame highScoreFrame = new JFrame("High Score");
                highScoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                highScoreFrame.setSize(300, 200);
                highScoreFrame.setLocationRelativeTo(null);

                JTextArea highScoreTextArea = new JTextArea();
                highScoreTextArea.setEditable(false);
                highScoreTextArea.setFont(new Font("Arial", Font.PLAIN, 16));

                try (BufferedReader reader = new BufferedReader(new FileReader("score.txt"))) {
                    String line;
                    int highestScore = Integer.MIN_VALUE;
                    while ((line = reader.readLine()) != null) {
                        if (!line.trim().isEmpty()) {
                            int score = Integer.parseInt(line);
                            if (score > highestScore) {
                                highestScore = score;
                            }
                        }
                    }
                    highScoreTextArea.setText("Highest Score: " + (highestScore == Integer.MIN_VALUE ? "No scores available" : highestScore));
                } catch (IOException ex) {
                    ex.printStackTrace();
                    highScoreTextArea.setText("Error loading high score.");
                }

                highScoreFrame.add(highScoreTextArea);
                highScoreFrame.setVisible(true);
            }
        });
    }

    @Override
    public void action(MainMenuPanel m) {

    }
}

