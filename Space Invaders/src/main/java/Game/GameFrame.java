package Game;

import javax.swing.*;

public class GameFrame extends JFrame {
    private GamePanel gamePanel;
    ImageIcon imageIcon=new ImageIcon("src/Images/ikona.png");
    public GameFrame(){
        gamePanel=new GamePanel();
        this.setTitle("SpaceInvaders");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(gamePanel);
        this.setIconImage(imageIcon.getImage());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        gamePanel.startGameThread();
        //if(gamePanel.isGameOver()){
          //  this.dispose();
       // }

    }

}
