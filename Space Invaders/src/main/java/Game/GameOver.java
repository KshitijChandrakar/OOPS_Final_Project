package Game;

import MainMenu.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver extends JFrame {
    ImageIcon imageIcon = new ImageIcon("src/Images/GameOver.png");

    public GameOver() {
        setTitle("GAME OVER");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        // Vytvoření JLabel pro obrázek Game Over
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // Vytvoření panelu pro tlačítka
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Tlačítko "Go to menu"
        JButton playAgainButton = new JButton("Go to menu");
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Zavřít okno Game Over
                dispose();
                // Spustit hru znovu
                MainFrame m = new MainFrame();
            }
        });
        buttonPanel.add(playAgainButton);

        // Tlačítko "Quit"
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ukončit aplikaci
                System.exit(0);
            }
        });
        buttonPanel.add(quitButton);

        // Přidat panel s tlačítky do okna
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }


}
