package towers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import tile.TileManager;

public class MageTower extends ATower {
    private BufferedImage sprite;
    private BufferedImage[] animationFrames; 
    private int currentFrame; 
    private int animationDelay; 
    private int currentDelay; 
    private TileManager tileManager;

        public MageTower(int level, int damage, int range, int cost, BufferedImage sprite, TileManager tileManager) {
            super(level, damage, range, cost, sprite, 3); 
            this.sprite = sprite;
            this.tileManager = tileManager;


        // Initiate animation
        BufferedImage[] animationFrames = new BufferedImage[10];

        for (int frame = 0; frame < animationFrames.length; frame++) {
            animationFrames[frame] = tileManager.getSprite(9 - frame, 10);
        }
        
        this.animationFrames = animationFrames; 

        currentFrame = 0;
        animationDelay = 25;
        currentDelay = 0;
    }




    @Override
    public void updateAnimations() {
       
        currentDelay++;
        if (currentDelay >= animationDelay) {
            currentDelay = 0;
            currentFrame = (currentFrame + 1) % animationFrames.length;
            sprite = animationFrames[currentFrame];
        }
    }
    

    @Override
    public void upgradeDamage() {
        this.damage += 5;
    }

    @Override
    public void upgradeRange() {
        this.range += 10;
    }

    @Override
    public void draw(Graphics g) {
        if (sprite != null) {
            g.drawImage(sprite, position.x, position.y, null); // Draw the sprite
        } 
    }

    @Override
    public int getTargetNumber() {
        return 0;
    }
}