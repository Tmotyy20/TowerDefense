package tile;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage sprite;
    private boolean canPlaceTower;

    public Tile(BufferedImage sprite, boolean canPlaceTower) {
        this.sprite = sprite;
        this.canPlaceTower = canPlaceTower;
    }

    public boolean canPlaceTower() {
        return canPlaceTower;
    }

    public BufferedImage getSprite() {
        return sprite;
    }
}
