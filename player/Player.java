package player;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

import main.Coordinate;

public class Player {
    private int health;
    private int money;
    private int barWidth; // Width Barlife
    private int barHeight = 5; // Height barLife
    private Coordinate position;
    private int score;
    private Image moneyImage;
    private Font moneyFont;

    public Player(int initialHealth, int money) {
        this.health = initialHealth;
        this.money = money;
        this.position = new Coordinate(1170, 580); 
        this.barWidth = this.health * 5;
        moneyImage = new ImageIcon("res/money.png").getImage();

        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("font/a.ttf")).deriveFont(Font.PLAIN, 20);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            moneyFont = font;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }


     // Method to draw health bar
     public void CastleHealthBar(Graphics g) {
        int x = position.x;
        int y = position.y;

        

        if (barWidth <= 80 && barWidth >= 60) {
            g.setColor(Color.YELLOW);
        } else if (barWidth >= 35 && barWidth <= 59) {
            g.setColor(Color.ORANGE);
        } else if (barWidth < 35) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.GREEN);
        }

        g.fillRect(x, y, barWidth, barHeight);  // Initiate position, width and height
    

    }


    public void drawMoneyAmount(Graphics g) {
        int x = 10;  // position
        int y = 25; 
    
        g.setFont(moneyFont);

        g.drawImage(moneyImage, x, 20 - moneyImage.getHeight(null) / 2, null); 
    
        x += moneyImage.getWidth(null) + 5; // space between image and text
    
        g.setColor(Color.WHITE); 
        g.setFont(moneyFont); // use the custom font
        g.drawString("Money: " + money, x, y); 
    }


    public void DrawMoney(Graphics g){
        drawMoneyAmount(g);
    }

    public void loseHealth(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
        barWidth = this.health * 5;
    }

    public void spendMoney(int amount) {
        this.money -= amount;
    }

    public void earnMoney(int amount) {
        this.money += amount;
    }

    public int getHealth() {
        return health;
    }

    public int getMoney() {
        return money;
    }


   
    public void earnScore(int points) {
        this.score += points;
    }

   
    public int getScore() {
        return this.score + this.getMoney();
    }

  
}
