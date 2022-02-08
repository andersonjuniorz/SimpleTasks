package classes;

import java.awt.Color;
import javax.swing.JFrame;

public class transparent {
    public void ApplyTransparency(JFrame frame){
        frame.setUndecorated(true);
        frame.setBackground(new Color(0,0,0,0));      
    }
}
