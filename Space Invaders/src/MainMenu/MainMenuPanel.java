package MainMenu;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainMenuPanel extends JPanel implements Runnable {
    final int width = 750;
    final int height = 750;
    Thread thread;

    int FPS = 60;
    Font f;


    int alienX = 0;
    int alienY = 100;
    int alienSpeed = 5;
    boolean movingRight = true;

    {
        try {
            f = Font.createFont(Font.TRUETYPE_FONT, new File("src/Font/font.ttf")).deriveFont(18f);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    ImageIcon logoImage = new ImageIcon("src/Images/logo.png");
    ImageIcon alienImage = new ImageIcon("src/Images/alien.png");
    JLabel logoLabel = new JLabel(logoImage);
    JLabel alienLabel = new JLabel(alienImage);
    PlayButton playButton = new PlayButton("Play", f, 0);
    OptionsButton optionsButton = new OptionsButton("Controls", f, 0);
    HighScoreButton highScoreButton = new HighScoreButton("High Score", f, 180);
    ExitButton exitButton = new ExitButton("Quit", f, 0);

    public MainMenuPanel() {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        setLayout(null);


        // Setting location and size for the logo label
        logoLabel.setBounds((width - logoImage.getIconWidth()) , 20, logoImage.getIconWidth(), logoImage.getIconHeight());
        add(logoLabel);



        // Setting fixed size for buttons
        Dimension buttonSize = new Dimension(200, 70);
        playButton.setPreferredSize(buttonSize);
        optionsButton.setPreferredSize(buttonSize);
        highScoreButton.setPreferredSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);

        // Adding buttons with absolute positioning
        playButton.setBounds(width / 2 - 100, height / 2 - 50, 200, 70);
        optionsButton.setBounds(width / 2 - 100, height / 2 + 30, 200, 70);
        highScoreButton.setBounds(width / 2 - 100, height / 2 + 110, 200, 70);
        exitButton.setBounds(width / 2 - 100, height / 2 + 190, 200, 70);

        add(playButton);
        add(optionsButton);
        add(highScoreButton);
        add(exitButton);
        // Setting location and size for the alien label
        alienLabel.setBounds(alienX, alienY, 400, 300);
        add(alienLabel);

        // Setting up button actions
        playButton.action(this);
        optionsButton.action(this);
        highScoreButton.action(this);
        exitButton.action(this);


        // Start the animation thread
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (thread != null) {
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        alienLabel.setLocation(alienX, alienY);
        if (movingRight) {
            alienX += alienSpeed;
            if (alienX + alienLabel.getWidth() >= width) {
                movingRight = false;
            }
        } else {
            alienX -= alienSpeed;
            if (alienX <= 0) {
                movingRight = true;
            }
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

}

