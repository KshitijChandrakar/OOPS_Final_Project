package MainMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitButton extends MenuButton {
    public ExitButton(String text, Font font,int number) {
        super(text, font,number);
    }

    @Override
    public void action(MainMenuPanel m) {
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}

