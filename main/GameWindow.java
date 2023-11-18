package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import inputs.KeyBoardHandler;
import inputs.MouseHandler;
import scene.MenuPanel;

public class GameWindow extends JFrame implements Runnable { // Implements Runnable for thread usage

    private GamePanel gamePanel; // Instance gamepanel which contain draws
    private MenuPanel menuPanel;
    private JFrame frame;
    private volatile boolean states;

    // Screen Settings
    final int originalTileSize = 64; // Base tile size
    final int scale = 1; // Scale factor for the tiles
    public final int tileSize = originalTileSize * scale; // Scaled tile size

    public final int maxScreenCol = 20; // Maximum number of columns on the screen
    public final int maxScreenRow = 12; // Maximum number of rows on the screen
    public final int screenWidth = tileSize * maxScreenCol; // Total screen width
    public final int screenHeight = tileSize * maxScreenRow; // Total screen height

    // Full screen
    // int screenWidth2 = screenWidth;
    // int screenHeight2 = screenHeight;
    // BufferedImage tempScreen;
    // Graphics2D g2;

    // FPS and UPS
    int FPS = 120;
    int UPS = 60;

    Thread gameThread; // Thread for the game loop
    KeyBoardHandler keyBoardHandler = new KeyBoardHandler(); // Instanciate Keyboard functions
    MouseHandler mouseHandler = new MouseHandler(gamePanel);

    public GameWindow() {
        gamePanel = new GamePanel(this); // instanciate gamePanel which contain map, enemies, towers
        menuPanel = new MenuPanel(this); 
        mouseHandler = new MouseHandler(gamePanel); 
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        this.addKeyListener(keyBoardHandler); // Keyboard inputs
        this.setContentPane(gamePanel); // Define gamePanel as main content

        this.setSize(screenWidth, screenHeight); // Set size of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        this.setLocationRelativeTo(null); // Center the window

        this.setContentPane(menuPanel); // Add the menu panel to the content panel
    }

    public void switchToGamePanel() {
        // Switch to the game panel
        this.setContentPane(gamePanel);
        validate();
        repaint();
        states = true;
    }
    public void switchToMenuPanel() {
        // Switch to the menu panel
        this.setContentPane(menuPanel);
        validate();
        repaint();
        states = false;
    }

    public void startGameThread() {
        gameThread = new Thread(this); // Instantiate the game thread
        gameThread.start(); // Start the thread, calling the run method
    }

    @Override
    public void run() {
        // tempScreen = new BufferedImage(screenWidth, screenHeight,
        // BufferedImage.TYPE_INT_ARGB);
        // g2 = (Graphics2D)tempScreen.getGraphics();

        // The desired frames per second (FPS) and updates per second (UPS)
        double timePerFrame = 1000000000.0 / FPS; // Time interval for each frame
        double timePerUpdate = 1000000000.0 / UPS; // Time interval for each update

        // Timestamps for the last frame, update, and time check
        long lastFrame = System.nanoTime(); // Stores the timestamp of the last frame render
        long lastUpdate = System.nanoTime(); // Stores the timestamp of the last game update
        long lastTimeCheck = System.currentTimeMillis(); // Stores the timestamp of the last time check

        // Counters for frames and updates
        int frames = 0; // Counts how many frames have been rendered
        int updates = 0; // Counts how many updates have been performed

        long now; // Variable to store the current time
        while (true) {
            if (states) { // Check if the game is not paused
                now = System.nanoTime();
    
                // Render - check if it's time to render a new frame
                if (now - lastFrame >= timePerFrame) {
                    gamePanel.repaint();
                    lastFrame = now;
                    frames++;
                }
    
                // Update - check if it's time to update game logic
                if (now - lastUpdate >= timePerUpdate) {
                    gamePanel.update();
                    lastUpdate = now;
                    updates++;
                }
    
                // Check if one second has passed to print FPS and UPS
                if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                    System.out.println("FPS: " + frames + " | UPS: " + updates);
                    frames = 0;
                    updates = 0;
                    lastTimeCheck = System.currentTimeMillis();
                }
            } else {
                try {
                    Thread.sleep(50); // Sleep for 50 milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GameWindow gameWindow = new GameWindow(); // Create game window
            gameWindow.setVisible(true); // Make window visible
            gameWindow.startGameThread(); // Start the game thread
        });
    }
}