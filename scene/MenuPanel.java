package scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.GamePanel;
import main.GameWindow;

public class MenuPanel extends JPanel {
    private BufferedImage menuImage;
    private JButton playButton;
    private JButton quitButton;
    private GamePanel gamePanel;

    public MenuPanel(GameWindow gameWindow) {
        loadMenuImage();
        createButtons(gameWindow);
        setLayout(null);  // Disable layout manager for absolute positioning
        gamePanel = new GamePanel(gameWindow); 
    }

    private void loadMenuImage() {
        // Load the menu image
        try {
            menuImage = ImageIO.read(getClass().getResource("/res/menu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createButtons(GameWindow gameWindow) {

        playButton = new ImageButton("/res/playBtn.png");
        playButton.setBounds(100, 300, 200, 76); // position & width & height
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.switchToGamePanel();
            }
        });

        quitButton = new ImageButton("/res/quitBtn.png");
        quitButton.setBounds(100, 500, 163, 31); // position & width & height
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(playButton);
        add(quitButton);
    }

        // draw Menu image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuImage, 0, 0, this);
    }
}
