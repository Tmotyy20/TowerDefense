package enemies;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import tile.TileManager;

public class BombEnemy extends AEnemy {
    private BufferedImage sprite;
    private BufferedImage[] animationFrames; 
    private int currentFrame; 
    private int animationDelay; 
    private int currentDelay;
    private TileManager tileManager;
    private int row;

    int[][] lvl = main.Maps.getLevelData();

    public BombEnemy(int pv, int speed, TileManager tileManager) {
        super(10, 1);
        this.tileManager = tileManager;
        

        animationFrames = new BufferedImage[10];
        currentFrame = 0;
        animationDelay = 3;
        currentDelay = 0;
        initAnimationRow();
        UpdateFrames();
    }

    public void initAnimationRow() {
        if (lastDirection == Direction.RIGHT || lastDirection == Direction.UP) {
            row = 9;
        }
        else {
            row = 2;
        }
    }

    public void UpdateFrames() {
        for (int frame = 0; frame < animationFrames.length; frame++) {
            animationFrames[frame] = tileManager.getSprite(9 - frame, row);
        }

    }

    public void Animation() {
        currentDelay++;
        if (currentDelay >= animationDelay) {
            currentDelay = 0;
            currentFrame = (currentFrame + 1) % animationFrames.length;
            sprite = animationFrames[currentFrame];
        }
    }

    public void AnimationChange() {

        if (lastDirection == Direction.LEFT || lastDirection == Direction.DOWN) {
            row = 2;
            UpdateFrames();
        }
        else if (lastDirection == Direction.RIGHT || lastDirection == Direction.UP) {
            row = 9;
            UpdateFrames();
        }
    }

    @Override
    public void move(int[][] lvl) {
        super.move(lvl);

        Animation();
        AnimationChange();

    }

    @Override
    public void draw(Graphics g) {
        if (sprite != null) {
            g.drawImage(sprite, position.x, position.y, null);
        }
        if (sprite != null) {
        g.drawImage(sprite, position.x, position.y, null);
        drawHealthBar(g); // Draw the health bar
        }
    }
}