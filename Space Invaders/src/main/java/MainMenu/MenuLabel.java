package MainMenu;

import javax.swing.*;

public class MenuLabel extends JLabel {
    public MenuLabel(ImageIcon icon){
        this.setVerticalAlignment(JLabel.TOP);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setIcon(icon);
    }
}
