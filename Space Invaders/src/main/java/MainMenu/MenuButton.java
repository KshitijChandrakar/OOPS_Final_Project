package MainMenu;

import MainMenu.MainMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public abstract class MenuButton extends JButton {
    final int width=200;
    final int height=100;

    public MenuButton(String text,Font font,int number){
        this.setBounds(250,300+number,width,height);
        this.setText(text);
        this.setBackground(Color.green);
        this.setForeground(Color.WHITE);
        this.setFont(font);
    }

    public abstract void action(MainMenuPanel m) throws IOException, FontFormatException;

}

