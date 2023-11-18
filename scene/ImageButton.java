package scene;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton {
    public ImageButton(String imagePath) {
        // Create icon with image
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));

        // Apply icon
        setIcon(icon);

        // delete background and border
        setBorderPainted(false);
        setContentAreaFilled(false);
    }
}