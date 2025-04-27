package MainMenu;

import javax.swing.*;

public class MainFrame extends JFrame {
    ImageIcon image = new ImageIcon("src/Images/ikona.png");
    MainMenuPanel mainMenuPanel = new MainMenuPanel();

    public MainFrame() {
        this.add(mainMenuPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("SpaceInvaders");
        this.setIconImage(image.getImage());
        this.setBounds(100, 100, 900, 700);
        this.setVisible(true);
        if(!mainMenuPanel.isVisible()){
            this.setVisible(false);
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
        MainFrame mainFrame=new MainFrame();
    }
}
