package MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsButton extends MenuButton {
    public OptionsButton(String text, Font font,int number) {
        super(text, font,number);

    }

    @Override
    public void action(MainMenuPanel m) {
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {//creating window for controls
                JFrame frame = new JFrame("Space Invaders - Controls");
                JLabel label = new JLabel("<html><h1>Controls</h1>"
                        + "<p>left/right: moving</p>"
                        + "<p>Space: shooting</p></html>");
                label.setHorizontalAlignment(CENTER);

                frame.getContentPane().add(label);

                frame.setSize(400, 200);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
