package Game;

import Handlers.KeyHandler;
import Handlers.ScoreManager;
import Handlers.SoundManager;
import vendor.BasicBlocks;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import java.util.concurrent.CopyOnWriteArrayList;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int width = tileSize * maxScreenCol;
    final int height = tileSize * maxScreenRow;
    int FPS = 60;

    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    SoundManager soundManager = new SoundManager();
    private boolean playerAlive;
    private boolean GameOver;

    int shotSpeed = 10;
    int playerSpeed = 3;
    int alienSpeed = 2;
    private Player player;
    private Shot shot;
    int numberOfDestroyedAliens = 0;

    private BasicBlocks bb;
    private CopyOnWriteArrayList<Alien> aliens;
    private CopyOnWriteArrayList<AlienShot> alienShots;
    private int playerX = 100;
    private int playerY = 500;
    private Timer alienShotTimer;
    private int score = 0; // Score variable

    private GameFrame gameFrame;
    ScoreManager scoreManager;


    public GamePanel() {

        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        this.scoreManager = new ScoreManager("score.txt");

        aliens = new CopyOnWriteArrayList<>();
        shot = new Shot();
        createEnemies();
        bb = new BasicBlocks();
        player = new Player(playerX, playerY, tileSize, tileSize);
        alienShots = new CopyOnWriteArrayList<>();

        // Set up a timer to shoot aliens every second
        alienShotTimer = new Timer(200, e -> alienShoot());
        alienShotTimer.start();



    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void gameOver() {
        scoreManager.writeScore(score);
        GameOver gameOver = new GameOver();
        gameThread.stop();
        alienShotTimer.stop();

    }


    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
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
        setPlayerAlive(player.isAlive());
        if (!player.isAlive()) {
            SwingUtilities.getWindowAncestor(this).dispose();
            gameOver();
            return;
        }

        player.playerMoving(keyHandler, width, tileSize, playerSpeed);
        shot.shooting(keyHandler, player, shotSpeed);

        if (!shot.isShooting) {
            soundManager.playShoot();
        }

        // Use CopyOnWriteArrayList to remove aliens while iterating
        for (Alien alien : aliens) {
            alien.update(shot, tileSize);
            if (alien.isDestroyed()) {
                aliens.remove(alien);
                shot.isShooting = false;
                numberOfDestroyedAliens++;
                score += 100; // Increment score by 100 for each destroyed alien
            }
            if (alien.getY() == player.getY()) {
                player.loseLife();
                if (!player.isAlive()) {
                    SwingUtilities.getWindowAncestor(this).dispose();
                    gameOver();
                    return;
                }
            }

            // Check collision between aliens and blocks
            Iterator<Rectangle> blockIterator = bb.wall.iterator();
            while (blockIterator.hasNext()) {
                Rectangle block = blockIterator.next();
                if (alien.getBounds().intersects(block)) {
                    blockIterator.remove();
                }
            }
        }

        if (numberOfDestroyedAliens == 24) {
            Alien.speed = Math.abs(Alien.speed) + 1;
            createEnemies();
            numberOfDestroyedAliens = 0;
        }

        Alien.move(aliens, width);

        for (AlienShot alienShot : alienShots) {
            alienShot.move();
            if (!alienShot.isShooting) {
                alienShots.remove(alienShot);
            } else if (checkCollision(alienShot, player)) {
                player.loseLife();
                if (!player.isAlive()) {
                    SwingUtilities.getWindowAncestor(this).dispose();
                    gameOver();
                    return;
                }
                alienShots.remove(alienShot);
            } else if (checkBlockCollision(alienShot)) {
                alienShots.remove(alienShot);
            }
        }

        // Check collision with blocks for player shot
        if (shot.isShooting) {
            if (checkBlockCollision(shot)) {
                shot.isShooting = false;
            }
        }
    }



    private boolean checkBlockCollision(Shot shot) {
        Iterator<Rectangle> iterator = bb.wall.iterator();
        while (iterator.hasNext()) {
            Rectangle block = iterator.next();
            if (shot.getBounds(tileSize, tileSize).intersects(block)) {
                iterator.remove();

                return true;
            }
        }
        return false;
    }
    public void setPlayerAlive(boolean alive) {
        this.playerAlive = alive;
    }

    public boolean isPlayerAlive() {
        return playerAlive;
    }

    private boolean checkBlockCollision(AlienShot alienShot) {
        Iterator<Rectangle> iterator = bb.wall.iterator();
        while (iterator.hasNext()) {
            Rectangle block = iterator.next();
            if (alienShot.getBounds().intersects(block)) {
                iterator.remove();

                return true;
            }
        }
        return false;
    }

    public void createEnemies() {
        int startX = 0;
        int startY = 0;
        int gap = 10;
        int rows = 4;
        int cols = 6;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = startX + (tileSize + gap) * j;
                int y = startY + (tileSize + gap) * i;
                aliens.add(new Alien(x, y, tileSize, tileSize));
            }
        }
    }

    public void alienShoot() {
        if (!aliens.isEmpty()) {
            Random rand = new Random();
            Alien shootingAlien = aliens.get(rand.nextInt(aliens.size()));
            alienShots.add(shootingAlien.shoot());

        }
    }

    public boolean checkCollision(AlienShot alienShot, Player player) {
        return alienShot.getBounds().intersects(new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight()));
    }


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        player.draw(g2d);
        ArrayList<Alien> aliensCopy = new ArrayList<>(aliens);
        bb.draw(g2d);

        for (Alien alien : aliensCopy) {
            alien.draw(g2d);
        }

        if (shot.isShooting) {
            g2d.drawImage(new ImageIcon("src/Images/bullet.png").getImage(), shot.getX(), shot.getY(), tileSize, tileSize, null);
        }

        for (AlienShot alienShot : alienShots) {
            alienShot.draw(g2d);
        }

        g2d.setColor(Color.GREEN);
        g2d.drawLine(0, player.getY() + tileSize, 9999, player.getY() + tileSize);

        // Draw the score
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Score: " + score, 10, 20);

        // Draw the player's lives
        g2d.setColor(Color.WHITE);
        g2d.drawString("Lives: " + player.getLives(), width - 100, 20);

        g2d.dispose();
    }


    public boolean isGameOver() {
        return !playerAlive;
    }
}
