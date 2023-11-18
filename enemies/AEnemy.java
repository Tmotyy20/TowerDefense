package enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.Coordinate;

public abstract class AEnemy implements IEnemy {
    protected int pv;
    protected int speed;
    protected boolean isAlive;
    protected boolean isHit;
    protected boolean hasReachedEnd;
    protected Coordinate position;
    protected Direction lastDirection;
    protected int maxHealth; // Maximum health of the enemy
    protected int currentHealth; // Current health of the enemy
    protected int barWidth; // Width BarLife
    protected int barHeight = 5; // Height barLife

    // Define Direction for move method
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    protected final int tileSize = 64;
    protected Rectangle hitbox;

    public AEnemy(int pv, int speed) {
        this.pv = pv;
        this.speed = speed;
        this.isAlive = true;
        this.isHit = false;
        this.hasReachedEnd = false;
        this.position = new Coordinate(0, 0); // Default position
        this.lastDirection = Direction.RIGHT; // Initiate the default direction
        this.hitbox = new Rectangle(position.x, position.y, 5, 5); // Hitbox
        this.barWidth = 4 * pv;
    }

    // Method to draw health bar
    public void drawHealthBar(Graphics g) {
        int x = position.x - (barWidth - tileSize) / 2;
        int y = position.y ;


        
        g.fillRect(x, y, barWidth, barHeight);  

        if (barWidth <= 30 && barWidth >= 20) {
            g.setColor(Color.YELLOW);
                 
        }else if (barWidth >= 10 && barWidth <= 19) {
            g.setColor(Color.ORANGE);
                   
        }else if (barWidth < 10) {
            g.setColor(Color.RED);
        }else{
            g.setColor(Color.GREEN);
        }

    }
    
    @Override
    public int getPV() {
        return pv;
    }

    @Override
    public void setPV(int pv) {
        this.pv = pv;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean isHit) {
        this.isHit = isHit;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public Direction getLastDirection() {
        return lastDirection;
    }

    // Movement Method
    public void move(int[][] lvl) {
        int xTile = position.x / tileSize;
        int yTile = position.y / tileSize;

        // Possibles directions
        boolean canMoveRight = xTile + 1 < lvl[0].length && lvl[yTile][xTile + 1] == 7; // if next tile is inferior
                                                                                        // board size (here col) and
                                                                                        // next tile walkable =
                                                                                        // canmoveright
        boolean canMoveLeft = xTile - 1 >= 0 && lvl[yTile][xTile - 1] == 7;
        boolean canMoveUp = yTile - 1 >= 0 && lvl[yTile - 1][xTile] == 7;
        boolean canMoveDown = yTile + 1 < lvl.length && lvl[yTile + 1][xTile] == 7;

        if (!hasReachedEnd(lvl)) {

            // If full sprite tile reached, choose new direction
            if (position.x % tileSize == 0 && position.y % tileSize == 0) {
                if (canMoveRight && lastDirection != Direction.LEFT) {
                    lastDirection = Direction.RIGHT;
                } else if (canMoveDown && lastDirection != Direction.UP) {
                    lastDirection = Direction.DOWN;
                } else if (canMoveLeft && lastDirection != Direction.RIGHT) {
                    lastDirection = Direction.LEFT;
                } else if (canMoveUp && lastDirection != Direction.DOWN) {
                    lastDirection = Direction.UP;
                }
            }

            // Move enemy in function of lastdirection
            switch (lastDirection) {
                case RIGHT:
                    position.x += speed;
                    break;
                case LEFT:
                    position.x -= speed;
                    break;
                case UP:
                    position.y -= speed;
                    break;
                case DOWN:
                    position.y += speed;
                    break;
            }

            // Update hitbox after movement
            this.hitbox.setLocation(position.x, position.y);
        }

        // Check if ennemy reached destination
        if (hasReachedEnd(lvl)) {
            hasReachedEnd = true;
        }
    }

    public void receiveDamage(int damage) {
        this.isHit = true;
        this.pv -= damage;

        if (this.pv <= 0) {
            this.isAlive = false;
            this.pv = 0; // only positive life
        }
        barWidth -= 4 * damage;
    }

    public boolean hasReachedEnd(int[][] lvl) {
        // Calculate the current column (x) of the enemy
        int xTile = position.x / tileSize;

        // Check if the enemy is in the last column of the level
        if (xTile >= lvl[0].length - 3) {
            return true;
        }

        return false;
    }

    // Abstract method to draw enemy
    @Override
    public abstract void draw(Graphics g);
}
