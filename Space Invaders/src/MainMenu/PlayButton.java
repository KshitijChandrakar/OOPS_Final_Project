package MainMenu;

import Game.GameFrame;
import Game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayButton extends MenuButton {
    public PlayButton(String text, Font font, int number) {
        super(text, font, number);
    }

    @Override
    public void action(MainMenuPanel m) {
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GamePanel panel = new GamePanel();

                SwingUtilities.getWindowAncestor(m).dispose();

                GameFrame gameFrame= new GameFrame();
            }
        });
    }
}


